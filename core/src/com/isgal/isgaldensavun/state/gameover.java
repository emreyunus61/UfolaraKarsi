package com.isgal.isgaldensavun.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// can=0 olduğunda gameover eekranını açar ve skoru gösterir.


public class gameover extends state {

    private int score;
    public gameover(com.isgal.isgaldensavun.state.stateManager sm, int score) {
        super(sm);
        this.score = score;
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(com.isgal.isgaldensavun.imageloader.imageLoader.gameoverReg,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
        com.isgal.isgaldensavun.imageloader.imageLoader.font.draw(sb, "score = " + Integer.toString(score),Gdx.graphics.getWidth()/4, (float) (Gdx.graphics.getHeight()/1.5));
        sb.end();

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose() {

    }
}
