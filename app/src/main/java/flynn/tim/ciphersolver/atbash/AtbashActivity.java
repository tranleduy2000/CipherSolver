package flynn.tim.ciphersolver.atbash;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import flynn.tim.ciphersolver.MyListAdapter;
import flynn.tim.ciphersolver.R;
import flynn.tim.ciphersolver.Result;


public class AtbashActivity extends AppCompatActivity {

    private ArrayList<Result> resultsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atbash);

        final EditText ciphertext = findViewById(R.id.editText2);
        final Button solve = findViewById(R.id.button3);
        final ListView listview = findViewById(R.id.listView3);

        solve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                String result;
                resultsList.clear();
                if (ciphertext.getText().toString().equals("")) {
                    resultsList.add(new Result("No ciphertext entered!", false, true));
                } else {
                    result = Atbash.decrypt(ciphertext.getText().toString().toUpperCase());
                    resultsList.add(new Result(result, true, false));
                }


                final MyListAdapter adapter = new MyListAdapter(getApplicationContext(), R.layout.list_item_caesar, resultsList);
                listview.setAdapter(adapter);

                listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                                   int pos, long id) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("label", resultsList.get(pos).getResult());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getApplicationContext(), resultsList.get(pos).getResult().toUpperCase() + " copied to clipboard",
                                Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });
            }
        });
    }


}
