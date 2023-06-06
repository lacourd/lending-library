package lacourd.lendinglibrary.data;

import lacourd.lendinglibrary.models.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Integer> {
}
