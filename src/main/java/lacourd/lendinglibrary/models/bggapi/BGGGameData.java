package lacourd.lendinglibrary.models.bggapi;

public class BGGGameData {

    private String gameId;

    private String coverImageUrl;

    private String thumbnailUrl;

    private String description;

    public BGGGameData(String gameId, String coverImageUrl, String thumbnailUrl, String description) {
        this.gameId = gameId;
        this.coverImageUrl = coverImageUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
