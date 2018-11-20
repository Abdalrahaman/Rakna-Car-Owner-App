package com.example.rakna.raknagraduationproject.azerCode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rakna.raknagraduationproject.R;

import java.util.Objects;

public class MainActivityazer extends AppCompatActivity {
    EditText pass1;
    EditText pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainazer);

        Button mShowDialog = (Button) findViewById(R.id.okbutton);
        pass1 = (EditText) findViewById(R.id.Password);
        pass2 = (EditText) findViewById(R.id.ConfirmPassword);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Objects.equals(pass1.getText().toString(), pass2.getText().toString())) {
                    Toast.makeText(MainActivityazer.this,
                            "Please enter the correct password",
                            Toast.LENGTH_SHORT).show();

                } else if (pass1.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivityazer.this,
                            "Please enter the correct password",
                            Toast.LENGTH_SHORT).show();
                } else if (pass1.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivityazer.this,
                            "Please enter the correct password",
                            Toast.LENGTH_SHORT).show();
                } else {


                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivityazer.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog, null);
                    final EditText editText = (EditText) mView.findViewById(R.id.code);
                    Button resend = (Button) mView.findViewById(R.id.resend);
                    Button oky = (Button) mView.findViewById(R.id.ok);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    oky.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!editText.getText().toString().isEmpty()) {
                                Intent i = new Intent(MainActivityazer.this, Main2Activityazer.class);
                                startActivity(i);
                                Toast.makeText(MainActivityazer.this,
                                        "verify",
                                        Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {

                                Toast.makeText(MainActivityazer.this,
                                        "Please enter your verification code",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    resend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!editText.getText().toString().isEmpty()) {
                                Toast.makeText(MainActivityazer.this,
                                        "Please wait 30 seconds",
                                        Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(MainActivityazer.this,
                                        "wait",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });
    }
}



