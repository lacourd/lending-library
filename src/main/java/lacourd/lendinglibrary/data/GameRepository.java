package lacourd.lendinglibrary.data;

import lacourd.lendinglibrary.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameRepository extends PagingAndSortingRepository<Game, Integer> {

}
