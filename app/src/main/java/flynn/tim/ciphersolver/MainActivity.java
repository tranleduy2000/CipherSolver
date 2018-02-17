package flynn.tim.ciphersolver;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import flynn.tim.ciphersolver.atbash.AtbashActivity;
import flynn.tim.ciphersolver.caesar.CaesarCipherActivity;
import flynn.tim.ciphersolver.frequency.FrequencyActivity;
import flynn.tim.ciphersolver.rot13.Rot13CipherActivity;
import flynn.tim.ciphersolver.vigenere.VigenereCipherActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ColorDrawable newColor = new ColorDrawable(getResources().getColor(R.color.accent_material_light));
        getSupportActionBar().setBackgroundDrawable(newColor);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("");
        final ListView listview = findViewById((R.id.listView));
        final ArrayList<Result> cipherList = new ArrayList<>();
        cipherList.add(new Result("Caesar Cipher", false, false));
        cipherList.add(new Result("Frequency Analysis", false, false));
        cipherList.add(new Result("Rot-13 Cipher", false, false));
        cipherList.add(new Result("Vigenère Cipher", false, false));
        cipherList.add(new Result("Atbash Cipher", false, false));
        //cipherList.add(new Result ("Letter-Number Cipher", false, false));
        MainListAdapter adapter = new MainListAdapter(getApplicationContext(), R.layout.list_item_main, cipherList);
        listview.setAdapter(adapter);
        //Set click listener for each item in the listview
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (cipherList.get(position).getResult().contentEquals("Caesar Cipher")) {
                    Intent intent = new Intent(MainActivity.this, CaesarCipherActivity.class);
                    MainActivity.this.startActivity(intent);
                }
                if (cipherList.get(position).getResult().contentEquals("Frequency Analysis")) {
                    Intent i = new Intent(MainActivity.this, FrequencyActivity.class);
                    MainActivity.this.startActivity(i);
                }
                if (cipherList.get(position).getResult().contentEquals("Rot-13 Cipher")) {
                    Intent i = new Intent(MainActivity.this, Rot13CipherActivity.class);
                    MainActivity.this.startActivity(i);
                }
                if (cipherList.get(position).getResult().contentEquals("Vigenère Cipher")) {
                    Intent i = new Intent(MainActivity.this, VigenereCipherActivity.class);
                    MainActivity.this.startActivity(i);
                }
                if (cipherList.get(position).getResult().contentEquals("Atbash Cipher")) {
                    Intent i = new Intent(MainActivity.this, AtbashActivity.class);
                    MainActivity.this.startActivity(i);

                }
            }
        });
    }


}
