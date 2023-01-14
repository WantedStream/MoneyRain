package com.mygdx.game.interfaces;

import com.badlogic.gdx.physics.box2d.Body;

public interface IPhysical {
    public void onCollision(IPhysical physical);
    public Body getBody();
}
