package com.user.listnew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by User on 19.05.2016.
 */
public class PlusActivity extends Activity implements View.OnClickListener {
    EditText tWord;
    EditText tTranslation;
    Button btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actibity_plus);
        tWord = (EditText) findViewById(R.id.editWord);
        tTranslation = (EditText) findViewById(R.id.editTranslation);
        btnOk = (Button) findViewById(R.id.button);
        btnOk.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("word", tWord.getText().toString());
        intent.putExtra("translate", tTranslation.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
