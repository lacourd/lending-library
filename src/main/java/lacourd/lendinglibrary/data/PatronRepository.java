package lacourd.lendinglibrary.data;

import lacourd.lendinglibrary.models.Patron;
import org.springframework.data.repository.CrudRepository;

public interface PatronRepository extends CrudRepository<Patron, Integer> {
}
