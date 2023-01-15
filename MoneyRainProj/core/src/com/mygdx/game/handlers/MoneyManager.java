package com.mygdx.game.handlers;

import com.mygdx.game.bodies.Gold;
import com.mygdx.game.interfaces.ICoin;

import java.util.*;

public class MoneyManager {
    private Queue<ICoin> moneyQueue;
    private List<ICoin> fallingMoney;

    public void iterCoins(){
        ListIterator<ICoin> iterator = this.fallingMoney.listIterator();
        if(!this.fallingMoney.isEmpty())
            System.out.print(Arrays.toString(this.fallingMoney.toArray()));
        while(iterator.hasNext()) {
            ICoin c = iterator.next();
            iterator.remove();
            c.update();
        }
    }
    public void addCoinToWorld(){

        int rndnumX=new Random().nextInt(13,19);
        int rndnumY=19;
        if(this.moneyQueue.isEmpty())
            for (int i=0;i<5;i++)
                this.moneyQueue.add((ICoin) new Gold(this.world,this,new Random().nextInt(13,19),rndnumY,1,1));

        ICoin money = this.moneyQueue.poll();
        money.getBody().setTransform(rndnumX,rndnumY,money.getBody().getAngle());
        this.fallingMoney.add(money);


    }

    private void coinUpdate(float delta) {

        iterCoins();
        acc+=delta;
        if(acc>=3){
            addCoinToWorld();
            acc=0;
        }



    }
}
