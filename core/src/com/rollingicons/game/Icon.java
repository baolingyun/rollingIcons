package com.rollingicons.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Icon {
	public Body body;
	public Fixture fixture;

	public void Create(World world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		body = world.createBody(bodyDef);
		PolygonShape polygon = new PolygonShape();
		polygon.setAsBox(0.7f, 0.7f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygon;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 1.0f;
		body.setTransform(new Vector2(1, 1), 1);
		fixture = body.createFixture(fixtureDef);
		polygon.dispose();
	}
}
