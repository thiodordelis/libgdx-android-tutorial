package com.tut.mygame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class MyGdxGame extends Game  implements ApplicationListener {
	private SpriteBatch batch;
	private OrthographicCamera camera;
    private Rectangle tuxRect;
    Random rnd;
    boolean showTux=true;
    Long startTime;
    int score;
    boolean endGame;

	@Override
	public void create () {
        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering();
		Assets.load();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 640);
        tuxRect = new Rectangle();
        tuxRect.setWidth(Assets.tuxImage.getWidth());
        tuxRect.setHeight(Assets.tuxImage.getHeight());

        tuxRect.x = 480 / 2 - 64 / 2; // center the bucket horizontally
        tuxRect.y = 60; // bottom left corner of the bucket is 20 pi
		batch = new SpriteBatch();
        rnd = new RandomXS128();
        rnd.setSeed(System.currentTimeMillis() / 1000L);
        startTime = System.currentTimeMillis()/1000L;
    }

    public void update() {
	    if ((System.currentTimeMillis()/1000L) - startTime <10) {

            if (showTux) {
                tuxRect.x = rnd.nextInt((int) (480 - tuxRect.width));
                tuxRect.y = rnd.nextInt((int) (640 - tuxRect.height));
                showTux = false;
                Gdx.graphics.requestRendering();
            }

            if (Gdx.input.isTouched()) {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);
                if (touchPos.x >= tuxRect.x && touchPos.x <= tuxRect.x + tuxRect.width) {
                    showTux = true;
                    score++;
                }

            }

        } else {
            System.out.println("END GAME"+score);
            endGame = true;
        }

    }

	@Override
	public void render (){
	    update();
		Gdx.gl.glClearColor(66/255f,159/255f, 70/255f, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		if (!endGame) {
            batch.draw(Assets.tuxImage, tuxRect.x, tuxRect.y);
        }
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
