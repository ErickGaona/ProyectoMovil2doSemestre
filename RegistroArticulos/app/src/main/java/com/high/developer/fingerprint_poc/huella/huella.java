package com.high.developer.fingerprint_poc.huella;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.high.developer.fingerprint_poc.R;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class huella extends AppCompatActivity {

    //  Variable utilizada para almacenar la clave en el contenedor de Android Keystore
    private KeyStore keyStore;
    private static final String KEY_NAME = "FingerPrintPoC";
    //Encriptar cipher
    private Cipher cipher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Keyguard Manager and Fingerprint Manager para verificar la huella dactillar
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        android.hardware.fingerprint.FingerprintManager fingerprintManager = (android.hardware.fingerprint.FingerprintManager)
                getSystemService(FINGERPRINT_SERVICE);

        //Verifique el sensor de huellas dactilares
        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(this, "\n" + "La autenticación de huellas dactilares no es compatible", Toast.LENGTH_LONG).show();
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "\n" + "El permiso de huella digital no está habilitado.", Toast.LENGTH_SHORT).show();
            } else {
                if (!fingerprintManager.hasEnrolledFingerprints()) {
                    Toast.makeText(this, "\n" + "La huella digital no está configurada.", Toast.LENGTH_LONG).show();
                } else {
                    if (!keyguardManager.isKeyguardSecure()) {
                        Toast.makeText(this, "\n" + "La seguridad de la pantalla de bloqueo no está habilitada en el dispositivo.", Toast.LENGTH_LONG).show();
                    } else {
                        generateKey();

                        if (cipherInit()) {
                            FingerprintHelper2 helper = new FingerprintHelper2(this);
                            android.hardware.fingerprint.FingerprintManager.CryptoObject cryptoObject = new android.hardware.fingerprint.FingerprintManager.CryptoObject(cipher);
                            helper.startAuth(fingerprintManager, cryptoObject);
                        }
                    }
                }
            }
        }
    }

        protected void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException("No se pudo obtener la instancia de KeyGenerator", e);
        }


        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Inicia la encriptacion
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("No se pudo obtener Cipher", e);
        }
        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Fallo la encriptacion", e);
        }
    }
}