package com.example.ptsganjil202111rpl1lintangandikas17.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Model extends RealmObject {
    @PrimaryKey
    private Integer id;
    private boolean fav;
    private String nama;
    private String tahun;
    private String identitas;
    private String gambar;
    public Model (){}
    public Model( String nama, String tahun, String identitas, String gambar) {

        this.nama = nama;
        this.tahun = tahun;
        this.identitas = identitas;
        this.gambar = gambar;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getIdentitas() {
        return identitas;
    }

    public void setIdentitas(String identitas) {
        this.identitas = identitas;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

}
