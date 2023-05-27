package org.lacourd.lendinglibrary.data;

import org.lacourd.lendinglibrary.models.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {

}
