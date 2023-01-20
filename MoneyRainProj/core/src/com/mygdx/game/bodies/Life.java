package com.mygdx.game.bodies;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.interfaces.CoinTemplate;
import com.mygdx.game.interfaces.IPhysical;

import static com.mygdx.game.utils.Constants.PIXLE_PER_METER;
import static com.mygdx.game.utils.Resources.SOUNDS.HEART_SOUND;
import static com.mygdx.game.utils.Resources.TEXTURES.HEART_TEXTURE;

public class Life extends CoinTemplate {
    private short score;
    private Body body;
    private float width;
    private float height;
    private float damage;
    private float speed;
    public Life(World world, float x, float y, float w, float h){
        super(HEART_TEXTURE);
        this.body=makeBody(x,y,world);
        this.width=w*32/2/PIXLE_PER_METER;
        this.height=h*32/2/PIXLE_PER_METER;
        applyShape(this.body,this.width,this.height,1.0f,this);
        this.score=5;
        this.damage=-1;
    }
    @Override
    public short getScore() {
        return this.score;
    }

    @Override
    public void onCollision(IPhysical physical) {
        super.defaultOnCollision(physical);
        if(physical instanceof Player){
            ((Player) physical).decHealth(damage);
            HEART_SOUND.play();
        }
    }

    @Override
    public void update(){
        super.defaultUpdate(this.body);
        this.body.setLinearVelocity(0,this.speed);
    }

    @Override
    public void draw(Batch batch) {
        super.defaultCoinDrawer(batch,this.body,this.width,this.height);
    }

    @Override
    public void setSpeed(float speed) {
        this.speed=speed;
    }

    @Override
    public Body getBody() {
        return this.body;
    }
}
