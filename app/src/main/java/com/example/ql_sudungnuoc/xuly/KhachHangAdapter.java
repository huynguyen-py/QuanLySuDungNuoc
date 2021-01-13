package com.example.ql_sudungnuoc.xuly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ql_sudungnuoc.dulieu.DbHelper;
import com.example.ql_sudungnuoc.dulieu.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangAdapter {
    private DbHelper myDbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = { DbHelper.KH_MSKH, DbHelper.KH_HOTEN
            , DbHelper.KH_DIENTHOAI, DbHelper.KH_DOITUONG
            , DbHelper.KH_THANHTOAN, DbHelper.KH_KHUVUC};

    public KhachHangAdapter(Context context){
        myDbHelper = new DbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public  long insertKhachHang(KhachHang khachhang){
        db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.KH_MSKH, khachhang.getMskh());
        values.put(DbHelper.KH_HOTEN, khachhang.getHoten());
        values.put(DbHelper.KH_DIENTHOAI, khachhang.getDienthoai());
        values.put(DbHelper.KH_DOITUONG, khachhang.getDoituong());
        values.put(DbHelper.KH_THANHTOAN, khachhang.getThanhtoan());
        values.put(DbHelper.KH_KHUVUC, khachhang.getKhuvuc());

        return db.insert(DbHelper.TABLE_KHACHHANG, null, values);
    }
    public int updateKhachHang(int mskh, String strHoTen, String strDienThoai
            , String strDoiTuong, String strThanhToan, int KhuVuc){
        ContentValues values = new ContentValues();
        values.put(DbHelper.KH_HOTEN, strHoTen);
        values.put(DbHelper.KH_DIENTHOAI, strDienThoai);
        values.put(DbHelper.KH_DOITUONG, strDoiTuong);
        values.put(DbHelper.KH_THANHTOAN, strThanhToan);
        values.put(DbHelper.KH_KHUVUC, KhuVuc);

        return db.update(DbHelper.TABLE_KHACHHANG, values, DbHelper.KH_MSKH + " = "+mskh,
                null);
    }
    public int deleteKhachHang(int mskh){
        return db.delete(DbHelper.TABLE_KHACHHANG
                , DbHelper.KH_MSKH+ " = "+mskh, null);
    }
    private KhachHang cursorToKhachHang(Cursor cursor){
        KhachHang values = new KhachHang();
        values.setMskh(cursor.getInt(0));
        values.setHoten(cursor.getString(1));
        values.setDienthoai(cursor.getString(2));
        values.setDoituong(cursor.getString(3));
        values.setThanhtoan(cursor.getString(4));
        values.setKhuvuc(cursor.getInt(5));
        return values;
    }

    public List<KhachHang> ListAllKhachHang(){
        List<KhachHang> lstKhachHang = new ArrayList<KhachHang>();
        Cursor cursor = db.query(DbHelper.TABLE_KHACHHANG, allColumns, null,
                null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                KhachHang values = cursorToKhachHang(cursor);
                lstKhachHang.add(values);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return lstKhachHang;
    }
    public Boolean KiemTraKH(int ms){
        Boolean daco = false;
        List<KhachHang> lstKhachHang = ListAllKhachHang();
        int i = 0;
        while ((!daco) && (i < lstKhachHang.size())){
            if (lstKhachHang.get(i).getMskh() == ms)
                daco = true;
            else
                i++;
        }
        return daco;
    }
    public List<String> NhanKhuVuc(){
        List<String> arrKhuVuc = new ArrayList<String>();
        String strSelect = "select distinct khuvuc from khachhang order by khuvuc";
        Cursor cursor = db.rawQuery(strSelect, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                arrKhuVuc.add("" + cursor.getInt(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return arrKhuVuc;
    }
    public List<KhachHang> TimKhachHang(boolean chuaghics, int khuvuc, int thang, int nam){
        List<KhachHang> lstKhachHang = new ArrayList<KhachHang>();
        String strSelect = "select * from khachhang where khuvuc = "+khuvuc;
        if (chuaghics)
            strSelect += " And mskh not in (select sd_mskh from sudung where thang ="
                    + thang + " and nam = "+ nam+")";

        Cursor cursor = db.rawQuery(strSelect, null);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                KhachHang values = cursorToKhachHang(cursor);
                lstKhachHang.add(values);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return lstKhachHang;
    }
    public void close(){
        db.close();
        myDbHelper.close();
    }
}
