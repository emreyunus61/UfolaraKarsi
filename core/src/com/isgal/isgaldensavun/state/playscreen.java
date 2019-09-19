package com.isgal.isgaldensavun.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.isgal.isgaldensavun.gameobject.dusman;
import com.isgal.isgaldensavun.gameobject.mermi;
import com.isgal.isgaldensavun.gameobject.player;
import com.isgal.isgaldensavun.imageloader.imageLoader;

import java.util.ArrayList;
import java.util.Random;

import static com.isgal.isgaldensavun.imageloader.imageLoader.kursun;
import static com.isgal.isgaldensavun.imageloader.imageLoader.sesGameOver;
import static com.isgal.isgaldensavun.imageloader.imageLoader.sesKursun;

//Oyunun oynanış bölümü bu sınıfta dönderiliyor.

public class playscreen extends state implements InputProcessor{

    /*Mermiler ve Düşmanlar dizisi ve diğer değişkenler tanımlanıyor */

    private player gemi;
    private ArrayList<mermi> mermiler;
    private ArrayList<dusman> dusmanlar;
    private long dusmanUretmeZamani;
    Random rnd= new Random();
    private int score;
    private int can ;
    private int level,olenDusmanSayisi ,levelGecme;
    private stateManager sm;

    public playscreen(stateManager sm) {

        // Diziler olurturuluyor ve diğer değişkenlerimize değerler atanıyor.

        super(sm);
        this.sm = sm;
        gemi = new player();
        mermiler = new ArrayList<mermi>();
        dusmanlar = new ArrayList<dusman>();
        dusmanUretmeZamani = TimeUtils.nanoTime();
        score = 0;
        can = 3;
        level =1;
        olenDusmanSayisi = 0;
        levelGecme =15;
        Gdx.input.setInputProcessor(this);


    }

    @Override
    // Sağ altta bulunan can sayısını gösterir ve azalmasını sağlar.
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(com.isgal.isgaldensavun.imageloader.imageLoader.arkaplanReg,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        if(can>0){
            sb.draw(com.isgal.isgaldensavun.imageloader.imageLoader.playerReg, (float) (Gdx.graphics.getWidth()/1.1), (float) (Gdx.graphics.getHeight()/1.1),Gdx.graphics.getWidth()/20,Gdx.graphics.getWidth()/20);
        } if(can>1){
            sb.draw(com.isgal.isgaldensavun.imageloader.imageLoader.playerReg, (float) (Gdx.graphics.getWidth()/1.2), (float) (Gdx.graphics.getHeight()/1.1),Gdx.graphics.getWidth()/20,Gdx.graphics.getWidth()/20);
        } if(can>2){
            sb.draw(com.isgal.isgaldensavun.imageloader.imageLoader.playerReg, (float) (Gdx.graphics.getWidth()/1.3), (float) (Gdx.graphics.getHeight()/1.1),Gdx.graphics.getWidth()/20,Gdx.graphics.getWidth()/20);
        }

        //Ekranda yukarda yazan Skor ve Level yazıları.
        com.isgal.isgaldensavun.imageloader.imageLoader.font.draw(sb, "score = " + Integer.toString(score),0,0);
        com.isgal.isgaldensavun.imageloader.imageLoader.font.draw(sb, "level = " + Integer.toString(level), (float) (Gdx.graphics.getWidth()/1.5), 0);
        sb.end();


        for(int i =0; i<mermiler.size();i++){
            mermiler.get(i).render(sb);
        }
        for(int i = 0; i< dusmanlar.size();i++){
            dusmanlar.get(i).render(sb);
        }
        gemi.render(sb);
    }

    @Override
    //Düşman ve kurşunları kaybetmek için Üretilen düşmanların ve mermilerin sayısını tutar.(Daha sonra bu düşmanı ve mermiyi yok etmek için)
    public void update(float delta) {
        gemi.update(delta);
        for(int i =0; i<mermiler.size();i++){
            mermiler.get(i).update(delta);
        }
        for(int i = 0; i< dusmanlar.size();i++){
            dusmanlar.get(i).update(delta);
        }
        kursunAt();
        dusmanUret();
        // Düşmana isabet eden her kurşun için 10 puan artırır.Ölen düşman sayısına göre level(seviye) geçilir.
        if(com.isgal.isgaldensavun.carpismaKontrol.carpmaKontrol(dusmanlar,mermiler)){
            score +=10;
            olenDusmanSayisi++;
            if(olenDusmanSayisi>levelGecme){
                level++;
                levelGecme+=levelGecme;
            }
        }
        //Düşmanla oyuncu çarpışırsa can 1 azalır ve can 0 olursa gameover ekranı açılır ve skoru gösterir.
        if(com.isgal.isgaldensavun.carpismaKontrol.carpmaKontrol(gemi,dusmanlar)){

            can -= 1;
            if(can == 0 ){
                sesGameOver.play();
                sm.pushState(new com.isgal.isgaldensavun.state.gameover(sm,score));
            }
        }
        if(can == 0 ){
            sesGameOver.play();
            sm.pushState(new com.isgal.isgaldensavun.state.gameover(sm,score));
        }
        //Düşman kurşunu oyuncuya isabet ederse can 1 azalır
        for(int i = 0;i<dusmanlar.size();i++){
            for(int j =0 ; j<dusmanlar.get(i).getKursunD().size();j++){
                if(com.isgal.isgaldensavun.carpismaKontrol.carpmaKontrol2(gemi,dusmanlar.get(i).getKursunD())){
                    can--;
                }

            }
        }

    }

    @Override
    public void dispose() {
        kursun.dispose();
        sesGameOver.dispose();
        sesKursun.dispose();

    }
    // ekrana basınca oyuncu kursun atıyor.
    public void kursunAt(){
        if(Gdx.input.justTouched()){
            mermiler.add(new mermi(gemi.getPosition().x-12+gemi.getSize().x/2,(gemi.getPosition().y)));
            sesKursun.play();
        }
    }
    //her 1.5 saniyede bir dusmanlar dizisinden rastgele düşman üretilir.
    public void dusmanUret(){

        if(TimeUtils.nanoTime()-dusmanUretmeZamani >1500000000){
            int sayi = rnd.nextInt(Gdx.graphics.getWidth());
            dusmanlar.add(new dusman(sayi));
            dusmanUretmeZamani = TimeUtils.nanoTime();
        }
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