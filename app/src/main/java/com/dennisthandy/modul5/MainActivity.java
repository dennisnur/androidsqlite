package com.dennisthandy.modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.dennisthandy.modul5.Adapters.RecyclerAdapter;
import com.dennisthandy.modul5.Database.DatabaseHelper;
import com.dennisthandy.modul5.Models.Mahasiswa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //deklarasi variabel
    static ArrayList<Mahasiswa> mahasiswaArrayList;
    private Button createButton;
    private RecyclerView recycler;
    private RecyclerAdapter recyclerAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //call init method
        _init();

        //call setupRecycler method
        _setupRecycler();

        //button listenert
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intentToCreate();
            }
        });
    }

    //init method contain assign value of variabel that needed
    private void _init(){

        createButton = findViewById(R.id.tombolTambah);

        recycler = findViewById(R.id.recview);

        databaseHelper = new DatabaseHelper(this);
    }

    //setupRecycler method contain how to set recyclerview works on this activity
    private void _setupRecycler(){

        _readAllBiodata(); //call readAllBiodata

        recyclerAdapter = new RecyclerAdapter(mahasiswaArrayList, this); //make adapter
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this); //make layout manager

        //set into the recyclerview
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(recyclerAdapter);

    }

    //readAllBiodata method contain rule to read all data to database
    private void _readAllBiodata(){

        mahasiswaArrayList = new ArrayList<>(); //make arraylist variabel to store data

        //call method readAll from DatabaseHelper class to get the data
        databaseHelper._readAll(mahasiswaArrayList);
    }

    //intent method contain moving this activity to another activity
    private void _intentToCreate(){

        //from this activity to create activity
        startActivity(new Intent(getApplicationContext(), Create.class));
        finish();
    }
}
