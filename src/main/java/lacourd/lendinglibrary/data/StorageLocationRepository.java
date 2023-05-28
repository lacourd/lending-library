package lacourd.lendinglibrary.data;

import lacourd.lendinglibrary.models.StorageLocation;
import org.springframework.data.repository.CrudRepository;

public interface StorageLocationRepository extends CrudRepository<StorageLocation, Integer> {
}
