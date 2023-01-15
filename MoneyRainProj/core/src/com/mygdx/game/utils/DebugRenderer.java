package com.mygdx.game.utils;

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.handlers.Camera;
import com.mygdx.game.interfaces.IRenderer;

import static com.mygdx.game.utils.Constants.PIXLE_PER_METER;

public class DebugRenderer implements IRenderer {
    private Box2DDebugRenderer renderer=new Box2DDebugRenderer();
    private World world;
    private Camera camera;
    public DebugRenderer(World world){
        this.world=world;
    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Camera cam) {
        com.badlogic.gdx.graphics.Camera camera1 = this.camera.getWorldCamera();
        this.renderer.render( this.world,camera1.combined.scl(PIXLE_PER_METER));
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
