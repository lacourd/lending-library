package lacourd.lendinglibrary.models;

import javax.persistence.OneToOne;
import java.time.LocalDate;

public class Loan extends AbstractEntity{

    private LocalDate checkOutDate;

    private LocalDate checkInDate;

    @OneToOne
    private Game gameCheckedOut;
}
