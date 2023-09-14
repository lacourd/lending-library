package lacourd.lendinglibrary.controllers;

import lacourd.lendinglibrary.data.*;
import lacourd.lendinglibrary.models.Game;
import lacourd.lendinglibrary.models.StorageLocation;
import lacourd.lendinglibrary.models.Tag;
import lacourd.lendinglibrary.models.dto.GameTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameDetailsRepository gameDetailsRepository;

    @Autowired
    private StorageLocationRepository storageLocationRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String displayAllGames(@RequestParam(required = false) Integer locationId, @RequestParam(required = false) Integer tagId, Model model) {
        if (locationId == null && tagId == null) {
            model.addAttribute("title", "All Games");
            model.addAttribute("games", gameRepository.findAll(Sort.by("name")));
            model.addAttribute("loans", loanRepository.findAll());
        } else if (locationId != null && tagId == null) {
            Optional<StorageLocation> result = storageLocationRepository.findById(locationId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Storage Location ID: " + locationId);
            } else {
                StorageLocation location = result.get();
                model.addAttribute("title", "Games in storage location: " + location.getName());
                model.addAttribute("games", location.getGames());
            }
        } else if (locationId == null && tagId != null) {
            Optional<Tag> result = tagRepository.findById(tagId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Tag");
            } else {
                Tag tag = result.get();
                model.addAttribute("title", "Games with tag: " + tag.getName());
                model.addAttribute("games", tag.getGames());
            }
        }
            return "games/index";
    }

    @GetMapping("add")
    public String renderAddGameForm(Model model) {
        model.addAttribute("title", "Add Game");
        model.addAttribute(new Game());
        model.addAttribute("locations", storageLocationRepository.findAll());
        return "games/add";
    }

    @PostMapping("add")
    public String processAddGameForm(@ModelAttribute @Valid Game newGame, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Game");
            return "games/add";
        }

        gameRepository.save(newGame);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteGameForm(Model model) {
        model.addAttribute("title", "Delete Games");
        model.addAttribute("games", gameRepository.findAll(Sort.by("name")));
        return "games/delete";
    }

    @PostMapping("delete")
    public String processDeleteGameForm(@RequestParam(required = false) int[] gameIds) {
        if (gameIds !=null) {
            for (int id : gameIds) {
                gameRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("detail")
    public String displayGameDetails(@RequestParam Integer gameId, Model model) {

        Optional<Game> result = gameRepository.findById(gameId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Game ID: " + gameId);
        } else {
            Game game = result.get();
            model.addAttribute("title", game.getName() + " Details");
            model.addAttribute("game", game);
        }

        return "games/detail";
    }

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer gameId, Model model) {
        Optional<Game> result = gameRepository.findById(gameId);
        Game game = result.get();
        model.addAttribute("title","Add tag to " + game.getName());
        model.addAttribute("tags", tagRepository.findAll());
        GameTagDTO gameTag = new GameTagDTO();
        gameTag.setGame(game);
        model.addAttribute("gameTag", gameTag);
        return "games/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid GameTagDTO gameTag, Errors errors, Model model) {
        if (!errors.hasErrors()) {
            Game game = gameTag.getGame();
            Tag tag = gameTag.getTag();
            if (!game.getTags().contains(tag)) {
                game.addTag(tag);
                gameRepository.save(game);
            }
            return "redirect:detail?gameId=" + game.getId();
        }
        return "redirect:add-tag";
    }
}
