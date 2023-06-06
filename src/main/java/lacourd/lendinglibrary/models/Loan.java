package lacourd.lendinglibrary.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class Loan extends AbstractEntity{

    private LocalDate checkOutDate;

    private LocalDate checkInDate;

    @OneToOne
    private Game gameCheckedOut;

    @OneToOne
    private GameExpansion expansionCheckedOut;

    @OneToOne
    private Patron patron;

    public Loan() {}

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Game getGameCheckedOut() {
        return gameCheckedOut;
    }

    public void setGameCheckedOut(Game gameCheckedOut) {
        this.gameCheckedOut = gameCheckedOut;
    }

    public GameExpansion getExpansionCheckedOut() {
        return expansionCheckedOut;
    }

    public void setExpansionCheckedOut(GameExpansion expansionCheckedOut) {
        this.expansionCheckedOut = expansionCheckedOut;
    }
}
