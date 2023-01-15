package com.mygdx.game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputHandler {

    public void inputUpdate(float delta){
        int horizontalForce=0;
        int verticalForce=0;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            horizontalForce -=1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            horizontalForce +=1;
        }

        player.getBody().setLinearVelocity(horizontalForce*50,0f);

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            // player.doAttack();
        }
    }

}
