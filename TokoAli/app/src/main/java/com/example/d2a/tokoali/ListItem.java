package com.example.d2a.tokoali;

public class ListItem {

    private String judul;
    private String harga;
    private String gambar;

    public ListItem (String gambar, String judul, String harga){
        this.gambar = gambar;
        this.judul = judul;
        this.harga = harga;
    }

    public String getGambar() {
        return gambar;
    }

    public String getJudul() {
        return judul;
    }

    public String getHarga() {
        return harga;
    }
}
