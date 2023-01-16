package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Resources {
    public static class SOUNDS{
        public static Sound GOLD_SOUND = Gdx.audio.newSound(Gdx.files.internal("sounds/gold.mp3"));
        public static Sound TRASH_SOUND = Gdx.audio.newSound(Gdx.files.internal("sounds/trash.mp3"));

    }
    public static class TEXTURES{
        public static Texture GOLD_TEXTURE = new Texture(Gdx.files.internal("coins/gold.png"));
        public static Texture TRASH_TEXTURE = new Texture(Gdx.files.internal("coins/trash.png"));

    }

}
