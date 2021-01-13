package com.example.ql_sudungnuoc.dulieu;

import java.io.Serializable;

public class KhachHang implements Serializable {
    private int mskh;
    private String hoten;
    private String dienthoai;
    private String doituong;
    private String thanhtoan;
    private int khuvuc;

    public KhachHang() {
    }

    public KhachHang(int mskh, String hoten, String dienthoai, String doituong, String thanhtoan, int khuvuc) {
        this.mskh = mskh;
        this.hoten = hoten;
        this.dienthoai = dienthoai;
        this.doituong = doituong;
        this.thanhtoan = thanhtoan;
        this.khuvuc = khuvuc;
    }

    public int getMskh() {
        return mskh;
    }

    public void setMskh(int mskh) {
        this.mskh = mskh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getDoituong() {
        return doituong;
    }

    public void setDoituong(String doituong) {
        this.doituong = doituong;
    }

    public String getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(String thanhtoan) {
        this.thanhtoan = thanhtoan;
    }

    public int getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(int khuvuc) {
        this.khuvuc = khuvuc;
    }
}
