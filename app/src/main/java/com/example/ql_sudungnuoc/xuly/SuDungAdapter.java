package com.example.ql_sudungnuoc.xuly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ql_sudungnuoc.dulieu.DbHelper;
import com.example.ql_sudungnuoc.dulieu.SuDung;

import java.util.ArrayList;
import java.util.List;

public class SuDungAdapter {
    private DbHelper myDbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = {
            DbHelper.SD_MSSD, DbHelper.SD_MSKH, DbHelper.SD_THANG, DbHelper.SD_NAM
            , DbHelper.SD_CHISOCU, DbHelper.SD_CHISOMOI, DbHelper.SD_THANHTIEN
            , DbHelper.SD_DATHANHTOAN, DbHelper.SD_MSNV
    };
    public SuDungAdapter(Context context){
        myDbHelper = new DbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }
    public long insertSuDung(SuDung sudung){
        ContentValues values = new ContentValues();
        values.put(DbHelper.SD_MSKH, sudung.getMskh());
        values.put(DbHelper.SD_THANG, sudung.getThang());
        values.put(DbHelper.SD_NAM, sudung.getNam());
        values.put(DbHelper.SD_CHISOCU, sudung.getChisocu());
        values.put(DbHelper.SD_CHISOMOI, sudung.getChisomoi());
        values.put(DbHelper.SD_THANHTIEN, sudung.getThanhtien());
        values.put(DbHelper.SD_DATHANHTOAN, sudung.isDathanhtoan());
        values.put(DbHelper.SD_MSNV, sudung.getMsnv());
        return db.insert(DbHelper.TABLE_SUDUNG, null, values);
    }
    public int updateSuDung(SuDung sudung){
        ContentValues values = new ContentValues();
        values.put(DbHelper.SD_MSKH, sudung.getMskh());
        values.put(DbHelper.SD_THANG, sudung.getThang());
        values.put(DbHelper.SD_NAM, sudung.getNam());
        values.put(DbHelper.SD_CHISOCU, sudung.getChisocu());
        values.put(DbHelper.SD_CHISOMOI, sudung.getChisomoi());
        values.put(DbHelper.SD_THANHTIEN, sudung.getThanhtien());
        values.put(DbHelper.SD_DATHANHTOAN, sudung.isDathanhtoan());
        values.put(DbHelper.SD_MSNV, sudung.getMsnv());
        return db.update(DbHelper.TABLE_SUDUNG, values, DbHelper.SD_MSSD + " = "
                +sudung.getMssd(), null);
    }

    private SuDung cursorToSuDung(Cursor cursor){
        SuDung value = new SuDung();
        value.setMssd(cursor.getInt(0));
        value.setMskh(cursor.getInt(1));
        value.setThang(cursor.getInt(2));
        value.setNam(cursor.getInt(3));
        value.setChisocu(cursor.getInt(4));
        value.setChisomoi(cursor.getInt(5));
        value.setThanhtien(cursor.getDouble(6));
        value.setDathanhtoan(cursor.getString(7).equals("1"));
        value.setMsnv(cursor.getInt(8));
        return value;
    }
    public SuDung getSuDung(int mskh, int thang, int nam){
        SuDung kq = null;
        Cursor cursor = db.query(DbHelper.TABLE_SUDUNG, allColumns, DbHelper.SD_MSKH + "="
                        + mskh + " And " + DbHelper.SD_THANG + " = "+ thang + " And "
                        + DbHelper.SD_NAM + " = " + nam, null, null
                , null, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            kq = cursorToSuDung(cursor);
        }
        cursor.close();
        return kq;
    }

    public int getChiCu(int mskh, int thang, int nam){
        int kq = 0;
        thang--;
        if (thang == 0){
            thang = 12;
            nam--;
        }
        String strSelect = "Select chisomoi from sudung where sd_mskh = "+ mskh
                + " and thang=" +thang + " and nam =" +nam;
        Cursor cursor = db.rawQuery(strSelect, null);
        if(cursor.getCount() >0){
            cursor.moveToFirst();
            kq = cursor.getInt(0);
        }
        cursor.close();
        return kq;
    }

    public List<SuDung> SuDung_TheoThang(int thang, int nam){
        List<SuDung> lstSuDung = new ArrayList<SuDung>();
        Cursor cursor = db.query(DbHelper.TABLE_SUDUNG, allColumns, DbHelper.SD_THANG
                        + " = " + thang + " and " + DbHelper.SD_NAM + "=" + nam
                , null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                SuDung values = cursorToSuDung(cursor);
                lstSuDung.add(values);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return lstSuDung;
    }

    public boolean DaThanhToan(int mskh, int thang, int nam){
        boolean kq = false;
        String strSelect = "Select dathanhtoan from sudung where sd_mskh =" +mskh+" and thang ="+ thang+
                " and nam ="+nam;
        Cursor cursor = db.rawQuery(strSelect, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            kq = cursor.getString(0).equals("1");
        }
        cursor.close();
        return kq;
    }
}
