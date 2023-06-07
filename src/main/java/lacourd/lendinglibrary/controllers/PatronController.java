package lacourd.lendinglibrary.controllers;

import lacourd.lendinglibrary.data.GameRepository;
import lacourd.lendinglibrary.data.LoanRepository;
import lacourd.lendinglibrary.data.PatronRepository;
import lacourd.lendinglibrary.models.Game;
import lacourd.lendinglibrary.models.Loan;
import lacourd.lendinglibrary.models.Patron;
import lacourd.lendinglibrary.models.StorageLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("patrons")
public class PatronController {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PatronRepository patronRepository;

    @GetMapping
    public String displayAllPatrons(Model model) {
        model.addAttribute("title", "All Patrons");
        model.addAttribute("patrons", patronRepository.findAll());
        return "patrons/index";
    }

    @GetMapping("add")
    public String renderCheckoutForm(Model model) {
        model.addAttribute("title", "New Patron");
        model.addAttribute(new Patron());
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("patrons", patronRepository.findAll());
        return "patrons/add";
    }

    @PostMapping("add")
    public String processCheckoutForm(@ModelAttribute @Valid Patron newPatron, Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Patron");
            return "patrons/add";
        }

        patronRepository.save(newPatron);
        return "redirect:";
    }
}
