package com.mygdx.game.Tokens;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Renderers.Renderer;
import com.mygdx.game.Renderers.TokenRenderer;

public class Tokens {
    private Array<Token> tokens;
    public Renderer renderer;

    public Tokens(){
        tokens = new Array<Token>();
        this.renderer = new TokenRenderer(this);
    }

    public Array<Token> getTokens() {
        updateTokens();
        return tokens;
    }

    public void addToken(Token token){
        tokens.add(token);
    }

    public void updateTokens(){
        for(Token token : tokens ){
            if(!token.isAlive()){
                tokens.removeValue(token, true);
            }
        }
    }
}
