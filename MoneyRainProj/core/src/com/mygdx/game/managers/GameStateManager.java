package com.mygdx.game.managers;

import com.mygdx.game.MoneyRainClass;
import com.mygdx.game.states.GameOverState;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.PlayState;
import com.mygdx.game.states.SplashState;

import java.util.Stack;

public class GameStateManager {
    private final MoneyRainClass application;
    private Stack<GameState> states;
    public enum State{
        SPLASH,
        PLAY,
        GAMEOVER
    }
    public GameStateManager(final MoneyRainClass app){
        this.application=app;
        this.states=new Stack<GameState>();
        this.setState(State.PLAY);
    }

    public MoneyRainClass application(){
        return this.application;
    }
    public void update(float delta){
        this.states.peek().update(delta);
    }
    public void render(){
        this.states.peek().render();
    }
    public void dispose(){
        for (GameState gameState: this.states){
            gameState.dispose();
        }
        this.states.clear();
    }
    public void resize(int width,int height){
        this.states.peek().resize(width,height);
    }
    public void setState(State state){
        if(this.states.size() >=1){
            this.states.pop().dispose();
        }
        this.states.push(getState(state));


    }
    private GameState getState(State state){
       switch (state){

           case SPLASH : return new SplashState(this);
           case PLAY: return new PlayState(this);
           case GAMEOVER: return new GameOverState(this);
       }
           return null;
    }
    public MoneyRainClass getApp(){
        return this.application();
    }
}
