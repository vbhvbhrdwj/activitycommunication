package com.codekul.interactivitycommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class NextActivity extends AppCompatActivity {

    public static final String KEY_TRANSPORTER_DATA = "transporterData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        String nextName = getNameFromLastActivity();

        assignTextToEditText(nextName);

        initRadioButtons();

        returnProcessing();
    }

    private final String getNameFromLastActivity(){

        Intent intentResponsible =
                getIntent();

        String name = "";

        if(intentResponsible != null) {
            Bundle bundle = intentResponsible
                    .getExtras();
            if(bundle != null){
                name = bundle.getString(MainActivity.KEY_NEXT_NAME);
            }
        }
        return name;
    }

    private final void assignTextToEditText(String text){

        final EditText edtResult = (EditText)
                findViewById(R.id.edtResult);
        edtResult.setText(text);
    }

    private final void initRadioButtons(){

        final CheckChange checkChange = new CheckChange();

        final RadioButton radioAndroid =
                (RadioButton) findViewById(R.id.radioAndroid);
        radioAndroid.setOnCheckedChangeListener(checkChange);

        final RadioButton radioIos = (RadioButton)
                findViewById(R.id.radioiOs);
        radioIos.setOnCheckedChangeListener(checkChange);
    }

    private final void returnProcessing(){

        final Button btnBack =
                (Button) findViewById(R.id.btnBack);

        //anonymus inner class
        if (btnBack != null) {
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.i("@codekul","CLicked");
                    final EditText edtResult = (EditText)
                            findViewById(R.id.edtResult);

                    Intent dataTransporter =
                            new Intent();

                    Bundle bundleTransporter =
                            new Bundle();
                    bundleTransporter.putString(KEY_TRANSPORTER_DATA,
                            edtResult.getText().toString());

                    dataTransporter.putExtras(bundleTransporter);

                    setResult(RESULT_OK,dataTransporter);
                    finish();
                }
            });
        }

    }

    private final class CheckChange implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if(buttonView.getId() == R.id.radioAndroid && isChecked){
                assignTextToEditText("Android");
            }
            else if(buttonView.getId() == R.id.radioiOs && isChecked){
                assignTextToEditText("IOs");
            }
        }
    }

    private final class Click implements  View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }
}