package com.dennisthandy.modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dennisthandy.modul5.Database.DatabaseHelper;

public class Create extends AppCompatActivity {

    //deklarasi variabel
    private Button backButton, insertButton;
    private EditText editTextNrp, editTextNama, editTextAlamat;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //call init method
        _init();

        //button listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intentToMain();
            }
        });

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call insertBiodata
                _insertBiodata();
            }
        });
    }

    //init method contain assign value of variabel that needed
    private void _init(){

        backButton = findViewById(R.id.buttonBack);
        insertButton = findViewById(R.id.buttonSave);

        editTextNrp = findViewById(R.id.inputNRP);
        editTextNama = findViewById(R.id.inputNama);
        editTextAlamat = findViewById(R.id.inputAlamat);

        databaseHelper = new DatabaseHelper(this);
    }

    //intent method contain moving this activity to another activity
    private void _intentToMain(){

        //from this activity to main activity
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    //insertBiodata method contain rule to insert data to database
    private void _insertBiodata(){

        try {
            //deklarasi variabel berserta isinya dari view edittext
            int nrp = Integer.parseInt(editTextNrp.getText().toString());
            String nama = editTextNama.getText().toString();
            String alamat = editTextAlamat.getText().toString();

            databaseHelper._insert(nrp, nama, alamat); //call method insert dari class DatabaseHelper

            _intentToMain(); //call intent method

            //make toast for notif
            Toast.makeText(getApplicationContext(), "Berhasil menambah data", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Log.e("Err", e.getMessage());
        }
    }
}
