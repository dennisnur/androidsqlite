package com.dennisthandy.modul5.Models;

public class Mahasiswa {

    String nama, alamat;
    int nrp;

    public Mahasiswa(int nrp, String nama, String alamat){
        this.nrp = nrp;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getNrp() {
        return nrp;
    }

    public void setNrp(int nrp) {
        this.nrp = nrp;
    }
}
