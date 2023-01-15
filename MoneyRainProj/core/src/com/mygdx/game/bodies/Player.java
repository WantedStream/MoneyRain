package com.mygdx.game.bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.interfaces.ICoin;
import com.mygdx.game.interfaces.ICreature;
import com.mygdx.game.interfaces.IPhysical;
import com.mygdx.game.managers.GameStateManager;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.utils.Constants.GROUP_PLAYER;
import static com.mygdx.game.utils.Constants.PIXLE_PER_METER;

public class Player implements ICreature {
    private Body body;
    private float width;
    private float height;
    private float health;
    private GameStateManager gameStateManager;
     private World world;
     private int score;

    public Player(World world, String id, float x, float y, GameStateManager gameStateManager){
        this.health=3;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type=BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x,y);
        bodyDef.fixedRotation=true;
        this.body=world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        this.width=32/2/PIXLE_PER_METER;
        this.height=32/2/PIXLE_PER_METER;
        shape.setAsBox(this.width,this.height);
        this.body.createFixture(shape,1.0f).setUserData(this);
        shape.dispose();
        this.gameStateManager=gameStateManager;
        this.world=world;
    }
    public Body getBody(){
        return this.body;
    }
    public float getWidth(){
        return this.width;
    }
    public float getHeight(){
        return this.height;
    }
    public void hit(){
        System.out.println("hit!");
    }

    @Override
    public void onCollision(IPhysical physical) {
        if(physical instanceof ICoin){
            this.score+=((ICoin) physical).getScore();
        }
    }

    @Override
    public float getDamage() {
        return 0;
    }

    @Override
    public void decHealth(float damage) {
        this.health-=damage;
        if(this.health<0){

            this.gameStateManager.setState(GameStateManager.State.GAMEOVER);

        }
    }
    public void render(Batch batch, Texture playerTexture){
        float width=this.width*PIXLE_PER_METER*2;
        float height =this.height*PIXLE_PER_METER*2;
        batch.draw(playerTexture,this.body.getPosition().x*PIXLE_PER_METER-(width/2),this.body.getPosition().y*PIXLE_PER_METER-(height/2),width,height);
    }
    public void update(){

    }

    public int getScore() {
        return this.score;
    }
}
