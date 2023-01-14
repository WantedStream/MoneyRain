package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MoneyRainClass;
import com.mygdx.game.managers.GameStateManager;

public abstract class GameState {
    protected GameStateManager gameStateManager;
    protected MoneyRainClass appplication;

    protected SpriteBatch batch;
    protected OrthographicCamera camera;

    protected GameState(GameStateManager gameStateManager){
        this.gameStateManager=gameStateManager;
        this.appplication=gameStateManager.application();

        batch=appplication.getBatch();
       camera=appplication.getCamera();

    }
    public void resize(int width,int height){
        camera.setToOrtho(false,width,height);
    }
    public abstract void update(float delta);
    public abstract void render();
    public abstract void dispose();

}
