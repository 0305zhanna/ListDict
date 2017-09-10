package com.user.listnew;



/**
 * Created by User on 20.05.2016.
 */
public class Card  {
    private String word;
    private String translation;

    public Card(String word,String translation){
        this.word = word;
        this.translation = translation;
    }




    public String getWord(){
        return word;
    }
    public String getTranslation(){
        return translation;
    }
}
