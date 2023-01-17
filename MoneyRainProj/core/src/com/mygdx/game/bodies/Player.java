package com.mygdx.game.bodies;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.interfaces.CoinTemplate;
import com.mygdx.game.interfaces.ICreature;
import com.mygdx.game.interfaces.IPhysical;
import com.mygdx.game.interfaces.PowerUp;
import com.mygdx.game.managers.GameStateManager;
import com.mygdx.game.utils.SunchronizedQueue;

import java.util.Map;

import static com.mygdx.game.utils.Constants.PIXLE_PER_METER;
import static com.mygdx.game.utils.Resources.SOUNDS.NOPOWERUPS_SOUND;
import static com.mygdx.game.utils.Resources.SOUNDS.POWERUP_SOUND;

public class Player implements ICreature {
    private Body body;
    private float width;
    private float height;
    private float health;
    private GameStateManager gameStateManager;
     private World world;
     private int score;
     private int scoreMult=1;
     private SunchronizedQueue<PowerUp> powerUpQueue = new SunchronizedQueue<PowerUp>();
     private PowerUp powerUp= null;
     private float powerUpTime=0;

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

        this.gameStateManager.resetResults();
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
        if(physical instanceof CoinTemplate){
            updateMoneyRes((CoinTemplate) physical);
        }
        if(physical instanceof PowerUp){
            addPowerUp((PowerUp)physical);
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

    public void update(float delta ){
        System.out.println(this.health);
        if(this.powerUp!=null){
            if(this.powerUpTime>0){
                this.powerUp.continuePowerEffect(this);
                this.powerUpTime-=delta;
            }
            else{
                this.powerUp=null;
                this.powerUpTime=0;
                setScoreMult(1);
            }
        }

    }
    private void updateMoneyRes(CoinTemplate coin){
        setScore(coin.getScore()*this.scoreMult);
        this.gameStateManager.updateResults(coin.getClass().getSimpleName());

    }
    private void setScore(int score){
        this.score+=score;
    }
    public int getScore() {
        return this.score;
    }
    private void addPowerUp(PowerUp p){
        this.powerUpQueue.add(p);
    }
    public void pollPowerUp(){
        if(this.powerUpQueue.isEmpty()){
            NOPOWERUPS_SOUND.play();
            return;
        }


        this.powerUp=this.powerUpQueue.poll();
        this.powerUpTime=this.powerUp.getTime();
        POWERUP_SOUND.play();
    }
    public void setScoreMult(int mult){
        this.scoreMult=mult;
    }
    public String getScoreMult(){
        if(this.scoreMult<=1)
            return "";
       return "X"+this.scoreMult;
    }
    public int getHealth(){
        return (int)this.health;
    }
}
