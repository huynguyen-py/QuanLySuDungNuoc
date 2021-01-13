package com.example.ql_sudungnuoc.giaodien;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ql_sudungnuoc.R;

public class ThongBao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongbao);
    }

    public void HienThiThongBao(View view){
        EditText edtChuDe, edtNguoiNhan, edtDiaChi, edtNoiDung;
        String strChuDe, strNguoiNhan, strDiaChi, strNoiDung, strThongBao;

        edtChuDe = findViewById(R.id.edt_chude);
        edtNguoiNhan = findViewById(R.id.edt_nguoinhan);
        edtDiaChi = findViewById(R.id.edt_diachi);
        edtNoiDung = findViewById(R.id.edt_noidung);

        strChuDe = edtChuDe.getText().toString().trim();
        if( strChuDe.length() < 1){
            edtChuDe.requestFocus();
            edtChuDe.selectAll();
            Toast.makeText(this, "Chủ đề không được rỗng", Toast.LENGTH_SHORT).show();
            return;
        }

        strNguoiNhan = edtNguoiNhan.getText().toString().trim();
        if( strNguoiNhan.length() < 1){
            edtNguoiNhan.requestFocus();
            edtNguoiNhan.selectAll();
            Toast.makeText(this, "Người nhận không được rỗng", Toast.LENGTH_SHORT).show();
            return;
        }


        strDiaChi = edtDiaChi.getText().toString().trim();
        if( strDiaChi.length() < 1){
            edtDiaChi.requestFocus();
            edtDiaChi.selectAll();
            Toast.makeText(this, "Địa chỉ không được rỗng", Toast.LENGTH_SHORT).show();
            return;
        }

        strNoiDung = edtNoiDung.getText().toString().trim();
        if( strNoiDung.length() < 1){
            edtNoiDung.requestFocus();
            edtNoiDung.selectAll();
            Toast.makeText(this, "Nội dung không được rỗng", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder bdrThongBao = new AlertDialog.Builder(this);
        bdrThongBao.setTitle("Gởi thông báo");
        bdrThongBao.setIcon(R.mipmap.send);
        bdrThongBao.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ThongBao.this, "Đồng ý", Toast.LENGTH_SHORT).show();
            }
        });
        bdrThongBao.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ThongBao.this, "Hủy bỏ", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        strThongBao = "Chủ đề: "+ strChuDe + "\n";
        strThongBao += "Người nhận: "+ strNguoiNhan + "\n";
        strThongBao += "Địa chỉ: "+ strDiaChi + "\n";
        strThongBao += "Nội dung: "+ strNoiDung;
        bdrThongBao.setMessage(strThongBao);
        bdrThongBao.create().show();
    }
    public void DongActivity(View view){
        finish();
    }
}