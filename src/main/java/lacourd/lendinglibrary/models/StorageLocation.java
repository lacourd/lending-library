package lacourd.lendinglibrary.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StorageLocation extends AbstractEntity{
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;
    @OneToMany(mappedBy = "storageLocation")
    private final List<Game> games = new ArrayList<>();

    @OneToMany(mappedBy = "storageLocation")
    private final List<GameExpansion> expansions = new ArrayList<>();

    public StorageLocation(@Size(min=3, message="Name must be at least 3 characters long") String name) {
        this.name=name;
    }

    public StorageLocation() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() {
        return games;
    }

    @Override
    public String toString() {
        return name;
    }

}
