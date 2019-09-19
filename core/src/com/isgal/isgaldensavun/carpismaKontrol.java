package com.isgal.isgaldensavun;

import com.isgal.isgaldensavun.gameobject.dusmanKursun;
import com.isgal.isgaldensavun.gameobject.mermi;

import java.util.ArrayList;

//Düşmanın oyuncuyla çarpışması
//Düşman mermisinin oyuncuyla çarpışma
//Oyuncunun mermisinin düşmanla çarpışması kontrol eden sınıf

public class carpismaKontrol {

    public static boolean carpmaKontrol(com.isgal.isgaldensavun.gameobject.player gemi, ArrayList<com.isgal.isgaldensavun.gameobject.dusman> ufolar) {

        for(int i =0 ; i<ufolar.size();i++){
            if (gemi.getColRec().overlaps(ufolar.get(i).getColRec())) {
                ufolar.remove(i);
                return true;
            }
        }
        return false;
    }
    public static boolean carpmaKontrol2(com.isgal.isgaldensavun.gameobject.player gemi, ArrayList<dusmanKursun> dkursunlar) {

        for(int i =0 ; i<dkursunlar.size();i++){
            if (gemi.getColRec().overlaps(dkursunlar.get(i).getColRec())) {
                dkursunlar.remove(i);
                return true;
            }
        }
        return false;
    }

    public static boolean carpmaKontrol(ArrayList<com.isgal.isgaldensavun.gameobject.dusman> ufolar, ArrayList<mermi> mermiler) {

        for (int i = 0; i < mermiler.size(); i++) {
            for (int j = 0; j < ufolar.size(); j++) {
                if (ufolar.get(j).getColRec().overlaps(mermiler.get(i).getColRec())) {
                    mermiler.remove(i);
                    ufolar.remove(j);
                    return true;
                }
            }
        }

        return false;
    }
}