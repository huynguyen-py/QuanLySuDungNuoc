package com.example.ql_sudungnuoc.giaodien;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ql_sudungnuoc.R;
import com.example.ql_sudungnuoc.dulieu.KhachHang;
import com.example.ql_sudungnuoc.dulieu.NhanVien;
import com.example.ql_sudungnuoc.dulieu.Publics;
import com.example.ql_sudungnuoc.xuly.KhachHangArrayAdapter;
import com.example.ql_sudungnuoc.xuly.NhanVienAdapter;
import com.example.ql_sudungnuoc.xuly.NhanVienArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuanLyNhanVien extends AppCompatActivity {

    EditText edtMsnv, edtTaikhoan, edtMatkhau, edtHoten, edtDienthoai, edtDiachi;
    TextInputLayout layoutMsnv, layoutTaikhoan, layoutMatkhau, layoutHoten, layoutDienthoai
            , layoutDiaChi;
    String strMsnv, strTaikhoan, strMatkhau, strHoten, strDienthoai, strDiachi, strQuyensd;
    String[] arrQuyenSD = {"Nhân viên", "Quản lý", "Giám đốc"};
    Spinner spnQuyenSD;
    int msnv;
    NhanVien nv;
    NhanVienAdapter nvAdapter;
    NhanVienArrayAdapter nvArrayAdapter;
    List<NhanVien> lstNhanVien;
    ListView lvNhanVien;
    Button btnThem, btnSua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quan_ly_nhan_vien);

        edtMsnv = findViewById(R.id.edt_msnv);
        edtTaikhoan = findViewById(R.id.edt_taiKhoanNV);
        edtMatkhau = findViewById(R.id.edt_matkhauNV);
        edtHoten = findViewById(R.id.edt_hotenNV);
        edtDienthoai = findViewById(R.id.edt_dienthoaiNV);
        edtDiachi = findViewById(R.id.edt_diachiNV);


        layoutMsnv = findViewById(R.id.layout_msnv);
        layoutTaikhoan = findViewById(R.id.layout_taikhoanNV);
        layoutMatkhau = findViewById(R.id.layout_matkhauNV);
        layoutHoten = findViewById(R.id.layout_hotenNV);
        layoutDienthoai = findViewById(R.id.layout_dienthoaiNV);
        layoutDiaChi = findViewById(R.id.layout_diachiNV);

        btnThem = findViewById(R.id.btn_themNV);
        btnSua = findViewById(R.id.btn_suaNV);
        spnQuyenSD = findViewById(R.id.spn_quyensd);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrQuyenSD);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnQuyenSD.setAdapter(adapter);
        spnQuyenSD.setOnItemSelectedListener(new QuanLyNhanVien.ChonQuyen());


        nvAdapter = new NhanVienAdapter(this);
        lvNhanVien = findViewById(R.id.lst_nhanvien);
        registerForContextMenu(lvNhanVien);
        LocNhanVien();
        setBtnThem();

    }
    public void setBtnThem(){
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTemp = edtMsnv.getText().toString().trim();
                if(strTemp.length() == 0 || Integer.parseInt(strTemp) <= 0){
                    layoutMsnv.setError("Lỗi nhập mã số nhân viên");
                    edtMsnv.requestFocus();
                    return;
                }else{
                    msnv = Integer.parseInt(strTemp);
                    layoutMsnv.setError(null);
                }

                strTemp = edtTaikhoan.getText().toString().trim();
                if(strTemp.length() == 0){
                    layoutTaikhoan.setError("Lỗi nhập tài khoản");
                    edtTaikhoan.requestFocus();
                    return;
                }else{
                    strTaikhoan = strTemp;
                    layoutTaikhoan.setError(null);
                }

                strTemp = edtMatkhau.getText().toString().trim();
                if(strTemp.length() == 0){
                    layoutMatkhau.setError("Lỗi nhập mật khẩu");
                    edtMatkhau.requestFocus();
                    return;
                }else{
                    strMatkhau = strTemp;
                    layoutMatkhau.setError(null);
                }
                strTemp = edtHoten.getText().toString().trim();
                if(strTemp.length() == 0){
                    layoutHoten.setError("Lỗi nhập họ tên nhân viên");
                    edtHoten.requestFocus();
                    return;
                }else{
                    strHoten = strTemp;
                    layoutHoten.setError(null);
                }

                strTemp = edtDienthoai.getText().toString().trim();
                if(strTemp.length() == 0 || strTemp.length() >10){
                    layoutDienthoai.setError("Lỗi nhập số điện thoại");
                    edtDienthoai.requestFocus();
                    return;
                }else{
                    strDienthoai = strTemp;
                    layoutDienthoai.setError(null);
                }


                strTemp = edtDiachi.getText().toString().trim();
                if(strTemp.length() == 0){
                    layoutDiaChi.setError("Lỗi nhập địa chỉ");
                    edtDiachi.requestFocus();
                    return;
                }else{
                    strDiachi = strTemp;
                    layoutDiaChi.setError(null);
                }

                NhanVien nv = new NhanVien(msnv, strTaikhoan, strMatkhau, strHoten
                        , strDienthoai, strDiachi, strQuyensd);
                ThemNhanVien(nv);
                LocNhanVien();
            }
        });
    }


    public void LocNhanVien(){
        lstNhanVien = nvAdapter.ListAllNhanVien();
        nvArrayAdapter = new NhanVienArrayAdapter(this, R.layout.nhanvien_list
                , (ArrayList<NhanVien>) lstNhanVien);
        lvNhanVien.setAdapter(nvArrayAdapter);
    }

    public void XoaNhanVien(int msnv){
        nvAdapter.deleteNhanVien(msnv);
    }
    public void ThemNhanVien(NhanVien nv){
        if (nvAdapter.kiemTraNV(nv.getMsnv())){
            Toast.makeText(this, "Nhân viên đã có trong CSDL"
                    , Toast.LENGTH_SHORT).show();
        }else{
            nvAdapter.insertNhanVien(nv);
            Toast.makeText(this, "Thêm thành công"
                    , Toast.LENGTH_SHORT).show();
        }
    }
    public void SuaNhanVien(NhanVien nv){
        nvAdapter.updateNhanVien(nv.getMsnv(), nv.getTaikhoan(), nv.getMatkhau(), nv.getHoten()
        , nv.getDienthoai(), nv.getDiachi(), nv.getQuyensd());
        nvAdapter.insertNhanVien(nv);
        Toast.makeText(this, "Sửa thành công"
                , Toast.LENGTH_SHORT).show();
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        edtMsnv.setEnabled(true);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nhanvien_contextmenu, menu);
    }
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        NhanVien nvHientai = (NhanVien) lvNhanVien.getItemAtPosition(info.position);
        Bundle bundle = new Bundle();
        if (item.getItemId() == R.id.mnu_xoaNV) {
            AlertDialog.Builder bdrThongBao = new AlertDialog.Builder(this);
            bdrThongBao.setTitle("Xác nhận");
            bdrThongBao.setIcon(R.mipmap.send);
            bdrThongBao.setMessage("Bạn chắc chắn sa thải nhân viên này ?");
            bdrThongBao.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(QuanLyNhanVien.this, "Đồng ý", Toast.LENGTH_SHORT).show();
                    XoaNhanVien(nvHientai.getMsnv());
                    LocNhanVien();
                }
            });
            bdrThongBao.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(QuanLyNhanVien.this, "Hủy bỏ", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
            bdrThongBao.create().show();
        }else  if (item.getItemId() == R.id.mnu_chinhsuaNV){
            btnThem.setEnabled(false);
            btnSua.setEnabled(true);
            edtMsnv.setEnabled(false);
            edtMsnv.setText(""+ nvHientai.getMsnv());
            edtTaikhoan.setText(""+ nvHientai.getTaikhoan());
            edtMatkhau.setText(""+ nvHientai.getMatkhau());
            edtHoten.setText(""+ nvHientai.getHoten());
            edtDienthoai.setText(""+ nvHientai.getDienthoai());
            edtDiachi.setText(""+ nvHientai.getDiachi());
            for(int i = 0; i < arrQuyenSD.length; i++){
                if (arrQuyenSD[i].equals(nvHientai.getQuyensd()))
                    spnQuyenSD.setSelection(i);
            }

            btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strTemp = edtMsnv.getText().toString().trim();
                    if(strTemp.length() == 0 || Integer.parseInt(strTemp) <= 0){
                        layoutMsnv.setError("Lỗi nhập mã số nhân viên");
                        edtMsnv.requestFocus();
                        return;
                    }else{
                        msnv = Integer.parseInt(strTemp);
                        layoutMsnv.setError(null);
                    }

                    strTemp = edtTaikhoan.getText().toString().trim();
                    if(strTemp.length() == 0){
                        layoutTaikhoan.setError("Lỗi nhập tài khoản");
                        edtTaikhoan.requestFocus();
                        return;
                    }else{
                        strTaikhoan = strTemp;
                        layoutTaikhoan.setError(null);
                    }

                    strTemp = edtMatkhau.getText().toString().trim();
                    if(strTemp.length() == 0){
                        layoutMatkhau.setError("Lỗi nhập mật khẩu");
                        edtMatkhau.requestFocus();
                        return;
                    }else{
                        strMatkhau = strTemp;
                        layoutMatkhau.setError(null);
                    }
                    strTemp = edtHoten.getText().toString().trim();
                    if(strTemp.length() == 0){
                        layoutHoten.setError("Lỗi nhập họ tên nhân viên");
                        edtHoten.requestFocus();
                        return;
                    }else{
                        strHoten = strTemp;
                        layoutHoten.setError(null);
                    }

                    strTemp = edtDienthoai.getText().toString().trim();
                    if(strTemp.length() == 0 || strTemp.length() >10){
                        layoutDienthoai.setError("Lỗi nhập số điện thoại");
                        edtDienthoai.requestFocus();
                        return;
                    }else{
                        strDienthoai = strTemp;
                        layoutDienthoai.setError(null);
                    }


                    strTemp = edtDiachi.getText().toString().trim();
                    if(strTemp.length() == 0){
                        layoutDiaChi.setError("Lỗi nhập địa chỉ");
                        edtDiachi.requestFocus();
                        return;
                    }else{
                        strDiachi = strTemp;
                        layoutDiaChi.setError(null);
                    }

                    NhanVien nv = new NhanVien(msnv, strTaikhoan, strMatkhau, strHoten
                            , strDienthoai, strDiachi, strQuyensd);
                    SuaNhanVien(nv);
                    LocNhanVien();
                }
            });
        }
        return super.onContextItemSelected(item);
    }
    private class ChonQuyen implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strQuyensd = arrQuyenSD[position];
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nvAdapter.close();
    }
}