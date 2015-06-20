package com.mygdx.game.UserInterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class DeveloperInfo extends CenterScreenWindow{
    Cell<TextArea> textAreaCell;
    private final TextArea stats;
    public DeveloperInfo(Skin skin) {
        super("Dev Info", skin);
        stats = new TextArea("", skin);
        stats.setDisabled(true);

        textAreaCell = this.add(stats);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        textAreaCell.width(getWidth()).height(getHeight()-getPadTop());

        super.draw(batch, parentAlpha);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible){
            setStatText();
        }
    }

    private void setStatText(){
        PlayerCharacterEntity player = PlayerCharacterEntity.getInstance();
        StringBuilder statsTextBuilder = new StringBuilder();

        // Health
        statsTextBuilder.append("Health: ");
        statsTextBuilder.append(player.statsHandler.getHealth());
        statsTextBuilder.append("/");
        statsTextBuilder.append(player.statsHandler.getMaxHealth());
        statsTextBuilder.append("\n");

        // Attack Rating
        statsTextBuilder.append("Weapon Rating: ");
        statsTextBuilder.append(player.inventory.getAttackRating());
        statsTextBuilder.append("\n");

        // Defence Rating
        statsTextBuilder.append("Armour Rating: ");
        statsTextBuilder.append(player.inventory.getArmourRating());
        statsTextBuilder.append("\n");

        stats.setText(statsTextBuilder.toString());
    }
}
