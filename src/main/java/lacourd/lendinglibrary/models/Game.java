package lacourd.lendinglibrary.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Game extends AbstractEntity{

    @NotBlank(message = "Game name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 70 characters.")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private GameDetails gameDetails;

    @ManyToOne
    @NotNull(message = "Storage location is required")
    private StorageLocation storageLocation;

    @OneToMany
    private GameExpansion gameExpansion;

    public Game(String name, StorageLocation storageLocation) {
        this.name = name;
        this.storageLocation = storageLocation;
    }

    public Game() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameDetails getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(GameDetails gameDetails) {
        this.gameDetails = gameDetails;
    }

    public StorageLocation getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(StorageLocation storageLocation) {
        this.storageLocation = storageLocation;
    }

    public GameExpansion getGameExpansion() {
        return gameExpansion;
    }

    public void setGameExpansion(GameExpansion gameExpansion) {
        this.gameExpansion = gameExpansion;
    }

    @Override
    public String toString() {
        return name;
    }
}
