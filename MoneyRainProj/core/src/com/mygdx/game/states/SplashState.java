package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.managers.GameStateManager;

public class SplashState extends GameState{

    float acc=0f;
    Texture texture;
    public SplashState(GameStateManager gameStateManager){
        super(gameStateManager);
    }
    @Override
    public void update(float delta) {
        acc+=delta;
        if(acc>=0.5){
            gameStateManager.setState(GameStateManager.State.PLAY);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose() {

    }
}
