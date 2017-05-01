package trainedge.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Feedback extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText erfeed, ermail;
    int rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        erfeed = (EditText) findViewById(R.id.er_feed);
        ermail = (EditText) findViewById(R.id.er_mail);
        Button submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(this);
        ermail.addTextChangedListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.submit) {
            submitf();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String email = s.toString();
        if (email.isEmpty() || email.length() < 10 || !email.contains("@") || !email.contains(".com")) {
            ermail.setError("Please give a correct email address.");
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    private void submitf() {
        String str1 = erfeed.getText().toString();
        String str2 = ermail.getText().toString();

        if (str1.isEmpty()) {
            erfeed.setError("Required");
            return;
        }
        if (!str2.contains("@") || str2.isEmpty() || str2.length() < 10) {
            ermail.setError("Please enter a valid email address.");
            return;
        }
        Intent emailint = new Intent(Intent.ACTION_SEND);
        emailint.setType("text/html");
        emailint.putExtra(Intent.EXTRA_EMAIL, new String[]{"thisisut1905@gmail.com"});
        emailint.putExtra(Intent.EXTRA_EMAIL, new String[]{"dixitprashant762@gmail.com"});
        emailint.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        emailint.putExtra(Intent.EXTRA_TEXT, "Hi,\n \t You have got a feedback. And user's thought about the app are - \"" +
                "" + erfeed.getText().toString() + "\". To write him back please use the email \"" +
                "" + ermail.getText().toString() + "\". \n\tHave a nice day. \n\tThank You.");
        startActivity(Intent.createChooser(emailint, "Send feedback using..."));
        erfeed.setText("");
        ermail.setText("");
    }

}