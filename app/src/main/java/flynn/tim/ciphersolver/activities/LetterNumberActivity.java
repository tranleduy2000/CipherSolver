package flynn.tim.ciphersolver.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

import flynn.tim.ciphersolver.LetterNumber;
import flynn.tim.ciphersolver.MyListAdapter;
import flynn.tim.ciphersolver.R;
import flynn.tim.ciphersolver.Result;


public class LetterNumberActivity extends AppCompatActivity {

    ArrayList<Result> resultsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rot13_cipher);

        final EditText ciphertext = (EditText) findViewById(R.id.editText2);
        final Button solve = (Button) findViewById(R.id.button3);
        final ListView listview = (ListView) findViewById(R.id.listView3);
        final RadioButton encrypt = (RadioButton) findViewById(R.id.radioButton3);
        encrypt.setChecked(true);
        final LetterNumber ln = new LetterNumber();

        solve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(LetterNumberActivity.this.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                String result;
                resultsList.clear();
                if (ciphertext.getText().toString().equals("")) {
                    resultsList.add(new Result("No ciphertext entered!", false, true));
                } else {
                    result = ln.encrypt(ciphertext.getText().toString().toUpperCase());
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_rot13_cipher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
