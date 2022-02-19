package com.example.encodestring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    TextView text, outputText;
    EditText edit_query, inputText, inputPass;
    Button encryptBtn;
    String outputString;
    String AES = "AES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_query = findViewById(R.id.edit_query);
        text = findViewById(R.id.text);
        inputText = findViewById(R.id.inputText);
        inputPass = findViewById(R.id.inputPassword);
        outputText = findViewById(R.id.outputText);
        encryptBtn = findViewById(R.id.buttonEncrypt);
        findViewById(R.id.buttonPanel).setOnClickListener(v -> {
            if (!edit_query.getText().toString().isEmpty()) {
                String encode = Base64.encodeToString(edit_query.getText().toString().getBytes(), Base64.DEFAULT);
                text.setText(encode);
            }
        });

        encryptBtn.setOnClickListener(v -> {
            try {
                outputString = encrypt(inputText.getText().toString(), inputPass.getText().toString());
                outputText.setText(outputString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    private String encrypt(String data, String password) throws Exception {
        SecretKey key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;
    }

    private SecretKey generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }
}