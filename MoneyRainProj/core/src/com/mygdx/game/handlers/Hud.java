package com.mygdx.game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Hud {

    private String hudstr;
    private BitmapFont currentFont;

    public static Hud createHud(){
        Hud hud = new Hud();
        hud.currentFont=createFont();
        return hud;
    }

    public void drawHud(Batch batch,String info){
        this.currentFont.setColor(Color.GOLDENROD);
        this.currentFont.draw(batch,this.hudstr+info,0,0 );
        this.currentFont.setColor(Color.BLACK);
        this.currentFont.draw(batch,"press enter to try again!",0,this.hudstr.length()*-5 );
    }
    private static BitmapFont createFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/VCR_OSD_MONO_1.001.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        return generator.generateFont(parameter);
    }
}
