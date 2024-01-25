package lacourd.lendinglibrary.models;

import lacourd.lendinglibrary.models.bggapi.BGGGameData;

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

    private String bggId;

    @NotNull
    @Min(1)
    private int minPlayers;

    @NotNull
    @Max(12)
    private int maxPlayers;

    private String coverImage;

    private String thumbnailUrl;

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

    public void fetchCoverImage(BGGApiService bggApiService, String searchableName) {
        this.coverImage = bggApiService.searchGameAndGetCoverImage(searchableName);
    }

    public void fetchApiData(BGGApiService bggApiService, String searchableName) {
        BGGGameData gameData = bggApiService.searchGameAndGetData(searchableName);

        this.coverImage = gameData.getCoverImageUrl();
        this.thumbnailUrl = gameData.getThumbnailUrl();
        this.bggId = gameData.getGameId();

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
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

    public String getCoverImage() {
        return coverImage;
    }

    public String getBggId() {
        return bggId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
