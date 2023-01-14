package com.mygdx.game.interfaces;

public interface ICreature extends IPhysical{
    public float getDamage();
    public void decHealth(float damage);
}
