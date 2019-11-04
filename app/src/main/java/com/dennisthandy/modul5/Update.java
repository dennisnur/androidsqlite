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
import com.dennisthandy.modul5.Models.Mahasiswa;

import java.util.ArrayList;

public class Update extends AppCompatActivity {

    //deklarasi variabel
    static ArrayList<Mahasiswa> mahasiswaArrayList;
    private Button backButton, updateButton;
    private EditText editTextNrp, editTextNama, editTextAlamat;
    private DatabaseHelper databaseHelper;
    private int nrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //call init method
        _init();

        databaseHelper._read(nrp, mahasiswaArrayList); //call method read dari class DatabaseHelper

        _readBiodata(); //call readBiodata

        //button listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intentToMain();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //call updateBiodata
                _updateBiodata();
            }
        });
    }

    //init method contain assign value of variabel that needed
    private void _init(){

        backButton = findViewById(R.id.buttonBack);
        updateButton = findViewById(R.id.buttonEdit);

        editTextNrp = findViewById(R.id.inputNRP);
        editTextNama = findViewById(R.id.inputNama);
        editTextAlamat = findViewById(R.id.inputAlamat);

        mahasiswaArrayList = new ArrayList<>();

        databaseHelper = new DatabaseHelper(this);

        nrp = getIntent().getIntExtra("id",0);
    }

    //intent method contain moving this activity to another activity
    private void _intentToMain(){

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    //readBiodata method contain rule how to display data to the app
    private void _readBiodata(){

        editTextNrp.setText(String.valueOf(mahasiswaArrayList.get(0).getNrp()));
        editTextNama.setText(mahasiswaArrayList.get(0).getNama());
        editTextAlamat.setText(mahasiswaArrayList.get(0).getAlamat());
    }

    private void _updateBiodata(){

        try{

            String nrp = editTextNrp.getText().toString();
            String nama = editTextNama.getText().toString();
            String alamat = editTextAlamat.getText().toString();

            databaseHelper._update(Integer.parseInt(nrp), nama, alamat);
            _intentToMain();
            Toast.makeText(getApplicationContext(), "Berhasil memperbarui data", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }
    }

}
