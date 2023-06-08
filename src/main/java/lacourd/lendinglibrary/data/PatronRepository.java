package lacourd.lendinglibrary.data;

import lacourd.lendinglibrary.models.Patron;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatronRepository extends PagingAndSortingRepository<Patron, Integer> {
}
