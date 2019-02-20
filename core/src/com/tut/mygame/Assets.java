package com.tut.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    static Texture tuxImage;

    static void load() {

        tuxImage = new Texture(Gdx.files.internal("tux.png"));
    }

    static void dispose () {
        tuxImage.dispose();
    }
}
