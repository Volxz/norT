package com.exclnetworks.nort;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exclnetworks.nort.matchmaking.MMConfig;
import com.exclnetworks.nort.states.GameStateManager;
import com.exclnetworks.nort.states.MenuState;

import io.socket.client.Socket;

public class NortGame extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;

	public static MMConfig MM_CONFIG;

	public static final int BOXSIZE = 20;

	public static final String TITLE = "NORT";


	private GameStateManager gsm;
	private SpriteBatch batch;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		MM_CONFIG =  new MMConfig();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
