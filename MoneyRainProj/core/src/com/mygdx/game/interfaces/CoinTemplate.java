package com.mygdx.game.interfaces;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.bodies.Platform;
import com.mygdx.game.bodies.Player;

import java.util.Random;

import static com.mygdx.game.utils.Constants.PIXLE_PER_METER;

public abstract class CoinTemplate implements IPhysical {
    private Texture texture;
    protected boolean died=false;

    public CoinTemplate(Texture texture) {
        this.texture=texture;
    }

    public abstract short getScore();
    public abstract void update();
     protected void defaultUpdate(Body body){

     }
     public Texture getTexture(){
         return this.texture;
     }
     public abstract void draw(Batch batch);
     protected void defaultCoinDrawer(Batch batch,Body body,float width,float height){
        batch.draw(this.texture,body.getPosition().x*PIXLE_PER_METER-(width*PIXLE_PER_METER),body.getPosition().y*PIXLE_PER_METER-(height*PIXLE_PER_METER),width*PIXLE_PER_METER*2,height*PIXLE_PER_METER*2);

     }

     protected Body makeBody(float x, float y, World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type=BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x,y);
        bodyDef.fixedRotation=true;
        return world.createBody(bodyDef);
     }
     protected void applyShape(Body body,float width,float height,float density,Object userData){
         PolygonShape shape = new PolygonShape();
         shape.setAsBox(width,height);
         body.createFixture(shape,1.0f).setUserData(userData);
         shape.dispose();
     }
     protected void defaultOnCollision(IPhysical physical){
         if(physical instanceof Platform)
             died=true;
         if(physical instanceof Player){
             died=true;
         }
     }
     public boolean isDead(){
        return this.died;
     }
     public void setAlive(){
         this.died=false;
     }

    public abstract void setSpeed(float speed);
}
