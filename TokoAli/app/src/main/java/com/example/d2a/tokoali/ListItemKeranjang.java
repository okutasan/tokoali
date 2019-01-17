package com.example.d2a.tokoali;

public class ListItemKeranjang {

    private String judul;
    private String harga;
    private String gambar;
    private int total;

    public ListItemKeranjang(String gambar, String judul, String harga, Integer total){
        this.gambar = gambar;
        this.judul = judul;
        this.harga = harga;
        this.total = total;
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

    public Integer getTotal(){return  total;}
}
