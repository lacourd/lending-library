package lacourd.lendinglibrary.controllers;

import lacourd.lendinglibrary.data.GameRepository;
import lacourd.lendinglibrary.data.LoanRepository;
import lacourd.lendinglibrary.data.PatronRepository;
import lacourd.lendinglibrary.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
        model.addAttribute("title", "Active Checkouts");
        model.addAttribute("loans", loanRepository.findAll());
        return "checkouts/index";
    }

    @GetMapping("archive")
    public String displayArchive(Model model) {
        model.addAttribute("title", "Past Checkouts");
        model.addAttribute("loans", loanRepository.findAll());
        return "checkouts/archive";
    }

    @GetMapping("new")
    public String renderCheckoutForm(Model model) {
        model.addAttribute("title", "New Checkout");
        model.addAttribute(new Loan());
        model.addAttribute("games", gameRepository.findAll(Sort.by("name")));
        model.addAttribute("patrons", patronRepository.findAll());
        return "checkouts/new";
    }

    @GetMapping("new/{gameId}")
    public String renderCheckoutForm(Model model, @PathVariable int gameId) {
        Optional<Game> result = gameRepository.findById(gameId);
        Game game = result.get();
        model.addAttribute("title", "Checkout " + game.getName());
        model.addAttribute(new Loan());
        model.addAttribute("game", game);
        model.addAttribute("patrons", patronRepository.findAll());
        return "checkouts/new";
    }

    @PostMapping("new")
    public String processCheckoutForm(@ModelAttribute @Valid Loan newLoan, Errors errors, Model model,
                                        @RequestParam int gameId, @RequestParam int patronId) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "New Checkout");
            model.addAttribute("games", gameRepository.findAll(Sort.by("name")));
            model.addAttribute("patrons", patronRepository.findAll());
            return "checkouts/new";
        }
        Optional<Game> result1 = gameRepository.findById(gameId);
        if (result1.isPresent()) {
            Game checkedOutGame = result1.get();
            GameDetails info = checkedOutGame.getGameDetails();
//          I was able to eliminate checked out items from the dropdown menu so I don't think I need this code
//            if (info.isAvailable()==false) {
//                errors.reject("game.unavailable", "Game is already checked out!");
//                model.addAttribute("title", "New Checkout");
//                model.addAttribute("games", gameRepository.findAll(Sort.by("name")));
//                model.addAttribute("patrons", patronRepository.findAll());
//                return "checkouts/new";
//            }
            info.setAvailable(false);
            info.setCurrentLoan(newLoan.getId());

//           is this necessary now that I reimplemented mappedBy?
//            checkedOutGame.setLoan(newLoan);
            newLoan.setGameCheckedOut(checkedOutGame);
            gameRepository.save(checkedOutGame);
        }
        Optional<Patron> result2 = patronRepository.findById(patronId);
        if (result2.isPresent()) {
            Patron patron = result2.get();
            newLoan.setPatron(patron);
        }
        loanRepository.save(newLoan);
        newLoan.getGameCheckedOut().getGameDetails().setCurrentLoan(newLoan.getId());
        gameRepository.save(newLoan.getGameCheckedOut());
        return "redirect:";
    }


    @GetMapping("return/{loanId}")
    public String displayReturnForm(Model model, @PathVariable int loanId) {

        Optional<Loan> result = loanRepository.findById(loanId);
        if (!result.isEmpty()){
            Loan loan = result.get();
            model.addAttribute("loan", loan);
            model.addAttribute("title", "Return Item: " + loan.getGameCheckedOut().getName());
        }   else {
            model.addAttribute("loans", loanRepository.findAll());
            model.addAttribute("title", "Return Item");
        }
        return "checkouts/return";
    }

    @GetMapping("return")
    public String displayReturnForm(Model model) {

            model.addAttribute("loans", loanRepository.findAll());
            model.addAttribute("title", "Return Item");

        return "checkouts/return";
    }

    @PostMapping("return")
    public String processReturnForm(@RequestParam int loanId, @RequestParam String checkInDate) {
        Optional<Loan> result = loanRepository.findById(loanId);
        if (result.isPresent()) {
            Loan loanToReturn = result.get();
            loanToReturn.setCheckInDate(checkInDate);
            Game returnedGame = loanToReturn.getGameCheckedOut();
            returnedGame.getGameDetails().setCurrentLoan(0);
            returnedGame.getGameDetails().setAvailable(true);
            loanRepository.save(loanToReturn);
            gameRepository.save(returnedGame);
        }

        return "redirect:";
    }
}
