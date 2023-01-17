package com.mygdx.game.bodies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.interfaces.CoinTemplate;
import com.mygdx.game.states.GameState;
import com.mygdx.game.utils.SunchronizedQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MoneyRain {
    private final static float time=0.5f;
    private float acc=time;
    private SunchronizedQueue<CoinTemplate> moneyQueue;
    private List<CoinTemplate> fallingMoney;
    private World world;
    public MoneyRain(World world, GameState state){
        this.moneyQueue=new SunchronizedQueue<CoinTemplate>();
        this.fallingMoney=new ArrayList<CoinTemplate>();
        this.world=world;
        int y=1;

           this.moneyQueue.add((CoinTemplate) new Gold(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new Trash(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new Gold(this.world,0,50*y,1,1));
       this.moneyQueue.add((CoinTemplate) new Trash(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new Gold(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new Diamond(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
       this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new Heart(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
       this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new Penny(this.world,0,50*y,1,1));
       this.moneyQueue.add((CoinTemplate) new Cheese(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new Crown(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new Crown(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new MultByTwoPow(this.world,0,50*y,1,1));
        this.moneyQueue.add((CoinTemplate) new MultByTwoPow(this.world,0,50*y,1,1));


    }
    public void addCoinToWorld(){
        if(this.moneyQueue.isEmpty())return;

        int rndnumX=new Random().nextInt(13,28);
        int rndnumY=40;
        CoinTemplate money = (CoinTemplate) this.moneyQueue.poll();
        money.getBody().setTransform(rndnumX,rndnumY,money.getBody().getAngle());
        this.fallingMoney.add(money);
    }
    public void iterCoins(){
        ListIterator<CoinTemplate> iterator = this.fallingMoney.listIterator();
        while(iterator.hasNext()) {
            CoinTemplate c = iterator.next();
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
}
