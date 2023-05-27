package org.lacourd.lendinglibrary.controllers;

import org.lacourd.lendinglibrary.data.GameDetailsRepository;
import org.lacourd.lendinglibrary.data.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameDetailsRepository gameDetailsRepository;

    @GetMapping
    public String displayAllGames( Model model) {
        model.addAttribute("title", "All Games");
        model.addAttribute("games", gameRepository.findAll());
        return "games/index";
    }

}
