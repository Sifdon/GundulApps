package com.gundulsoftware.gundulapps.helper;

import java.util.ArrayList;

/**
 * Created by Ardika Bagus on 12-Jul-16.
 */
public class ItemKuis {
    public String item;

    public String getItem(){return item;}
    public void setItem(String item){
        this.item = item;
    }
    public static ArrayList<ItemKuis> getData(String data){
        ArrayList<ItemKuis> dataList = new ArrayList<>();

        String itemCok[] = {
                "Matematika IPA","Fisika", "Kimia", "Biologi","Matematika IPS","Geografi","Ekonomi","Sosiologi",
                "Sejarah","Bahasa Indonesia","Bahasa Inggris", "TKPA"
        };
        for(int i = 0;i< itemCok.length; i++){
            ItemKuis itemKuis = new ItemKuis();
            itemKuis.setItem(itemCok[i]);
            dataList.add(itemKuis);
        }
        return dataList;
    }
}
