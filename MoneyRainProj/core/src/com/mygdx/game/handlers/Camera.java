package com.mygdx.game.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.bodies.Player;
import com.mygdx.game.interfaces.ICamera;
import com.mygdx.game.interfaces.IRenderer;

import java.util.List;

import static com.mygdx.game.utils.Constants.PIXLE_PER_METER;

public class Camera<CamType extends com.badlogic.gdx.graphics.Camera> implements ICamera {
    public interface CamFunction {
        String doSomething(int param1, String param2);
    }
    List<IRenderer> renderers;
    private Batch batch;
    private World world;
    private CamType camera;
    private Player player;
    public Camera(Batch batch,World world, CamType orthographicCamera){
            this.world=world;
            this.camera=orthographicCamera;
            this.player=null;
    }
    public Camera(Batch batch, World world, CamType orthographicCamera, Player p){
        this.world=world;
        this.camera=orthographicCamera;
        this.player=p;
    }
    @Override
    public void resize(float width, float height) {

    }

    @Override
    public void update(float delta) {
        cameraUpdate(delta);
        this.batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render() {
        for (IRenderer renderer: this.renderers){
            renderer.render();
        }
        orthogonalTiledMapRenderer.render();
    }

    @Override
    public void dispose() {
        for (IRenderer renderer: this.renderers){
            renderer.dispose();
        }
        orthogonalTiledMapRenderer.dispose();
        tiledMap.dispose();
    }

    @Override
    public com.badlogic.gdx.graphics.Camera getWorldCamera() {
        return this.camera;
    }

    private void cameraUpdate(float delta){
        if(this.player!=null){
            Vector3 position = camera.position;

            position.x=camera.position.x+( player.getBody().getPosition().x*PIXLE_PER_METER-camera.position.x)*1f;
            position.y= camera.position.y+( player.getBody().getPosition().y*PIXLE_PER_METER-camera.position.y)*1f;
            camera.position.set(position);

            camera.update();
        }
    }
    public void addRenderer(IRenderer renderer){
        this.renderers.add(renderer);
    }

}
