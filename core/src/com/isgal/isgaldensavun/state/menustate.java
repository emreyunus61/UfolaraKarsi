package com.isgal.isgaldensavun.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.isgal.isgaldensavun.Buton;

 // menüdeki butonları oluşturur ve butonlaarın üstünr dokunulduğunda farklı renk olmasını sağlar.

public class menustate extends state implements InputProcessor{
    public Buton basla1 ,basla2
            ,cik1,cik2,yardim1,yardim2;
    boolean cizBasla,cizYardim,cizCikis;
    com.isgal.isgaldensavun.state.stateManager sm ;
    public menustate(com.isgal.isgaldensavun.state.stateManager sm) {
        super(sm);
        this.sm = sm;
        cizBasla = false;
        cizYardim = false;
        cizCikis = true;
        Gdx.input.setInputProcessor(this);
        basla1 = new Buton((float) (Gdx.graphics.getWidth()/(3.7)), (float) (Gdx.graphics.getHeight()/(5)),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/8, com.isgal.isgaldensavun.imageloader.imageLoader.basla1);
        basla2 = new Buton((float) (Gdx.graphics.getWidth()/(3.7)), (float) (Gdx.graphics.getHeight()/(5)),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/8, com.isgal.isgaldensavun.imageloader.imageLoader.basla2);
        yardim1 = new Buton((float) (Gdx.graphics.getWidth()/(3.7)), (float) (Gdx.graphics.getHeight()/(2.95)),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/8, com.isgal.isgaldensavun.imageloader.imageLoader.yardim1);
        yardim2 = new Buton((float) (Gdx.graphics.getWidth()/(3.7)), (float) (Gdx.graphics.getHeight()/(2.95)),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/8, com.isgal.isgaldensavun.imageloader.imageLoader.yardim2);
        cik1 = new Buton((float) (Gdx.graphics.getWidth()/(3.7)), (float) (Gdx.graphics.getHeight()/(2.1)),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/8, com.isgal.isgaldensavun.imageloader.imageLoader.cik1);
        cik2 = new Buton((float) (Gdx.graphics.getWidth()/(3.7)), (float) (Gdx.graphics.getHeight()/(2.1)),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/8, com.isgal.isgaldensavun.imageloader.imageLoader.cik2);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(com.isgal.isgaldensavun.imageloader.imageLoader.menuArkaPlan,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        sb.end();
        if(cizBasla){
            basla2.render(sb);
        }else {
            basla1.render(sb);
        }
        if(cizYardim){
            yardim2.render(sb);
        }else {
            yardim1.render(sb);
        }
        if(cizCikis){
            cik1.render(sb);
        }else {
            cik2.render(sb);
        }

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

        if(basla1.getButtonRect().contains(screenX,screenY)){
            cizBasla =true;
            //sm.pushState(new playscreen(sm));

        }
        if(yardim1.getButtonRect().contains(screenX,screenY)){
            cizBasla =true;
            //sm.pushState(new playscreen(sm));

        }
        if(cik1.getButtonRect().contains(screenX,screenY)){
            cizCikis =false;
            //sm.pushState(new playscreen(sm));

        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        cizBasla =false;
        cizYardim = false;
        cizCikis = true;
        if(basla1.getButtonRect().contains(screenX,screenY)){

            sm.pushState(new com.isgal.isgaldensavun.state.playscreen(sm));
            System.out.println("play");
        }
        if(yardim1.getButtonRect().contains(screenX,screenY)){

            sm.pushState(new com.isgal.isgaldensavun.state.yardimstate(sm));
            System.out.println("yardim");
        }
        if(cik1.getButtonRect().contains(screenX,screenY)){

            Gdx.app.exit();
            System.out.println("cık");
        }




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
