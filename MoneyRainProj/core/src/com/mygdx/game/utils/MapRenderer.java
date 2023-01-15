package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.handlers.Camera;
import com.mygdx.game.interfaces.IRenderer;

public class MapRenderer implements IRenderer {

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    MapRenderer(World world){
        tiledMap=new TmxMapLoader().load("misc/map.tmx");
        orthogonalTiledMapRenderer= new OrthogonalTiledMapRenderer(tiledMap);
        TiltedObjectUtil.parseTileObjectLayer(world, tiledMap.getLayers().get("collider-layer").getObjects());
    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Camera cam) {

        orthogonalTiledMapRenderer.setView((OrthographicCamera) cam.getWorldCamera());
        orthogonalTiledMapRenderer.render();

    }

    @Override
    public void dispose() {
        orthogonalTiledMapRenderer.dispose();
        tiledMap.dispose();
    }
}
