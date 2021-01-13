package com.example.ql_sudungnuoc.dulieu;

public class NhanVien {
    private int msnv;
    private String taikhoan;
    private String matkhau;
    private String hoten;
    private String dienthoai;
    private String diachi;
    private String quyensd;

    public NhanVien() {
    }

    public NhanVien(int msnv, String taikhoan, String matkhau, String hoten, String dienthoai, String diachi, String quyensd) {
        this.msnv = msnv;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.dienthoai = dienthoai;
        this.diachi = diachi;
        this.quyensd = quyensd;
    }

    public int getMsnv() {
        return msnv;
    }

    public void setMsnv(int msnv) {
        this.msnv = msnv;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
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

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getQuyensd() {
        return quyensd;
    }

    public void setQuyensd(String quyensd) {
        this.quyensd = quyensd;
    }
}
