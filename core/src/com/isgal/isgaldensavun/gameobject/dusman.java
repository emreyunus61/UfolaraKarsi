package com.isgal.isgaldensavun.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

//dusman  interface ile gameobject sınıfından türetildi ve playscreen sınıfında nesneyle  üretildi.

public class dusman implements gameobject {
    private Vector2 position;
    private Vector2 size;
    private Rectangle colRec;
    private int hiz;
    private ArrayList<com.isgal.isgaldensavun.gameobject.dusmanKursun> kursunD;
    Random rdn ;
    public dusman(int x){
        position = new Vector2(x,0);
        size = new Vector2(Gdx.graphics.getWidth()/14,Gdx.graphics.getWidth()/14);
        colRec = new Rectangle(position.x,position.y,size.x,size.y);
        hiz = 2;
        rdn = new Random();
        kursunD = new ArrayList<com.isgal.isgaldensavun.gameobject.dusmanKursun>();
    }
    @Override
    public void render(SpriteBatch sb) {   
        sb.begin();
        sb.draw(com.isgal.isgaldensavun.imageloader.imageLoader.dusmanReg,position.x,position.y,size.x,size.y);
        sb.end();
        for(int i = 0; i<kursunD.size();i++ ){
            kursunD.get(i).render(sb);
        }
    }

    @Override
    public void update(float delta) {
        colRec.x = position.x;
        colRec.y = position.y;
        dusmanHareket();
        kursunAt();
        for(int i = 0; i<kursunD.size();i++ ){
            kursunD.get(i).update(delta);
        }
    }
    public void dusmanHareket(){

        position.add(0,hiz);
    }
    public void kursunAt(){
        int sayi = rdn.nextInt(10000);
        if(sayi<50){
            System.out.println("dusman kursun");
            kursunD.add(new com.isgal.isgaldensavun.gameobject.dusmanKursun(position.x+size.x/2,position.y+size.y/2));

        }
    }
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public Rectangle getColRec() {
        return colRec;
    }

    public void setColRec(Rectangle colRec) {
        this.colRec = colRec;
    }

    public int getHiz() {
        return hiz;
    }

    public void setHiz(int hiz) {
        this.hiz = hiz;
    }

    public ArrayList<com.isgal.isgaldensavun.gameobject.dusmanKursun> getKursunD() {
        return kursunD;
    }

    public void setKursunD(ArrayList<com.isgal.isgaldensavun.gameobject.dusmanKursun> kursunD) {
        this.kursunD = kursunD;
    }
}
