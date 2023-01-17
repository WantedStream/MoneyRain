package com.mygdx.game.bodies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.interfaces.CoinTemplate;
import com.mygdx.game.states.GameState;
import com.mygdx.game.utils.SunchronizedQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import static com.mygdx.game.utils.Constants.CAMERA_POS_X;
import static com.mygdx.game.utils.Constants.CAMERA_POS_Y;
import static com.mygdx.game.utils.Resources.TEXTURES.REC_TEXTURE;

public class MoneyRain {
    private final static float time=2f;
    private float acc=time;
    private SunchronizedQueue<CoinTemplate> moneyQueue;
    private List<CoinTemplate> fallingMoney;
    private World world;
    public MoneyRain(World world, GameState state){
        this.moneyQueue=new SunchronizedQueue<CoinTemplate>();
        this.fallingMoney=new ArrayList<CoinTemplate>();
        this.world=world;
        int y=1;

        this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
       // this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
        //this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));

         this.moneyQueue.add((CoinTemplate) new Gold(this.world,0,50*y,1,1));
       // this.moneyQueue.add((CoinTemplate) new Gold(this.world,0,50*y,1,1));
       /// this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
       // this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
        //this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
       // this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
       // this.moneyQueue.add((CoinTemplate) new Gold(this.world,0,50*y,1,1));
       // this.moneyQueue.add((CoinTemplate) new Gold(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new MultByThreePow(this.world,0,50*y,1,1));
       // this.moneyQueue.add((CoinTemplate) new MultByThreePow(this.world,0,50*y,1,1));

       // this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
      //  this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new Diamond(this.world,0,50*y,1,1));
       // this.moneyQueue.add((CoinTemplate) new Diamond(this.world,0,50*y,1,1));
       // this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
       // this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
      //  this.moneyQueue.add((CoinTemplate) new Gold(this.world,0,50*y,1,1));
       this.moneyQueue.add((CoinTemplate) new Heart(this.world,0,50*y,1,1));
       this.moneyQueue.add((CoinTemplate) new Trash(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new Cheese(this.world,0,50*y,1,1));
       this.moneyQueue.add((CoinTemplate) new MultByTwoPow(this.world,0,50*y,1,1));
      this.moneyQueue.add((CoinTemplate) new Crown(this.world,0,50*y,1,1));

      //  this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
       // this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
       // this.moneyQueue.add((CoinTemplate) new MultByTwoPow(this.world,0,50*y,1,1));

       // this.moneyQueue.add((CoinTemplate) new MultByTwoPow(this.world,0,50*y,1,1));

    }
    public void addCoinToWorld(){
        if(this.moneyQueue.isEmpty())return;

        int rndnumX=new Random().nextInt(13,28);
        int rndnumY=40;
        CoinTemplate money = (CoinTemplate) this.moneyQueue.poll();
        money.getBody().setTransform(rndnumX,rndnumY,money.getBody().getAngle());
        money.setSpeed(-15);
        this.fallingMoney.add(money);
    }
    public void iterCoins(){
        ListIterator<CoinTemplate> iterator = this.fallingMoney.listIterator();
        while(iterator.hasNext()) {
            CoinTemplate c = iterator.next();
            if(c.isDead()){
                c.setAlive();
                addCoinToQueue(c,iterator);
                int rndnumX=-500;
                int rndnumY=-500;
                c.getBody().setTransform(rndnumX,rndnumY,c.getBody().getAngle());
            }
            c.update();

        }
    }

    public void update(float delta){
        acc+=delta;
        if(acc>=time){
            addCoinToWorld();
            acc=0;
        }

        iterCoins();
    }

    public void draw(SpriteBatch batch) {
        ListIterator<CoinTemplate> iterator = this.fallingMoney.listIterator();
        while(iterator.hasNext()) {
            CoinTemplate c = iterator.next();
            c.draw(batch);
        }
    }
    public void addCoinToQueue(CoinTemplate c, ListIterator<CoinTemplate> iter){
        this.moneyQueue.add(c);
        iter.remove();
    }

}
