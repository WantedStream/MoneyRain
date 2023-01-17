package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Resources {
    public static class SOUNDS{
        public static Sound GOLD_SOUND = Gdx.audio.newSound(Gdx.files.internal("sounds/gold.mp3"));
        public static Sound TRASH_SOUND = Gdx.audio.newSound(Gdx.files.internal("sounds/trash.mp3"));
        public static Sound GAME_OVER = Gdx.audio.newSound(Gdx.files.internal("sounds/gameover.mp3"));
        public static Sound GAME_START = Gdx.audio.newSound(Gdx.files.internal("sounds/GameStart.mp3"));
        public static Sound DIAMOND_SOUND =Gdx.audio.newSound(Gdx.files.internal("sounds/diamond.mp3"));
        public static Sound PENNY_SOUND =Gdx.audio.newSound(Gdx.files.internal("sounds/penny.mp3"));
        public static Sound CHEESE_SOUND =Gdx.audio.newSound(Gdx.files.internal("sounds/cheese.mp3"));
        public static Sound CROWN_SOUND =Gdx.audio.newSound(Gdx.files.internal("sounds/crown.mp3"));
        public static Sound HEART_SOUND =Gdx.audio.newSound(Gdx.files.internal("sounds/heart.mp3"));
        public static Sound POWERUP_SOUND =Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.mp3"));
        public static Sound NOPOWERUPS_SOUND =Gdx.audio.newSound(Gdx.files.internal("sounds/nopowerups.mp3"));
        public static Sound X2_SOUND =Gdx.audio.newSound(Gdx.files.internal("sounds/x2.mp3"));
        public static Sound X3_SOUND =Gdx.audio.newSound(Gdx.files.internal("sounds/x3.mp3"));

    }
    public static class TEXTURES{
        public static Texture DIAMOND_TEXTURE= new Texture(Gdx.files.internal("coins/diamond.png"));
        public static Texture GOLD_TEXTURE = new Texture(Gdx.files.internal("coins/gold.png"));
        public static Texture TRASH_TEXTURE = new Texture(Gdx.files.internal("coins/trash.png"));
        public static Texture PENNY_TEXTURE = new Texture(Gdx.files.internal("coins/penny.png"));
        public static Texture CHEESE_TEXTURE = new Texture(Gdx.files.internal("coins/cheese.png"));
        public static Texture CROWN_TEXTURE = new Texture(Gdx.files.internal("coins/crown.png"));
        public static Texture HEART_TEXTURE = new Texture(Gdx.files.internal("coins/heart.png"));
        public static Texture X2_TEXTURE = new Texture(Gdx.files.internal("coins/x2.png"));
        public static Texture X3_TEXTURE = new Texture(Gdx.files.internal("coins/x3.png"));

        public static Texture PLAYER_TEXTURE= new Texture(Gdx.files.internal("coins/clashofclans.png"));

        public static Texture REC_TEXTURE = createRecTex();
        public static Texture createRecTex(){
            Pixmap pixmap = new Pixmap(30, 30, Pixmap.Format.RGBA8888);
            pixmap.fillRectangle(0,0,pixmap.getWidth(), pixmap.getHeight());
            Texture t = new Texture(pixmap);
            pixmap.dispose();
            return t;
        }
    }

}
