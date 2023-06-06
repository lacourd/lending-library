package lacourd.lendinglibrary.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class GameExpansion extends AbstractEntity{

    @ManyToOne
    private Game baseGame;

    @NotBlank(message = "Game name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 70 characters.")
    private String name;

    @ManyToOne
    @NotNull(message = "Storage location is required")
    private StorageLocation storageLocation;

    @OneToOne(mappedBy = "gameCheckedOut")
    private Loan loan;

    public GameExpansion(String name, Game baseGame, StorageLocation storageLocation) {
        this.name = name;
        this.baseGame = baseGame;
        this.storageLocation = storageLocation;
    }

    public GameExpansion(){}

}
