package lacourd.lendinglibrary.data;

import lacourd.lendinglibrary.models.GameDetails;
import org.springframework.data.repository.CrudRepository;

public interface GameDetailsRepository extends CrudRepository<GameDetails, Integer> {
}
