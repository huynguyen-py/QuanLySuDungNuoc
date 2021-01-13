package com.example.ql_sudungnuoc.xuly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ql_sudungnuoc.dulieu.DbHelper;
import com.example.ql_sudungnuoc.dulieu.KhachHang;
import com.example.ql_sudungnuoc.dulieu.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienAdapter {
    private DbHelper myDbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = { DbHelper.NV_MSNV, DbHelper.NV_TAIKHOAN
            , DbHelper.NV_MATKHAU, DbHelper.NV_HOTEN
            , DbHelper.NV_DIENTHOAI, DbHelper.NV_DIACHI, DbHelper.NV_QUYENSD};



    public NhanVienAdapter(Context context){
        myDbHelper = new DbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public  long insertNhanVien(NhanVien nv){
        db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.NV_MSNV, nv.getMsnv());
        values.put(DbHelper.NV_TAIKHOAN, nv.getTaikhoan());
        values.put(DbHelper.NV_MATKHAU, nv.getMatkhau());
        values.put(DbHelper.NV_HOTEN, nv.getHoten());
        values.put(DbHelper.NV_DIENTHOAI, nv.getDienthoai());
        values.put(DbHelper.NV_DIACHI, nv.getDiachi());
        values.put(DbHelper.NV_QUYENSD, nv.getQuyensd());

        return db.insert(DbHelper.TABLE_NHANVIEN, null, values);
    }
    public int updateNhanVien(int msnv, String strTaiKhoan, String strMatKhau, String strHoTen
            , String strDienThoai, String strDiaChi, String strQuyenSD){
        ContentValues values = new ContentValues();
        values.put(DbHelper.NV_MSNV, msnv);
        values.put(DbHelper.NV_TAIKHOAN, strTaiKhoan);
        values.put(DbHelper.NV_MATKHAU, strMatKhau);
        values.put(DbHelper.NV_HOTEN, strHoTen);
        values.put(DbHelper.NV_DIENTHOAI, strDienThoai);
        values.put(DbHelper.NV_DIACHI, strDiaChi);
        values.put(DbHelper.NV_QUYENSD, strQuyenSD);

        return db.update(DbHelper.TABLE_NHANVIEN, values, DbHelper.NV_MSNV + " = "+ msnv,
                null);
    }
    public int deleteNhanVien(int msnv){
        return db.delete(DbHelper.TABLE_NHANVIEN
                , DbHelper.NV_MSNV+ " = "+msnv, null);
    }

    private NhanVien cursorToNhanVien(Cursor cursor){
        NhanVien values = new NhanVien();
        values.setMsnv(cursor.getInt(0));
        values.setTaikhoan(cursor.getString(1));
        values.setMatkhau(cursor.getString(2));
        values.setHoten(cursor.getString(3));
        values.setDienthoai(cursor.getString(4));
        values.setDiachi(cursor.getString(5));
        values.setQuyensd(cursor.getString(6));
        return values;
    }

    public List<NhanVien> ListAllNhanVien(){
        List<NhanVien> lstNhanVien = new ArrayList<NhanVien>();
        Cursor cursor = db.query(DbHelper.TABLE_NHANVIEN, allColumns, null,
                null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                NhanVien values = cursorToNhanVien(cursor);
                lstNhanVien.add(values);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return lstNhanVien;
    }
    public Boolean kiemTraNV(int ms){
        Boolean daco = false;
        List<NhanVien> lstNhanVien = ListAllNhanVien();
        int i = 0;
        while ((!daco) && (i < lstNhanVien.size())){
            if (lstNhanVien.get(i).getMsnv() == ms)
                daco = true;
            else
                i++;
        }
        return daco;
    }
    public Boolean DangNhap(String taikhoan, String matkhau){
        Boolean daco = false;
        List<NhanVien> lstNhanVien = ListAllNhanVien();
        int i = 0;
        while ((!daco) && (i < lstNhanVien.size())){
            if (lstNhanVien.get(i).getTaikhoan().equals(taikhoan)
                    && lstNhanVien.get(i).getMatkhau().equals(matkhau) )
                daco = true;
            else
                i++;
        }
        return daco;
    }


    public void close(){
        db.close();
        myDbHelper.close();
    }


}
