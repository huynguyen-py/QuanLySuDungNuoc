package com.example.ql_sudungnuoc.giaodien;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ql_sudungnuoc.MainActivity;
import com.example.ql_sudungnuoc.R;
import com.example.ql_sudungnuoc.xuly.NhanVienAdapter;

public class DangNhap extends AppCompatActivity {

    EditText edtTaiKhoan, edtMatKhau;
    TextInputLayout layoutTaiKhoan, layoutMatKhau;
    String strTaiKhoan, strMatKhau;
    NhanVienAdapter nvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(true);
        myActionBar.setIcon(R.mipmap.water);
        edtTaiKhoan = findViewById(R.id.edt_taikhoan);
        edtMatKhau = findViewById(R.id.edt_matkhau);
        layoutTaiKhoan = findViewById(R.id.layout_taikhoan);
        layoutMatKhau = findViewById(R.id.layout_matkhau);
        nvAdapter = new NhanVienAdapter(this);
    }

    public void KiemTraDangNhap(View view){
        strTaiKhoan = edtTaiKhoan.getText().toString().trim();
        if (strTaiKhoan.length() == 0){
            layoutTaiKhoan.setError("Lỗi chưa nhập tài khoản !");
            edtTaiKhoan.requestFocus();
            return;
        }else{
            layoutTaiKhoan.setError(null);
        }
        strMatKhau = edtMatKhau.getText().toString().trim();
        if (strMatKhau.length() == 0){
            layoutMatKhau.setError("Lỗi chưa nhập mật khẩu!");
            edtMatKhau.requestFocus();
            return;
        }else{
            layoutMatKhau.setError(null);
        }

        if(nvAdapter.DangNhap(strTaiKhoan, strMatKhau)){
            layoutMatKhau.setError(null);
            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Lỗi tài khoản hoặc mật khẩu không đúng!"
                    , Toast.LENGTH_LONG).show();
            layoutMatKhau.setError("Lỗi tài khoản hoặc mật khẩu không đúng!");
        }

    }
}