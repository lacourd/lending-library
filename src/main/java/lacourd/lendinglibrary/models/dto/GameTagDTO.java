package lacourd.lendinglibrary.models.dto;

import lacourd.lendinglibrary.models.Game;
import lacourd.lendinglibrary.models.Tag;

import javax.validation.constraints.NotNull;

public class GameTagDTO {

    @NotNull
    private Game game;

    @NotNull
    private Tag tag;

    public GameTagDTO() {}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
