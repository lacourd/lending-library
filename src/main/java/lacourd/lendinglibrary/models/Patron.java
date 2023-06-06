package lacourd.lendinglibrary.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Patron extends AbstractEntity{

    private String firstName;

    private String lastName;

    @OneToOne(mappedBy = "patron")
    private Loan loan;

    public Patron() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
