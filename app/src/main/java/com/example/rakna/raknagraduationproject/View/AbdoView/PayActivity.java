package com.example.rakna.raknagraduationproject.View.AbdoView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rakna.raknagraduationproject.R;

import java.text.DecimalFormat;

public class PayActivity extends AppCompatActivity {

    private TextView numberOfHours, priceOfHour, totalPrice;
    private Button billPayButton;
    private DecimalFormat df ;

    private String totalRaknaPrice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        initWidgets();

        df = new DecimalFormat("0.00");

        savedInstanceState = getIntent().getExtras();

        numberOfHours.setText(savedInstanceState.getString("rakna_time"));
        priceOfHour.setText(savedInstanceState.getString("rakna_in_hour") + " LE");
        totalRaknaPrice = df.format(Double.parseDouble(savedInstanceState.getString("price")));
        totalPrice.setText(totalRaknaPrice + " LE");

        actionWidgets();
    }

    public void initWidgets(){

        numberOfHours = findViewById(R.id.tv_number_of_hours);
        priceOfHour = findViewById(R.id.tv_price_of_hour);
        totalPrice = findViewById(R.id.tv_total_price);

        billPayButton = findViewById(R.id.btn_pay);

    }

    public void actionWidgets(){

        billPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PayActivity.this, PaymentProcessActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
