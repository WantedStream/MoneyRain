package com.mygdx.game.bodies;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.interfaces.ICoin;
import com.mygdx.game.interfaces.IPhysical;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.PlayState;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

import static com.mygdx.game.utils.Constants.PIXLE_PER_METER;

public class Gold implements ICoin {
    private short score;
    private Body body;
    private World world;
    private float width;
    private float height;
    private GameState currentgameState;
    private boolean died=false;
    public Gold(World world, GameState gameState, float x, float y, float w, float h){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type=BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x,y);
        bodyDef.fixedRotation=true;
        this.body=world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        this.width=w*32/2/PIXLE_PER_METER;
        this.height=h*32/2/PIXLE_PER_METER;
        shape.setAsBox(this.width,this.height);
        this.body.createFixture(shape,1.0f).setUserData(this);
        shape.dispose();
        this.world=world;
        this.score=3;
        this.currentgameState=gameState;
    }
    @Override
    public short getScore() {
        return this.score;
    }

    @Override
    public void respawn() {

    }

    @Override
    public void onCollision(IPhysical physical) {
      //  if(physical!=null){
        //    if(physical.getBody().getUserData().equals("Ground"))

        //}
        if(physical instanceof Platform)
            died=true;
    }

    @Override
    public void update(){
        System.out.println(this);
        if(died){
            died=false;
            int rndnumX=new Random().nextInt(13,19);
            int rndnumY=19;
            this.body.setTransform(rndnumX,rndnumY,this.body.getAngle());

        }
    }
    @Override
    public Body getBody() {
        return this.body;
    }
}
