package com.high.developer.fingerprint_poc.PantallaTemporizada;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.high.developer.fingerprint_poc.MainActivity5;
import com.high.developer.fingerprint_poc.R;

public class cargando3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargando);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent inti = new Intent(cargando3.this, MainActivity5.class);
                startActivity(inti);
            }
        }, 7000);
    }
}