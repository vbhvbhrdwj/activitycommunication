package com.codekul.interactivitycommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_NEXT_NAME = "nextName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);



        final EditText edtName =
                (EditText) findViewById(R.id.edtName);

        Click click = new Click();
        final Button btnNext =
                (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(click);
    }

    private final class Click implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnNext) {

                createIntentForGettingDataBack();
            }
        }
    }

    private final void createIntentWithData(){



        final EditText edtName = (EditText)
                findViewById(R.id.edtName);

        Intent intent =
                new Intent(MainActivity.this,NextActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(KEY_NEXT_NAME,
                edtName.getText().toString());

        intent.putExtras(bundle);

        startActivity(intent);

    }

    private final void createIntentForGettingDataBack(){

        final EditText edtName = (EditText)
                findViewById(R.id.edtName);

        Intent intent =
                new Intent(MainActivity.this,NextActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(KEY_NEXT_NAME,
                edtName.getText().toString());

        intent.putExtras(bundle);

        startActivityForResult(intent,1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1234){

            if(resultCode == RESULT_OK){

                Bundle transporterBundle = data.getExtras();

                String result = transporterBundle
                        .getString(NextActivity.KEY_TRANSPORTER_DATA);


                final EditText edtName =
                        (EditText) findViewById(R.id.edtName);
                edtName.setText(result);
            }
        }
    }
}