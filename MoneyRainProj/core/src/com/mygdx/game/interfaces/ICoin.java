package com.mygdx.game.interfaces;


import java.util.Iterator;
import java.util.ListIterator;

public interface ICoin extends IPhysical{
    public short getScore();
    public void respawn();
    public void update();
}
