package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MoneyRainClass;
import com.mygdx.game.interfaces.CoinTemplate;
import com.mygdx.game.interfaces.ICreature;
import com.mygdx.game.managers.GameStateManager;

import java.util.Map;

import static com.mygdx.game.utils.Resources.SOUNDS.GAME_OVER;

public class GameOverState extends GameState {
    float acc=0f;
    private Texture texture;
    private World world;
    private MoneyRainClass app;
    private BitmapFont font12;
    private FreeTypeFontGenerator generator;
    private String resultstr;
    private int resstrLineCount=0;

    public GameOverState(GameStateManager gameStateManager){
        super(gameStateManager);
        GAME_OVER.play();
        this.world=new World(new Vector2(0,0),false);
        this.app=gameStateManager.getApp();


        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/VCR_OSD_MONO_1.001.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        font12 = generator.generateFont(parameter); // font size 12 pixels

        this.resultstr="";

        for (Map.Entry<String, Integer> entry : gameStateManager.getResults().entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            this.resultstr+= "\n"+( key+ " : " + value);
            this.resstrLineCount++;
        }
    }
    @Override
    public void update(float delta) {
        world.step(1f/60,6,2);
        cameraUpdate(delta);
        batch.setProjectionMatrix(camera.combined);
        checkInputs();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.app.getBatch().begin();
        font12.setColor(Color.BLUE);
        font12.draw(this.app.getBatch(),"results:",0,50 );
        font12.setColor(Color.GOLDENROD);
        font12.draw(this.app.getBatch(),this.resultstr,font12.getLineHeight(),50 );
        font12.setColor(Color.BLACK);
        font12.draw(this.app.getBatch(),"press enter to try again!",0,-(this.resstrLineCount)*48);
        this.app.getBatch().end();
    }

    @Override
    public void dispose() {
        generator.dispose();
    }

    public void cameraUpdate(float delta){
        Vector3 position = camera.position;

        position.x= 300;
        position.y= -200;
        camera.position.set(position);
        camera.update();
    }
    public void checkInputs(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            this.gameStateManager.setState(GameStateManager.State.PLAY);
        }
    }
}
