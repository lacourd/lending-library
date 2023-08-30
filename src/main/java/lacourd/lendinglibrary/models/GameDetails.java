package lacourd.lendinglibrary.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class GameDetails extends AbstractEntity{

    @Size(max=500, message = "Description too long!")
    private String description;

    private String longDescription;

    @NotNull
    @Min(1)
    private int minPlayers;

    @NotNull
    @Max(12)
    private int maxPlayers;

    private boolean isAvailable = true;

    private Integer currentLoan = 0;

    @OneToOne(mappedBy = "gameDetails")
    private Game game;

    public GameDetails(String description, int minPlayers, int maxPlayers) {
        this.description = description;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    public GameDetails() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Integer getCurrentLoan() {
        return currentLoan;
    }

    public void setCurrentLoan(Integer currentLoan) {
        this.currentLoan = currentLoan;
    }
}
