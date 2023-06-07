package lacourd.lendinglibrary.controllers;

import lacourd.lendinglibrary.data.GameDetailsRepository;
import lacourd.lendinglibrary.data.GameRepository;
import lacourd.lendinglibrary.data.StorageLocationRepository;
import lacourd.lendinglibrary.models.Game;
import lacourd.lendinglibrary.models.StorageLocation;
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

    @GetMapping
    public String displayAllGames(@RequestParam(required = false) Integer locationId, Model model) {
        if (locationId == null) {
            model.addAttribute("title", "All Games");
            model.addAttribute("games", gameRepository.findAll());
        } else {
            Optional<StorageLocation> result = storageLocationRepository.findById(locationId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Storage Location ID: " + locationId);
            } else {
                StorageLocation location = result.get();
                model.addAttribute("title", "Games in storage location: " + location.getName());
                model.addAttribute("games", location.getGames());
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
        model.addAttribute("games", gameRepository.findAll());
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
}
