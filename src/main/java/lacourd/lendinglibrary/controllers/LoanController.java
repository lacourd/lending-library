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
@RequestMapping("checkouts")
public class LoanController {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PatronRepository patronRepository;

    @GetMapping
    public String displayAllLoans(Model model) {
        model.addAttribute("title", "All Checkouts");
        model.addAttribute("loans", loanRepository.findAll());
        return "checkouts/index";
    }

    @GetMapping("new")
    public String renderCheckoutForm(Model model) {
        model.addAttribute("title", "New Checkout");
        model.addAttribute(new Loan());
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("patrons", patronRepository.findAll());
        return "checkouts/new";
    }

    @PostMapping("new")
    public String processCheckoutForm(@ModelAttribute @Valid Loan newLoan, Errors errors, Model model,
                                        @RequestParam int gameId, @RequestParam int patronId) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "New Checkout");
            return "checkouts/new";
        }
        Optional<Game> result1 = gameRepository.findById(gameId);
        if (result1.isPresent()) {
            Game checkedOutGame = result1.get();
            newLoan.setGameCheckedOut(checkedOutGame);
//            need to add a method to indicate the game is not available for checkout
//            checkedOutGame.setGameDetails();
        }
        Optional<Patron> result2 = patronRepository.findById(patronId);
        if (result2.isPresent()) {
            Patron patron = result2.get();
            newLoan.setPatron(patron);
        }
        loanRepository.save(newLoan);
        return "redirect:";
    }
}
