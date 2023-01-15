package com.mygdx.game.interfaces;

public interface ICamera<T> {
    public void resize(float width,float height);
    public  void update(float delta);
    public abstract void render();
    public abstract void dispose();
    public T getWorldCamera();
}
