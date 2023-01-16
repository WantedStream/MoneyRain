package com.mygdx.game.bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.interfaces.CoinTemplate;
import com.mygdx.game.interfaces.IPhysical;
import com.mygdx.game.states.GameState;

import java.util.Random;

import static com.mygdx.game.utils.Constants.PIXLE_PER_METER;
import static com.mygdx.game.utils.Resources.SOUNDS.GOLD_SOUND;
import static com.mygdx.game.utils.Resources.TEXTURES.GOLD_TEXTURE;

public class Gold extends CoinTemplate {
    private short score;
    private Body body;
    private float width;
    private float height;
    public Gold(World world, float x, float y, float w, float h){
        super(GOLD_TEXTURE);
        this.body=makeBody(x,y,world);
        this.width=w*32/2/PIXLE_PER_METER;
        this.height=h*32/2/PIXLE_PER_METER;
        applyShape(this.body,this.width,this.height,1.0f,this);
        this.score=3;
    }
    @Override
    public short getScore() {
        return this.score;
    }

    @Override
    public void onCollision(IPhysical physical) {

        super.defaultOnCollision(physical);
        if(physical instanceof Player)
            GOLD_SOUND.play();
    }

    @Override
    public void update(){
        super.defaultUpdate(this.body);
        this.body.setLinearVelocity(0,-9.8f);
    }

    @Override
    public void draw(Batch batch) {
       super.defaultCoinDrawer(batch,this.body,this.width,this.height);
    }

    @Override
    public Body getBody() {
        return this.body;
    }
}
