package com.user.listnew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity implements OnClickListener {
    private static final int CM_DELETE_ID = 1;
    Button btnTest;
    Button btnPlus;
    ListView lvCards;
    ArrayList<Card> cards = new ArrayList<Card>();
    CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTest = (Button) findViewById(R.id.btnTest);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        lvCards = (ListView) findViewById(R.id.lvCards);

        btnPlus.setOnClickListener(this);
        btnTest.setOnClickListener(this);
        adapter = new CardAdapter(MainActivity.this);
        lvCards.setAdapter(adapter);
        registerForContextMenu(lvCards);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            // получаем инфу о пункте списка
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            // удаляем Map из коллекции, используя позицию пункта в списке
            cards.remove(acmi.position);
            // уведомляем, что данные изменились
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlus:
                Intent intent1 = new Intent(this, PlusActivity.class);
                startActivityForResult(intent1, 1);

                break;
            case R.id.btnTest:
                if(cards.size() != 0){
                Intent intent2 = new Intent(this, TestActivity.class);
                String [] words = new String[cards.size()];
                String [] translates = new String[cards.size()];
                for(int i = 0; i< cards.size();i++){
                    words[i] = cards.get(i).getWord();
                }
                for(int i = 0; i< cards.size();i++){
                    translates[i] = cards.get(i).getTranslation();
                }
                intent2.putExtra("words", words);
                intent2.putExtra("translates", translates);
                startActivity(intent2);}
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        String word = "";
        String translate = "";
        word =  data.getStringExtra("word");
        translate = data.getExtras().getString("translate");
        Card card = new Card(word,translate);
        adapter.add(card);
        adapter.notifyDataSetChanged();
    }


    private class CardAdapter extends ArrayAdapter<Card> {
        public CardAdapter(Context context){
            super(context, android.R.layout.simple_list_item_2,cards);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            Card card = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(android.R.layout.simple_list_item_2, null);
            }
            ((TextView) convertView.findViewById(android.R.id.text1))
                    .setText(card.getWord());
            ((TextView) convertView.findViewById(android.R.id.text2))
                    .setText(card.getTranslation());
            return convertView;
        }
    }

}

