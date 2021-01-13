package com.example.ql_sudungnuoc.dulieu;

public class SuDung {
    private int mssd;
    private int mskh;
    private int thang;
    private int nam;
    private int chisocu;
    private int chisomoi;
    private double thanhtien;
    private boolean dathanhtoan;
    private int msnv;

    public SuDung() {
    }

    public SuDung(int mskh, int thang, int nam, int chisocu, int chisomoi, double thanhtien, boolean dathanhtoan, int msnv) {
        this.mskh = mskh;
        this.thang = thang;
        this.nam = nam;
        this.chisocu = chisocu;
        this.chisomoi = chisomoi;
        this.thanhtien = thanhtien;
        this.dathanhtoan = dathanhtoan;
        this.msnv = msnv;
    }

    public int getMssd() {
        return mssd;
    }

    public void setMssd(int mssd) {
        this.mssd = mssd;
    }

    public int getMskh() {
        return mskh;
    }

    public void setMskh(int mskh) {
        this.mskh = mskh;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public int getChisocu() {
        return chisocu;
    }

    public void setChisocu(int chisocu) {
        this.chisocu = chisocu;
    }

    public int getChisomoi() {
        return chisomoi;
    }

    public void setChisomoi(int chisomoi) {
        this.chisomoi = chisomoi;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public boolean isDathanhtoan() {
        return dathanhtoan;
    }

    public void setDathanhtoan(boolean dathanhtoan) {
        this.dathanhtoan = dathanhtoan;
    }

    public int getMsnv() {
        return msnv;
    }

    public void setMsnv(int msnv) {
        this.msnv = msnv;
    }
}
