package com.isgal.isgaldensavun.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//interface ile clasları türetti.

public interface gameobject {

    public  void render(SpriteBatch sb);

    public void update(float delta);
}