package com.user.listnew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class TestActivity extends Activity implements View.OnClickListener {
    TextView tvWord;
    Button btnNext;
    Button btnFinish;
    ListView lvWords;
    String[] words;
    String[] translates;
    ArrayAdapter<String> adapter;
    Random rnd;
    int k = 0;
    String[] s = new String[4];
    int Errors = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        rnd = new Random();

        btnNext = (Button) findViewById(R.id.btnNext);
        btnFinish = (Button) findViewById(R.id.btnFinish);
        tvWord = (TextView) findViewById(R.id.tvWord);

        lvWords = (ListView) findViewById(R.id.lvTranslate);
        lvWords.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        btnNext.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        Intent intent = getIntent();
        words = intent.getStringArrayExtra("words");
        translates = intent.getStringArrayExtra("translates");

        myshuffleArray(words, translates);
        MyTest(k);

    }

    public void MyTest(int k) {
        tvWord.setText(words[k]);
        s[0] = translates[k];
        for(int i = 1; i<4; i++){
            int index = rnd.nextInt(translates.length);
            s[i] = translates[index];
        }
        shuffleArray(s);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,s);
        lvWords.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                if(s[lvWords.getCheckedItemPosition()] == translates[k]){
                    if(k <words.length-1){
                        k++;
                        MyTest(k);
                    }else{
                        Toast.makeText(getApplicationContext(), "Неправильных "+Errors+" из "+translates.length+"!", Toast.LENGTH_LONG)
                                .show();
                    }
                }else{
                    Errors++;
                    Toast.makeText(getApplicationContext(), "Неправильно", Toast.LENGTH_SHORT)
                    .show();
                }
                break;
            case R.id.btnFinish:
                finish();
                break;
            default:
                break;
        }
    }

     void shuffleArray(String[] ar) {
     //   Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

     void myshuffleArray(String[] ar1, String[] ar2) {
       // Random rnd = new Random();
        for (int i = ar1.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i+1);
            // Simple swap
            String a1 = ar1[index];
            String a2 = ar2[index];
            ar1[index] = ar1[i];
            ar2[index] = ar2[i];
            ar1[i]=a1;
            ar2[i]=a2;
        }
    }
}

