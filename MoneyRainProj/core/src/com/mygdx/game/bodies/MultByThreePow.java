package com.mygdx.game.bodies;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.interfaces.CoinTemplate;
import com.mygdx.game.interfaces.IPhysical;
import com.mygdx.game.interfaces.PowerUp;

import static com.mygdx.game.utils.Constants.PIXLE_PER_METER;
import static com.mygdx.game.utils.Resources.SOUNDS.X2_SOUND;
import static com.mygdx.game.utils.Resources.SOUNDS.X3_SOUND;
import static com.mygdx.game.utils.Resources.TEXTURES.X2_TEXTURE;
import static com.mygdx.game.utils.Resources.TEXTURES.X3_TEXTURE;

public class MultByThreePow  extends CoinTemplate implements PowerUp {
    private short score;
    private Body body;
    private float width;
    private float height;
    private float time;
    private int mult=3;
    private float speed;
    public MultByThreePow(World world, float x, float y, float w, float h){
        super(X3_TEXTURE);
        this.body=makeBody(x,y,world);
        this.width=w*32/2/PIXLE_PER_METER;
        this.height=h*32/2/PIXLE_PER_METER;
        applyShape(this.body,this.width,this.height,1.0f,this);
        this.score=10;
        this.time=10;
    }
    @Override
    public short getScore() {
        return this.score;
    }

    @Override
    public void onCollision(IPhysical physical) {

        super.defaultOnCollision(physical);
        if(physical instanceof Player)
            X3_SOUND.play();
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

    public void continuePowerEffect(Player p) {
        p.setScoreMult(mult);
    }
    public float getTime(){
        return this.time;
    }
}
