package com.tut.mygame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game  implements ApplicationListener {
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
        setScreen(new MainMenuScreen(this));
    }

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
