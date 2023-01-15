package com.mygdx.game.bodies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.interfaces.ICoin;
import com.mygdx.game.states.GameState;
import com.mygdx.game.utils.SunchronizedQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MoneyRain {
    private final static float time=0.5f;
    private float acc=time;
    private SunchronizedQueue<ICoin> moneyQueue;
    private List<ICoin> fallingMoney;
    private World world;
    public MoneyRain(World world, GameState state){
        this.moneyQueue=new SunchronizedQueue<ICoin>();
        this.fallingMoney=new ArrayList<ICoin>();
        this.world=world;
        int y=1;

           this.moneyQueue.add((ICoin) new Gold(this.world,state,0,50*y,1,1));
        this.moneyQueue.add((ICoin) new Gold(this.world,state,0,50*y,1,1));
        this.moneyQueue.add((ICoin) new Gold(this.world,state,0,50*y,1,1));
        this.moneyQueue.add((ICoin) new Gold(this.world,state,0,50*y,1,1));
        this.moneyQueue.add((ICoin) new Gold(this.world,state,0,50*y,1,1));

    }
    public void addCoinToWorld(){
        if(this.moneyQueue.isEmpty())return;

        int rndnumX=new Random().nextInt(13,19);
        int rndnumY=19;
        ICoin money = (ICoin) this.moneyQueue.poll();
        money.getBody().setTransform(rndnumX,rndnumY,money.getBody().getAngle());
        this.fallingMoney.add(money);
    }
    public void iterCoins(){
        ListIterator<ICoin> iterator = this.fallingMoney.listIterator();
        while(iterator.hasNext()) {
            ICoin c = iterator.next();
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
}
