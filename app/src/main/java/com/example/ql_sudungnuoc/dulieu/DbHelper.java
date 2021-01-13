package com.example.ql_sudungnuoc.dulieu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "SuDungNuoc.db";
    private static final int DB_VERSION = 2;
    public static final String TABLE_KHACHHANG = "khachhang";
    public static final String KH_MSKH = "mskh";
    public static final String KH_HOTEN = "hoten";
    public static final String KH_DIENTHOAI = "dienthoai";
    public static final String KH_DOITUONG = "doituong";
    public static final String KH_THANHTOAN= "thanhtoan";
    public static final String KH_KHUVUC = "khuvuc";

    private static final String CREATE_TABLE_KHACHHANG =
            "Create table " + TABLE_KHACHHANG + "("
            + KH_MSKH + " Integer Primary Key Autoincrement, "
            + KH_HOTEN + " Text, "+ KH_DIENTHOAI + " Text, "
            + KH_DOITUONG + " Text, "+ KH_THANHTOAN + " Text, "
            + KH_KHUVUC + " Integer);";

    public static final String TABLE_NHANVIEN = "nhanvien";
    public static final String NV_MSNV = "msnv";
    public static final String NV_TAIKHOAN = "taikhoan";
    public static final String NV_MATKHAU = "matkhau";
    public static final String NV_HOTEN = "hoten";
    public static final String NV_DIENTHOAI = "dienthoai";
    public static final String NV_DIACHI = "diachi";
    public static final String NV_QUYENSD = "quyensd";

    private static final String CREATE_TABLE_NHANVIEN =
            "Create table "+ TABLE_NHANVIEN +" ("
            + NV_MSNV + " Integer Primary Key Autoincrement, "
            + NV_TAIKHOAN + " Text, "+ NV_MATKHAU + " Text, "
            + NV_HOTEN + " Text, "+ NV_DIENTHOAI + " Text, "
            + NV_DIACHI + " Text, "+ NV_QUYENSD + " Text); ";


    public static final String TABLE_SUDUNG = "sudung";
    public static final String SD_MSSD = "mssd";
    public static final String SD_MSKH = "sd_mskh";
    public static final String SD_THANG = "thang";
    public static final String SD_NAM = "nam";
    public static final String SD_CHISOCU = "chisocu";
    public static final String SD_CHISOMOI = "chisomoi";
    public static final String SD_THANHTIEN = "thanhtien";
    public static final String SD_DATHANHTOAN = "dathanhtoan";
    public static final String SD_MSNV = "sd_msnv";

    private static final String CREATE_TABLE_SUDUNG =
            "Create table " + TABLE_SUDUNG + "("
            + SD_MSSD + " Integer Primary Key Autoincrement, "
            + SD_MSKH + " Integer Not Null Constraint sd_mskh References khachhang(mskh), "
            + SD_MSNV + " Integer Not Null Constraint sd_msnv References nhanvien(msnv), "
            + SD_THANG + " Integer, "+ SD_NAM + " Integer, "
            + SD_CHISOCU + " Integer, "+ SD_CHISOMOI + " Integer, "
            + SD_THANHTIEN + " Float, "+ SD_DATHANHTOAN + " Integer);";



    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_KHACHHANG);
        db.execSQL(CREATE_TABLE_NHANVIEN);
        db.execSQL(CREATE_TABLE_SUDUNG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists "+ TABLE_KHACHHANG);
        db.execSQL("Drop Table if exists "+ TABLE_NHANVIEN);
        db.execSQL("Drop Table if exists "+ TABLE_SUDUNG);
        onCreate(db);
    }
}
