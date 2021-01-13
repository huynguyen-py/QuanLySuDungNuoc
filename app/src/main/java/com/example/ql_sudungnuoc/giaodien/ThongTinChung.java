package com.example.ql_sudungnuoc.giaodien;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ql_sudungnuoc.R;
import com.example.ql_sudungnuoc.dulieu.Publics;

import java.util.Calendar;

public class ThongTinChung extends AppCompatActivity {

    EditText edtThang, edtNam, edtDGHoNgheo, edtDGBinhThuong, edtDGCoQuan, edtDGSanXuat
            , edtDGKinhDoanh, edtDGNuocThai;
    TextInputLayout layoutThang, layoutNam, layoutDGHoNgheo, layoutDGBinhThuong, layoutDGCoQuan
            , layoutDGSanXuat, layoutDGKinhDoanh, layoutDGNuocThai;
    SharedPreferences pfrThongTin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtinchung);
        edtThang = findViewById(R.id.edt_thang);
        edtNam = findViewById(R.id.edt_nam);
        edtDGHoNgheo = findViewById(R.id.edt_giahongheo);
        edtDGBinhThuong = findViewById(R.id.edt_giabinhthuong);
        edtDGCoQuan = findViewById(R.id.edt_giacoquan);
        edtDGSanXuat = findViewById(R.id.edt_giasanxuat);
        edtDGKinhDoanh = findViewById(R.id.edt_giakinhdoanh);
        edtDGNuocThai = findViewById(R.id.edt_gianuocthai);

        layoutThang = findViewById(R.id.layout_thang);
        layoutNam = findViewById(R.id.layout_nam);
        layoutDGHoNgheo = findViewById(R.id.layout_giahongheo);
        layoutDGBinhThuong = findViewById(R.id.layout_giabinhthuong);
        layoutDGCoQuan = findViewById(R.id.layout_giacoquan);
        layoutDGSanXuat = findViewById(R.id.layout_giasanxuat);
        layoutDGKinhDoanh = findViewById(R.id.layout_kinhdoanh);
        layoutDGNuocThai = findViewById(R.id.layout_gianuocthai);

        pfrThongTin = getSharedPreferences("ttHienTai", MODE_PRIVATE);
        final Calendar cldThoiGian = Calendar.getInstance();

        int strThang = pfrThongTin.getInt("THANG", cldThoiGian.get(Calendar.MONTH)) +1;

        edtThang.setText(""+strThang);
        edtNam.setText(""+ pfrThongTin.getInt("NAM", cldThoiGian.get(Calendar.YEAR)));
        edtDGHoNgheo.setText(""+ pfrThongTin.getFloat("DGHONGHEO", 0));
        edtDGBinhThuong.setText(""+ pfrThongTin.getFloat("DGBINHTHUONG", 0));
        edtDGCoQuan.setText(""+ pfrThongTin.getFloat("DGCOQUAN", 0));
        edtDGSanXuat.setText(""+ pfrThongTin.getFloat("DGSANXUAT", 0));
        edtDGKinhDoanh.setText(""+ pfrThongTin.getFloat("DGKINHDOANH", 0));
        edtDGNuocThai.setText(""+ pfrThongTin.getFloat("DGNUOCTHAI", 0));
    }

    public void ChapNhan(View view){
        String strTemp = edtThang.getText().toString().trim();
        if(strTemp.length() == 0 || Byte.parseByte(strTemp) == 0 || Byte.parseByte(strTemp) >12){
            layoutThang.setError("Lỗi nhập tháng");
            edtThang.requestFocus();
            return;
        }else{
            Publics.THANG = Integer.parseInt(strTemp);
            layoutThang.setError(null);
        }
        strTemp = edtNam.getText().toString().trim();
        if (strTemp.length() == 0 || Integer.parseInt(strTemp)==0){
            layoutNam.setError("Lỗi nhập năm");
            edtNam.requestFocus();
            return;
        }else{
            Publics.NAM = Integer.parseInt(strTemp);
            layoutNam.setError(null);
        }

        strTemp = edtDGHoNgheo.getText().toString().trim();
        if (strTemp.length() == 0 || Float.parseFloat(strTemp)==0){
            layoutDGHoNgheo.setError("Lỗi nhập đơn giá hộ nghèo");
            edtDGHoNgheo.requestFocus();
            return;
        }else{
            Publics.DGHONGHEO = Float.parseFloat(strTemp);
            layoutDGHoNgheo.setError(null);
        }

        strTemp = edtDGBinhThuong.getText().toString().trim();
        if (strTemp.length() == 0 || Float.parseFloat(strTemp)==0){
            layoutDGBinhThuong.setError("Lỗi nhập đơn giá hộ bình thường");
            edtDGBinhThuong.requestFocus();
            return;
        }else{
            Publics.DGBINHTHUONG = Float.parseFloat(strTemp);
            layoutDGBinhThuong.setError(null);
        }

        strTemp = edtDGCoQuan.getText().toString().trim();
        if (strTemp.length() == 0 || Float.parseFloat(strTemp)==0){
            layoutDGCoQuan.setError("Lỗi nhập đơn giá cơ quan");
            edtDGCoQuan.requestFocus();
            return;
        }else{
            Publics.DGCOQUAN = Float.parseFloat(strTemp);
            layoutDGCoQuan.setError(null);
        }

        strTemp = edtDGSanXuat.getText().toString().trim();
        if (strTemp.length() == 0 || Float.parseFloat(strTemp)==0){
            layoutDGSanXuat.setError("Lỗi nhập đơn giá sản xuất");
            edtDGSanXuat.requestFocus();
            return;
        }else{
            Publics.DGSANXUAT = Float.parseFloat(strTemp);
            layoutDGSanXuat.setError(null);
        }
        strTemp = edtDGKinhDoanh.getText().toString().trim();
        if (strTemp.length() == 0 || Float.parseFloat(strTemp)==0){
            layoutDGKinhDoanh.setError("Lỗi nhập đơn giá kinh doanh");
            edtDGKinhDoanh.requestFocus();
            return;
        }else{
            Publics.DGKINHDOANH = Float.parseFloat(strTemp);
            layoutDGKinhDoanh.setError(null);
        }

        strTemp = edtDGNuocThai.getText().toString().trim();
        if (strTemp.length() == 0 || Float.parseFloat(strTemp)==0){
            layoutDGNuocThai.setError("Lỗi nhập đơn giá nước thải");
            edtDGNuocThai.requestFocus();
            return;
        }else{
            Publics.DGNUOCTHAI = Float.parseFloat(strTemp);
            layoutDGNuocThai.setError(null);
        }

        SharedPreferences.Editor editor = pfrThongTin.edit();
        editor.putInt("THANG", Publics.THANG);
        editor.putInt("NAM", Publics.NAM);
        editor.putFloat("DGHONGHEO", Publics.DGHONGHEO);
        editor.putFloat("DGBINHTHUONG", Publics.DGBINHTHUONG);
        editor.putFloat("DGCOQUAN", Publics.DGCOQUAN);
        editor.putFloat("DGSANXUAT", Publics.DGSANXUAT);
        editor.putFloat("DGKINHDOANH", Publics.DGKINHDOANH);
        editor.putFloat("DGNUOCTHAI", Publics.DGNUOCTHAI);
        editor.apply();
        finish();
    }
}