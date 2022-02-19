package com.example.fileencrypt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;

public class EncryptionActivity extends AppCompatActivity {

    private String encryptedFileName = "Encrypted_File.txt";
    static SecretKey yourKey = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);
        try {
            yourKey = SecurityUtil.generateKey();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        saveFile("Simple Test! of encryption and decryption");
        decodeFile();
    }
    void saveFile(String stringToSave) {
        try {
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator, encryptedFileName);
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            byte[] filesBytes = SecurityUtil.encodeFile(yourKey, stringToSave.getBytes());
            bos.write(filesBytes);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void decodeFile() {
        try {
            byte[] decodedData = SecurityUtil.decodeFile(yourKey, readFile());
            String decodedString = new String(decodedData);
            System.out.println("DECODED FILE CONTENTS : " + decodedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public byte[] readFile() {
        byte[] contents = null;
        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator, encryptedFileName);
        int size = (int) file.length();
        contents = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(
                    new FileInputStream(file));
            try {
                buf.read(contents);
                buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return contents;
    }
}