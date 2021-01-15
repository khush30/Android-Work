package com.example.a1851isa2;

import java.util.ArrayList;

public class CharacterInfo {

    int char_id;
    String imageUrl;
    String char_name;
    String birthday;
    ArrayList<String> occupations;
    String status;
    String nickname;
    String portrayed;
    ArrayList<String> category;
    ArrayList bcs_seasons;

    CharacterInfo(){
        this.occupations = new ArrayList<String>();
        this.char_name = null;
        this.birthday = null;
        this.status = null;
        this.nickname = null;
        this.portrayed = null;
        this.imageUrl = null;
        this.category = new ArrayList<String>();
        this.bcs_seasons = new ArrayList();
    }

}
