package com.mygdx.game.interfaces;

import com.mygdx.game.handlers.Camera;

public interface IRenderer {
    public void resize(int width,int height);
    public  void update(float delta);
    public  void render(Camera cam);
    public  void dispose();
}
