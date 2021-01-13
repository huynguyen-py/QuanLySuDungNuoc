package com.example.ql_sudungnuoc.giaodien;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ql_sudungnuoc.R;
import com.example.ql_sudungnuoc.dulieu.KhachHang;
import com.example.ql_sudungnuoc.dulieu.Publics;
import com.example.ql_sudungnuoc.xuly.KhachHangAdapter;
import com.example.ql_sudungnuoc.xuly.KhachHangArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class ThongTinKhachHang extends AppCompatActivity {

    KhachHangAdapter khAdapter;
    KhachHangArrayAdapter khArrayAdapter;
    KhachHang kh;
    List<KhachHang> lstKhachHang;
    ListView lvKhachHang;
    CheckBox chkChuaGhiChiSo;
    Spinner spnKhuVuc;
    ArrayAdapter<String> spnAdapter;
    List<String> arrKhuVuc;
    Button btnThem;
    public static boolean flag_create = true;
    public static final int REQUEST_CODE_CREATE = 10, REQUEST_CODE_UPDATE = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thong_tin_khach_hang);
        khAdapter = new KhachHangAdapter(this);
        lvKhachHang = findViewById(R.id.lst_khachhang);
        chkChuaGhiChiSo = findViewById(R.id.chk_chuaghics);

        spnKhuVuc = findViewById(R.id.spn_khuvuc);
        arrKhuVuc = khAdapter.NhanKhuVuc();
        if (arrKhuVuc.isEmpty())
            arrKhuVuc.add("1");
        spnAdapter = new ArrayAdapter<String>(this
                , android.R.layout.simple_spinner_item, arrKhuVuc);
        spnAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnKhuVuc.setAdapter(spnAdapter);
        spnKhuVuc.setOnItemSelectedListener(new ChonKhuvuc());
        Publics.KHUVUC = Integer.parseInt(arrKhuVuc.get(0));
        spnKhuVuc.setSelection(spnAdapter.getPosition(""+ Publics.KHUVUC));

        registerForContextMenu(lvKhachHang);

        btnThem = findViewById(R.id.btn_them);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinKhachHang.this, ThemSuaKhachHang.class);
                flag_create = true;
                startActivityForResult(intent, REQUEST_CODE_CREATE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CREATE){
            switch (resultCode){
                case ThemSuaKhachHang.RESULT_THEM:
                    Bundle bundle = data.getBundleExtra("BUNDLE_THEM");

                    KhachHang khHienTai = new KhachHang(
                            bundle.getInt("MSKH", 1),
                    bundle.getString("HOTEN", null),
                    bundle.getString("DIENTHOAI", null),
                    bundle.getString("DOITUONG", null),
                    bundle.getString("THANHTOAN", null),
                    bundle.getInt("KHUVUC", 1)

                    );
                    ThemKhachHang(khHienTai);
                    arrKhuVuc = khAdapter.NhanKhuVuc();
                    if (arrKhuVuc.isEmpty())
                        arrKhuVuc.add("1");
                    spnAdapter = new ArrayAdapter<String>(this
                            , android.R.layout.simple_spinner_item, arrKhuVuc);
                    spnAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spnKhuVuc.setAdapter(spnAdapter);
                    spnKhuVuc.setOnItemSelectedListener(new ChonKhuvuc());
                    Publics.KHUVUC = Integer.parseInt(arrKhuVuc.get(0));
                    spnKhuVuc.setSelection(spnAdapter.getPosition(""+ Publics.KHUVUC));
            }
        }else if (requestCode == REQUEST_CODE_UPDATE){
            switch (resultCode){
                case ThemSuaKhachHang.RESULT_SUA:
                    Bundle bundle = data.getBundleExtra("BUNDLE_SUA");

                    KhachHang khHienTai = new KhachHang(
                            bundle.getInt("MSKH", 1),
                            bundle.getString("HOTEN", null),
                            bundle.getString("DIENTHOAI", null),
                            bundle.getString("DOITUONG", null),
                            bundle.getString("THANHTOAN", null),
                            bundle.getInt("KHUVUC", 1)

                    );
                    SuaKhachHang(khHienTai);
                    arrKhuVuc = khAdapter.NhanKhuVuc();
                    if (arrKhuVuc.isEmpty())
                        arrKhuVuc.add("1");
                    spnAdapter = new ArrayAdapter<String>(this
                            , android.R.layout.simple_spinner_item, arrKhuVuc);
                    spnAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spnKhuVuc.setAdapter(spnAdapter);
                    spnKhuVuc.setOnItemSelectedListener(new ChonKhuvuc());
                    Publics.KHUVUC = Integer.parseInt(arrKhuVuc.get(0));
                    spnKhuVuc.setSelection(spnAdapter.getPosition(""+ Publics.KHUVUC));
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.khachhang_contextmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        Boolean ghiChiSo = false;
        KhachHang khHienTai = (KhachHang) lvKhachHang.getItemAtPosition(info.position);
        Bundle bundle = new Bundle();
        if (item.getItemId() == R.id.mnu_ghichiso){
            ghiChiSo = true;
            bundle.putBoolean("GhiChiSo", ghiChiSo);
            bundle.putSerializable("KhachHang", khHienTai);
            Intent intent = new Intent(this, SuDungNuoc.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else if(item.getItemId() == R.id.mnu_chinhsuaKH){
            flag_create = false;
            bundle.putSerializable("KhachHang", khHienTai);
            Intent intent = new Intent(this, ThemSuaKhachHang.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_CODE_UPDATE);
        }else if(item.getItemId() == R.id.mnu_xoaKH){
            AlertDialog.Builder bdrThongBao = new AlertDialog.Builder(this);
            bdrThongBao.setTitle("Xác nhận");
            bdrThongBao.setIcon(R.mipmap.send);
            bdrThongBao.setMessage("Bạn chắc chắn xóa khách hàng này ?");
            bdrThongBao.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ThongTinKhachHang.this, "Đồng ý", Toast.LENGTH_SHORT).show();
                    XoaKhachHang(khHienTai.getMskh());
                    arrKhuVuc = khAdapter.NhanKhuVuc();
                    if (arrKhuVuc.isEmpty())
                        arrKhuVuc.add("1");
                    spnAdapter = new ArrayAdapter<String>(ThongTinKhachHang.this
                            , android.R.layout.simple_spinner_item, arrKhuVuc);
                    spnAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spnKhuVuc.setAdapter(spnAdapter);
                    spnKhuVuc.setOnItemSelectedListener(new ChonKhuvuc());
                    Publics.KHUVUC = Integer.parseInt(arrKhuVuc.get(0));
                    spnKhuVuc.setSelection(spnAdapter.getPosition(""+ Publics.KHUVUC));
                }
            });
            bdrThongBao.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ThongTinKhachHang.this, "Hủy bỏ", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
            bdrThongBao.create().show();
        }

        return super.onContextItemSelected(item);
    }

    public void LocKhachHang(){
        lstKhachHang = khAdapter.TimKhachHang(chkChuaGhiChiSo.isChecked()
                , Publics.KHUVUC, Publics.THANG, Publics.NAM);
        khArrayAdapter = new KhachHangArrayAdapter(this, R.layout.khachhang_list
                , (ArrayList<KhachHang>) lstKhachHang);
        lvKhachHang.setAdapter(khArrayAdapter);
    }


    private class ChonKhuvuc implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Publics.KHUVUC = Integer.parseInt(arrKhuVuc.get(position));
            LocKhachHang();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    public void XoaKhachHang(int mskh){
        khAdapter.deleteKhachHang(mskh);
    }
    public void ThemKhachHang(KhachHang khachHang){
        if (khAdapter.KiemTraKH(khachHang.getMskh())){
            Toast.makeText(this, "Khách hàng đã có trong CSDL"
                    , Toast.LENGTH_SHORT).show();
        }else{
            khAdapter.insertKhachHang(khachHang);
            Toast.makeText(this, "Thêm thành công"
                    , Toast.LENGTH_SHORT).show();
        }
    }
    public void SuaKhachHang(KhachHang khachHang){
        khAdapter.updateKhachHang(khachHang.getMskh(), khachHang.getHoten()
                , khachHang.getDienthoai(), khachHang.getDoituong(), khachHang.getThanhtoan()
        , khachHang.getKhuvuc());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        khAdapter.close();
    }
}