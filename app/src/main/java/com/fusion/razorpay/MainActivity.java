package com.fusion.razorpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    Toolbar toolbar;
    ImageView imageView;
    TextView textViewTotalAmount;
    Button buttonNetBanking,buttonDebitCreditCards,buttonUPI,buttonPayTmWallet,buttonOtherWallets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Checkout.preload(getApplicationContext());
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView=findViewById(R.id.imageViewBackButton);
        textViewTotalAmount=findViewById(R.id.textViewYourTotalAmount);
        buttonNetBanking=findViewById(R.id.buttonNetBanking);
        buttonDebitCreditCards=findViewById(R.id.buttonDebitCreditCards);
        buttonUPI=findViewById(R.id.buttonUPI);
        buttonPayTmWallet=findViewById(R.id.buttonPayTmWallet);
        buttonOtherWallets=findViewById(R.id.buttonOtherWallets);
        buttonNetBanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=textViewTotalAmount.getText().toString().trim();
                startOpenPayment(""+s);
            }
        });
        buttonDebitCreditCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=textViewTotalAmount.getText().toString().trim();
                startOpenPayment(""+s);
            }
        });
        buttonUPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=textViewTotalAmount.getText().toString().trim();
                startOpenPayment(""+s);
            }
        });
        buttonPayTmWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=textViewTotalAmount.getText().toString().trim();
                startOpenPayment(""+s);
            }
        });
        buttonOtherWallets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=textViewTotalAmount.getText().toString().trim();
                startOpenPayment(""+s);
            }
        });
    }

    private void startOpenPayment(String amount) {
        Checkout checkout=new Checkout();
        checkout.setKeyID("rzp_test_dSotbpH2PoSt8e");
        checkout.setImage(R.drawable.rzp);
        double finalAmount=Float.parseFloat(amount)*100;
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("name","abc Company");
            jsonObject.put("description","description of abc Company");
            jsonObject.put("send_sms_hash",true);
            jsonObject.put("allow_rotation", true);
            jsonObject.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            jsonObject.put("theme.color","#1976D2");
            jsonObject.put("currency","INR");
            jsonObject.put("amount",""+finalAmount);
            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", "9876543210");
            jsonObject.put("prefill", preFill);
            checkout.open(MainActivity.this,jsonObject);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, ""+e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(MainActivity.this, "Payment Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(MainActivity.this, "Payment Failed!", Toast.LENGTH_SHORT).show();
    }
}
