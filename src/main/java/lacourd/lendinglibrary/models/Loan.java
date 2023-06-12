package lacourd.lendinglibrary.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan extends AbstractEntity{

    private String checkOutDate;

    private String checkInDate;

    @ManyToOne //(mappedBy = "loan")
    private Game gameCheckedOut;

    @OneToOne
    private GameExpansion expansionCheckedOut;

    @ManyToOne
    private Patron patron;

    public Loan() {}

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
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
