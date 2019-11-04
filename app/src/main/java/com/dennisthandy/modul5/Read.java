package com.dennisthandy.modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dennisthandy.modul5.Database.DatabaseHelper;
import com.dennisthandy.modul5.Models.Mahasiswa;

import java.util.ArrayList;

public class Read extends AppCompatActivity {

    //deklarasi variabel
    static ArrayList<Mahasiswa> mahasiswaArrayList;
    private Button backButton;
    private TextView textViewNrp, textViewNama, textViewAlamat;
    private DatabaseHelper databaseHelper;
    private int nrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        //call init method
        _init();

        databaseHelper._read(nrp, mahasiswaArrayList); //call method read dari class DatabaseHelper

        _readBiodata(); //call readBiodata method

        //button listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intentToMain();
            }
        });
    }

    //init method contain assign value of variabel that needed
    private void _init(){

        backButton = findViewById(R.id.buttonBack);

        textViewNrp = findViewById(R.id.dataNRP);
        textViewNama = findViewById(R.id.dataNama);
        textViewAlamat = findViewById(R.id.dataAlamat);

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

        textViewNrp.setText(String.valueOf(mahasiswaArrayList.get(0).getNrp()));
        textViewNama.setText(mahasiswaArrayList.get(0).getNama());
        textViewAlamat.setText(mahasiswaArrayList.get(0).getAlamat());
    }
}
