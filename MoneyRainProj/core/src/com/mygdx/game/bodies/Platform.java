package com.mygdx.game.bodies;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.interfaces.IPhysical;

import static com.mygdx.game.utils.Constants.PIXLE_PER_METER;

public class Platform implements IPhysical {
    Body body;
    public Platform(World world,float x,float y,float width,int height,boolean isStatic){
        BodyDef bodyDef = new BodyDef();

        if(isStatic)
            bodyDef.type=BodyDef.BodyType.StaticBody;
        else
            bodyDef.type=BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(x,y);
        bodyDef.fixedRotation=true;
        this.body=world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width*32/2/PIXLE_PER_METER,height*32/2/PIXLE_PER_METER);
        this.body.createFixture(shape,1.0f).setUserData(this);
        shape.dispose();
    }

    @Override
    public void onCollision(IPhysical physical) {

    }

    @Override
    public Body getBody() {
        return null;
    }
}
