package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.interfaces.ICreature;
import com.mygdx.game.managers.GameStateManager;

import java.util.Hashtable;

public class MoneyRainClass extends ApplicationAdapter {
	private boolean DEBUG=false;
	public static final float SCALE=2;
	public static final int V_WIDTH=720;
	public static final int v_HEIGHT =480;
	public static final String TITLE="EndlessGlory";
	private OrthographicCamera camera;

	private SpriteBatch batch;
	private GameStateManager gameStateManager;
	@Override
	public void create () {
		int width = Gdx.graphics.getWidth();
		int height= Gdx.graphics.getHeight();
		this.batch =  new SpriteBatch();
		this.camera= new OrthographicCamera();
		gameStateManager=new GameStateManager(this);//must be after batch and camera declarations
		resize(width,height);
	}

	@Override
	public void render(){
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render();

		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
	}
	@Override
	public void resize(int width,int height){
		gameStateManager.resize((int)(width/SCALE),(int)(height/SCALE));
	}
	@Override
	public void dispose(){
		gameStateManager.dispose();
		batch.dispose();
	}
	public OrthographicCamera getCamera() {return camera;}
	public SpriteBatch getBatch(){
		return batch;
	}
	public GameStateManager getGameStateManager(){return this.gameStateManager;}
}
