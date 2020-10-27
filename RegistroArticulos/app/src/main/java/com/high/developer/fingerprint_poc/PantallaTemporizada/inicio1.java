package com.high.developer.fingerprint_poc.PantallaTemporizada;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.high.developer.fingerprint_poc.R;
import com.high.developer.fingerprint_poc.huella.huella;

public class inicio1 extends AppCompatActivity {

    //Actividad Temporalizadora

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio1);

       new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent inti = new Intent(inicio1.this, huella.class);
            startActivity(inti);
        }
    }, 7000);
}
}