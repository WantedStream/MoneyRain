package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.mygdx.game.bodies.Gold;
import com.mygdx.game.bodies.Platform;
import com.mygdx.game.bodies.Player;
import com.mygdx.game.handlers.Camera;
import com.mygdx.game.handlers.GameContactListener;
import com.mygdx.game.interfaces.ICoin;
import com.mygdx.game.interfaces.ICreature;
import com.mygdx.game.managers.GameStateManager;
import com.mygdx.game.utils.TiltedObjectUtil;

import java.util.*;

import static com.mygdx.game.utils.Constants.PIXLE_PER_METER;

public class PlayState extends GameState{

    private Texture playerTexture;
    public World world;

    private Player player;

    private boolean finished=false;
    private Camera cam;

    public PlayState(GameStateManager gameStateManager){
         super(gameStateManager);
        appplication.results=new Hashtable<ICreature, Integer>();
        world = new World(new Vector2(0,-9.8f),false);


        player = new Player(world,"Player",20,2.6f,gameStateManager);
        playerTexture=new Texture("player/Doom_Slayer.png");


        world.setContactListener(new GameContactListener());
        Platform platform = new Platform(this.world,20.5f,2.5f,17,1,true);

        this.camera=new Camera<>();
    }
    @Override
    public void update(float delta) {
        player.update();
        camera.update(delta);
        world.step(1f/60,6,2);
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(.25f,.25f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        camera.render();
        batch.end();

    }
    @Override
    public void dispose() {

    }






}
