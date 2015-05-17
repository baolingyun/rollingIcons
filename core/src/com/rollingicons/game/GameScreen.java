package com.rollingicons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.rollingicons.game.Icon.Status;

public class GameScreen extends ScreenAdapter {

	private float SCREEN_WIDTH = Gdx.graphics.getWidth() / 100;
	private float SCREEN_HEIGHT = Gdx.graphics.getHeight() / 100;

	private IconsWorld iconsWorld = new IconsWorld();
	private OrthographicCamera camera = new OrthographicCamera(SCREEN_WIDTH,
			SCREEN_HEIGHT);

	private IconsWorldRenderer iconsWorldRenderer = new IconsWorldRenderer();

	public GameScreen() {

		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		camera.update();

		iconsWorldRenderer.SetIconsWorld(iconsWorld);
		iconsWorldRenderer.SetCamera(camera);

		iconsWorld.CreateEdge(camera);

		iconsWorld.Start();
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.getGL20().glClearColor(0, 0, 0, 0);
		Gdx.graphics.getGL20().glClear(
				GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		if (Gdx.input.justTouched()) {
			Vector3 touchPoint = new Vector3();
			camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(),
					0));

			for (Icon icon : iconsWorld.icons) {
				if (icon.status != Status.FINISHED) {
					if (icon.HitTest(touchPoint.x, touchPoint.y)) {
						icon.Hit();
						iconsWorld.UpdateStatus(icon);
						Gdx.input.vibrate(50);
						break;
					}
				}
			}
		}

		iconsWorld.CheckAndLevelUp();
		iconsWorldRenderer.render();
		iconsWorld.physicalWorld.step(1 / 60f, 6, 2);
	}
}
