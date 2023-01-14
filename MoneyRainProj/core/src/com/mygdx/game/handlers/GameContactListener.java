package com.mygdx.game.handlers;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.interfaces.IPhysical;

public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa == null || fb == null) return;
        if(fa.getUserData() == null || fb.getUserData() == null) return;
        ((IPhysical) fa.getUserData()).onCollision((IPhysical) fb.getUserData());
        ((IPhysical) fb.getUserData()).onCollision((IPhysical) fa.getUserData());

    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa == null || fb == null) return;
        if(fa.getUserData() == null || fb.getUserData() == null) return;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    /*
    private boolean  isTutorialContact(Fixture a, Fixture b) {
        return (a.getUserData() instanceof TutorialBox && b.getUserData() instanceof TutorialBox);
    }
    */

}
