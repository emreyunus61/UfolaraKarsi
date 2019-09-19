package com.isgal.isgaldensavun.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Menüdeki yardım butonuna basıldığında yardımArka plan resmini gösterir.

public class yardimstate extends state implements InputProcessor {

    public yardimstate(com.isgal.isgaldensavun.state.stateManager sm) {
        super(sm);
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(com.isgal.isgaldensavun.imageloader.imageLoader.yardimArka,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        sb.end();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
