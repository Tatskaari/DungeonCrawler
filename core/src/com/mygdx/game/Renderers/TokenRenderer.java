package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Tokens.Token;
import com.mygdx.game.Tokens.Tokens;

public class TokenRenderer extends Renderer {
    private final Tokens tokens;
    public TokenRenderer(Tokens tokens){
        this.tokens = tokens;
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        for(Token token : tokens.getTokens()){
            token.render(delta, batch);
        }
    }
}
