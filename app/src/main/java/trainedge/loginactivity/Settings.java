
package trainedge.loginactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


public class Settings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    public static final String TAG = "Settings";
    private Switch notif;
    private SharedPreferences pref;
    private Switch mode;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pref = getSharedPreferences("setting_pref", MODE_PRIVATE);

        //Button lgt = (Button) findViewById(R.id.logout);

        notif = (Switch) findViewById(R.id.notif);
        notif.setOnCheckedChangeListener(this);
        mode = (Switch) findViewById(R.id.switch_mode);
        mode.setOnCheckedChangeListener(this);


        Spinner spinner = (Spinner) findViewById(R.id.lang);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lang, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        Spinner spinner1 = (Spinner) findViewById(R.id.quality);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.quality, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = (Spinner) findViewById(R.id.font);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.font, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(this);


        updateUI();


        /*SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("is_logged_in",true);
        editor.apply();*/

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences.Editor editor = pref.edit();
        if (buttonView.getId() == R.id.notif) {
            editor.putBoolean("notif_option", isChecked);
            editor.apply();
        }

        if (buttonView.getId() == R.id.switch_mode) {
            // getApplication().setTheme(R.style.BlackTheme);
        } else {
            // getApplication().setTheme(R.style.LightTheme);
        }
        editor.putBoolean("night_mode", isChecked);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show();
    }

    private void updateUI() {
        boolean notifi = pref.getBoolean("notif_option", false);
        notif.setChecked(notifi);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //FirebaseAuth.getInstance().signOut();
    //intent to login activity
    //finish();

}


