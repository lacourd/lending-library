package org.lacourd.lendinglibrary.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class GameDetails extends AbstractEntity{

    @Size(max=500, message = "Description too long!")
    private String description;

    @NotBlank(message = "Storage location is required")
    private String storageLocation;

    @NotNull
    private int minPlayers;

    @NotNull
    private int maxPlayers;

    @OneToOne(mappedBy = "gameDetails")
    private Game game;

    public GameDetails(String description, String storageLocation, int minPlayers, int maxPlayers) {
        this.description = description;
        this.storageLocation = storageLocation;
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

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
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
}
