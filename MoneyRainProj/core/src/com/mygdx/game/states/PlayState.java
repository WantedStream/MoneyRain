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
import com.mygdx.game.handlers.GameContactListener;
import com.mygdx.game.interfaces.ICoin;
import com.mygdx.game.interfaces.ICreature;
import com.mygdx.game.managers.GameStateManager;
import com.mygdx.game.utils.TiltedObjectUtil;

import java.util.*;

import static com.mygdx.game.utils.Constants.PIXLE_PER_METER;

public class PlayState extends GameState{

    public  DelayedRemovalArray<Object> removalArray;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TiledMap tiledMap;
    private Texture playerTexture;
    public World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private float  acc;

    private Player player;

    private boolean finished=false;
    private Queue<ICoin> moneyQueue;
    private BitmapFont font12;
    private FreeTypeFontGenerator generator;
    private List<ICoin> fallingMoney;
    private String hudstr;
    public Queue<ICoin> getCoinQueue(){
        return this.moneyQueue;
    }
    public PlayState(GameStateManager gameStateManager){
         super(gameStateManager);
         this.acc=0;
         this.moneyQueue= new LinkedList<ICoin>();
            this.fallingMoney=new LinkedList<ICoin>();
        this.removalArray=new DelayedRemovalArray<>();

        appplication.results=new Hashtable<ICreature, Integer>();
        world = new World(new Vector2(0,-9.8f),false);

        box2DDebugRenderer=new Box2DDebugRenderer();

        player = new Player(world,"Player",20,2.6f,gameStateManager);
        playerTexture=new Texture("player/Doom_Slayer.png");

        tiledMap=new TmxMapLoader().load("misc/map.tmx");
        orthogonalTiledMapRenderer= new OrthogonalTiledMapRenderer(tiledMap);

        TiltedObjectUtil.parseTileObjectLayer(world, tiledMap.getLayers().get("collider-layer").getObjects());
        world.setContactListener(new GameContactListener());
        Platform platform = new Platform(this.world,20.5f,2.5f,17,1,true);


        createFont();
    }
    @Override
    public void update(float delta) {
        player.update();
        world.step(1f/60,6,2);
        inputUpdate(delta);
       cameraUpdate(delta);
        coinUpdate(delta);
        orthogonalTiledMapRenderer.setView(camera);
        batch.setProjectionMatrix(camera.combined);
    }

    private void coinUpdate(float delta) {

        iterCoins();
        acc+=delta;
        if(acc>=3){
            addCoinToWorld();
            acc=0;
        }



    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.25f,.25f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float width=player.getWidth()*PIXLE_PER_METER*2;
        float height = player.getHeight()*PIXLE_PER_METER*2;
        batch.begin();
        batch.draw(playerTexture,player.getBody().getPosition().x*PIXLE_PER_METER-(width/2),player.getBody().getPosition().y*PIXLE_PER_METER-(height/2),width,height);
        drawHud();
        batch.end();

        orthogonalTiledMapRenderer.render();
        box2DDebugRenderer.render(world,camera.combined.scl(PIXLE_PER_METER));

    }
    @Override
    public void dispose() {
        box2DDebugRenderer.dispose();
        orthogonalTiledMapRenderer.dispose();
        tiledMap.dispose();
    }

    public void cameraUpdate(float delta){
        Vector3 position = camera.position;

            position.x=camera.position.x+( player.getBody().getPosition().x*PIXLE_PER_METER-camera.position.x)*1f;
            position.y= camera.position.y+( player.getBody().getPosition().y*PIXLE_PER_METER-camera.position.y)*1f;
            camera.position.set(position);


        camera.update();
    }
    public void inputUpdate(float delta){
        int horizontalForce=0;
        int verticalForce=0;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            horizontalForce -=1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            horizontalForce +=1;
        }

        player.getBody().setLinearVelocity(horizontalForce*50,0f);

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
           // player.doAttack();
        }
    }



    public void drawHud(){
        font12.setColor(Color.GOLDENROD);
        font12.draw(appplication.getBatch(),this.hudstr+player.getScore(),0,0 );
        font12.setColor(Color.BLACK);
        font12.draw(appplication.getBatch(),"press enter to try again!",0,this.hudstr.length()*-5 );
    }
    public void addCoinToWorld(){

        int rndnumX=new Random().nextInt(13,19);
        int rndnumY=19;
        if(this.moneyQueue.isEmpty())
            for (int i=0;i<5;i++)
           this.moneyQueue.add((ICoin) new Gold(this.world,this,new Random().nextInt(13,19),rndnumY,1,1));

            ICoin money = this.moneyQueue.poll();
            money.getBody().setTransform(rndnumX,rndnumY,money.getBody().getAngle());
            this.fallingMoney.add(money);


    }
    public void iterCoins(){
        ListIterator<ICoin> iterator = this.fallingMoney.listIterator();
        if(!this.fallingMoney.isEmpty())
        System.out.print(Arrays.toString(this.fallingMoney.toArray()));
        while(iterator.hasNext()) {
            ICoin c = iterator.next();
            iterator.remove();
            c.update();
        }
    }
    private void createFont(){
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/VCR_OSD_MONO_1.001.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        font12 = generator.generateFont(parameter); // font size 12 pixels
        this.hudstr ="results:";
    }
}
