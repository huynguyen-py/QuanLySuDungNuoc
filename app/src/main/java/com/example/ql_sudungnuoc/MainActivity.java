package com.example.ql_sudungnuoc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ql_sudungnuoc.giaodien.QuanLyNhanVien;
import com.example.ql_sudungnuoc.giaodien.ThongTinChung;
import com.example.ql_sudungnuoc.giaodien.DangNhap;
import com.example.ql_sudungnuoc.giaodien.ThongBao;
import com.example.ql_sudungnuoc.giaodien.ThongTinKhachHang;
import com.example.ql_sudungnuoc.xuly.ImageTextAdapter;

public class MainActivity extends AppCompatActivity {

    private Integer[] Images = {R.mipmap.khachhang, R.mipmap.nhanvien,R.mipmap.thongtinchung, R.mipmap.thongbao,
                            R.mipmap.dangnhap, R.mipmap.thoat};
    private String[] Texts = {"Khách hàng", "Nhân viên","Thông tin chung", "Thông báo", "Đăng nhập lại", "Thoát ứng dụng"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(true);
        myActionBar.setIcon(R.mipmap.water);

        GridView gdvMenu = findViewById(R.id.gdv_menu);
        ImageTextAdapter adapter = new ImageTextAdapter(this, R.layout.custom_gridview
                                                            , Images, Texts);

        gdvMenu.setAdapter(adapter);
        gdvMenu.setOnItemClickListener(new ChonCongViec());
    }
    private class ChonCongViec implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 5){
                finish();
            }else{
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent(MainActivity.this, ThongTinKhachHang.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, QuanLyNhanVien.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, ThongTinChung.class);
                    case 3:
                        intent = new Intent(MainActivity.this, ThongBao.class);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, DangNhap.class);
                        break;
                }
                startActivity(intent);
            }
        }
    }
}