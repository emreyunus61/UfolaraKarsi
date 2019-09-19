package com.isgal.isgaldensavun.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//burası ekranların temel sınıfıdır play screen , bu sınıftan üretiliyor

public abstract class state {

    protected OrthographicCamera camera;

    public state(stateManager sm){

        camera = new OrthographicCamera();
        camera.setToOrtho(true, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


    }
    public abstract void render(SpriteBatch sb);
    public abstract void update(float delta);
    public abstract void dispose();

}
