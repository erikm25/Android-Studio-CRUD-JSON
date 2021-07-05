package com.erikmasarrang.tokobuku.Model;

public class DataModel {
    private int id;
    private String judulbuku, penerbitbuku, genrebuku, hargabuku;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudulBuku() {
        return judulbuku;
    }

    public void setJudulBuku(String judulbuku) {
        this.judulbuku = judulbuku;
    }

    public String getPenerbitBuku() {
        return penerbitbuku;
    }

    public void setPenerbitBuku(String penerbitbuku) {
        this.penerbitbuku = penerbitbuku;
    }

    public String getGenreBuku() {
        return genrebuku;
    }

    public void setGenreBuku(String genrebuku) {
        this.genrebuku = genrebuku;
    }

    public String getHargaBuku() {
        return hargabuku;
    }

    public void setHargaBuku(String hargabuku) {
        this.hargabuku = hargabuku;
    }
}
