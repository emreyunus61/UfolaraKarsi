package com.isgal.isgaldensavun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.isgal.isgaldensavun.imageloader.imageLoader;
import com.isgal.isgaldensavun.state.stateManager;


public class sef extends ApplicationAdapter {
	private SpriteBatch sb;

	private static stateManager sm;
	
	@Override
	public void create () {
		imageLoader.Load();

		sm= new stateManager();
		sm.pushState(new com.isgal.isgaldensavun.state.menustate(sm));
		sb= new SpriteBatch();


	}

	@Override
	//Ekranı temizler ve süreyi günceller.
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sm.render(sb);
		sm.update(Gdx.graphics.getDeltaTime());

	}
	
	@Override
	public void dispose () {
		imageLoader.dispose();

	}
}
