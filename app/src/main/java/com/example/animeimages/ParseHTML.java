package com.example.animeimages;

import org.jsoup.parser.HtmlTreeBuilder;

import java.util.ArrayList;

public class ParseHTML {
    private static final String TAG = "ParseHTML";

    ArrayList<Photo> arrayList;

    public ParseHTML() {
        this.arrayList = new ArrayList<>();
    }

    public ArrayList<Photo> getList() {
        return arrayList;
    }

    public boolean parse(String s) {
        boolean status = false;
        HtmlTreeBuilder builder;


        return status;
    }
}
