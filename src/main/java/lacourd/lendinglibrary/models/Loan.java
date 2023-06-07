package lacourd.lendinglibrary.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class Loan extends AbstractEntity{

    private String checkOutDate;

    private LocalDate checkInDate;

    @OneToOne
    private Game gameCheckedOut;

    @OneToOne
    private GameExpansion expansionCheckedOut;

    @OneToOne(cascade = CascadeType.ALL)
    private Patron patron;

    public Loan() {}

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
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

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }
}
