package com.dennisthandy.modul5.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dennisthandy.modul5.Models.Mahasiswa;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mahasiswa"; //deklarasi nama database
    private static final int DATABASE_VERSION = 1; //deklarasi versi database
    private static final String TABLE_NAME = "biodata"; //deklarasi nama tabel

    //deklarasi atribut dari tabel
    private static final String KEY_NRP = "nrp";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_ALAMAT = "alamat";

    //deklarasi sql query membuat tabel
    private static final String CREATE_BIODATA_TABLE = "CREATE TABLE " +
                                                TABLE_NAME + "(" + KEY_NRP + " INTEGER PRIMARY KEY, " +
                                                                KEY_NAMA + " TEXT," +
                                                                KEY_ALAMAT + " TEXT);";

    private SQLiteDatabase mydb; //variabel SQLiteDatabase

    //konstruktor dengan satu parameter bertipe Context
    public DatabaseHelper(Context context){

        //ketika dibuat objek baru akan menjalankan sintaks dibawah ini
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //override method onCreate
    @Override
    public void onCreate(SQLiteDatabase db) {

        //mengeksekusi perintah CREATE_BIODATA_TABEL;
        db.execSQL(CREATE_BIODATA_TABLE);

    }

    //override method onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //mengeksekusi perintah drop database jika sudah ada
        db.execSQL("DROP TABLE IF EXISTS '"+ TABLE_NAME +"'");

        //memanggil kembali method onCreate
        onCreate(db);

    }

    //method _insert dengan tiga parameter data
    public long _insert(int nrp, String nama, String alamat){

        mydb = this.getWritableDatabase(); //membuat database writable atau bisa dimanipulasi

        ContentValues data = new ContentValues(); //membuat variabel data berupa objek ContentValues

        //memasukkan tiap nilai parameter ke variabel data bersama dengan key / nama atrribut tabel
        data.put(KEY_NRP, nrp);
        data.put(KEY_NAMA, nama);
        data.put(KEY_ALAMAT, alamat);

        //menggunakan method insert untuk memasukkan data ke dalam tabel,
        //dibutuhkan 3 parameter yaitu nama tabel, nullColumnHack biasa diisi null, dan data berupa objek ContentValues
        long insertData = mydb.insert(TABLE_NAME, null, data);

        return insertData; //return nilai insertData
    }

    //method _readAll untuk membaca semua record dalam tabel
    // dengan sebuah parameter
    public ArrayList<Mahasiswa> _readAll(ArrayList<Mahasiswa> mahasiswaArrayList){

        String sql = "SELECT * FROM " + TABLE_NAME; //sintaks query select
        mydb = this.getReadableDatabase(); //membuat database readable atau bisa dibaca

        Cursor cursor = mydb.rawQuery(sql, null); //membuat objek Cursor untuk melakukan perintah query

        //kondisi jika query berhasil
        if (cursor.moveToFirst()){

            //kondisi ketika cursor masih membaca record dalam tabel
            while (cursor.moveToNext()){
                //maka arraylist akan ditambah / diisi dengan objek Mahasiswa yang berisi data dari tabel
                mahasiswaArrayList.add(new Mahasiswa(
                        cursor.getInt(cursor.getColumnIndex(KEY_NRP)),
                        cursor.getString(cursor.getColumnIndex(KEY_NAMA)),
                        cursor.getString(cursor.getColumnIndex(KEY_ALAMAT))
                        )
                );
            }

        }

        return mahasiswaArrayList; //mengembalikan arraylist yang berisa data dari tabel

    }

    //method _read untuk membaca record dalam tabel berdasarkan kondisi tertentu
    // dengan dua parameter
    public ArrayList<Mahasiswa>  _read(int nrp, ArrayList<Mahasiswa> mahasiswaArrayList){

        String sql = "SELECT * FROM " + TABLE_NAME +" WHERE "+ KEY_NRP +" = "+ nrp; //sintaks query select dengan where

        mydb = this.getReadableDatabase(); //membuat database readable atau bisa dibaca

        Cursor cursor = mydb.rawQuery(sql, null); //membuat objek Cursor untuk melakukan perintah query

        //kondisi jika query berhasil
        if (cursor.moveToFirst()){
            //maka arraylist akan ditambah / diisi dengan objek Mahasiswa yang berisi data dari tabel
            mahasiswaArrayList.add(new Mahasiswa(
                            cursor.getInt(cursor.getColumnIndex(KEY_NRP)),
                            cursor.getString(cursor.getColumnIndex(KEY_NAMA)),
                            cursor.getString(cursor.getColumnIndex(KEY_ALAMAT))
                    )
            );
        }

        return mahasiswaArrayList; //mengembalikan arraylist yang berisa data dari tabel

    }

    //method _update dengan tiga parameter data
    public int _update(int nrp, String nama, String alamat){

        mydb = this.getWritableDatabase(); //membuat database writable atau bisa dimanipulasi

        ContentValues data = new ContentValues(); //membuat variabel data berupa objek ContentValues

        //memasukkan tiap nilai parameter ke variabel data bersama dengan key / nama atrribut tabel
        data.put(KEY_NAMA, nama);
        data.put(KEY_ALAMAT, alamat);

        //menggunakan method update untuk memperbarui sebuah data dari tabel,
        //dibutuhkan 3 parameter yaitu nama tabel, data berupa objek ContentValues, dan kodisi where
        return mydb.update(TABLE_NAME, data, KEY_NRP + " = ?", new String[]{String.valueOf(nrp)});
    }

    //method _delete dengan sebuah parameter
    public void _delete(int nrp){

        mydb = this.getWritableDatabase(); //membuat database writable atau bisa dimanipulasi

        //menggunakan method delete untuk menghapus sebuah data dari tabel,
        //dibutuhkan 2 parameter yaitu nama tabel dan kodisi where
        mydb.delete(TABLE_NAME, KEY_NRP + " = ?", new String[]{String.valueOf(nrp)});

    }
}
