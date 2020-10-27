package com.high.developer.fingerprint_poc.huella;

import android.content.Context;
import android.content.Intent;
import android.os.CancellationSignal;
import android.widget.Toast;

import com.high.developer.fingerprint_poc.PantallaTemporizada.cargando3;


public class FingerprintHelper2 extends android.hardware.fingerprint.FingerprintManager.AuthenticationCallback {

    private Context mContext;

    public FingerprintHelper2(Context mContext) {
        this.mContext = mContext;
    }


    public void startAuth(android.hardware.fingerprint.FingerprintManager manager, android.hardware.fingerprint.FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }
    @Override
    public void onAuthenticationSucceeded(android.hardware.fingerprint.FingerprintManager.AuthenticationResult result) {
        this.showInformationMessage("Autenticacion Exitosa");
        mContext.startActivity(new Intent(mContext, cargando3.class));
    }
    private void showInformationMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    }