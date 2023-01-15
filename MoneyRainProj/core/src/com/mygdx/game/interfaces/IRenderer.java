package com.mygdx.game.interfaces;

public interface IRenderer {
    public void resize(int width,int height);
    public  void update(float delta);
    public  void render();
    public  void dispose();
}
