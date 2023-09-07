package lacourd.lendinglibrary.data;

import lacourd.lendinglibrary.models.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {
}
