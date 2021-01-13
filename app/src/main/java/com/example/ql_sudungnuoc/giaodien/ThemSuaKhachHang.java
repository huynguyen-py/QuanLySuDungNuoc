package com.example.ql_sudungnuoc.giaodien;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.ql_sudungnuoc.R;
import com.example.ql_sudungnuoc.dulieu.KhachHang;
import com.example.ql_sudungnuoc.dulieu.Publics;
import com.example.ql_sudungnuoc.xuly.SuDungAdapter;

public class ThemSuaKhachHang extends AppCompatActivity {

    EditText edtMskh, edtHoten, edtDienThoai, edtKhuVuc;
    String arrDoiTuong[] = {"Hộ nghèo", "Hộ bình thường", "Cơ quan", "Sản xuất"
            , "Kinh doanh", "Nước thải"};
    Spinner spnDoiTuong;
    int mskh, khuvuc;
    TextInputLayout layoutMskh, layoutHoten, layoutDienthoai, layoutKhuVuc;
    String strHoten, strDienThoai, strDoiTuong, strThanhToan;
    Button btnChapNhanThem, btnChapNhanSua;
    public static final int RESULT_THEM = 1, RESULT_SUA = 2;
    KhachHang khHienTai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themsua_khach_hang);
        edtMskh = findViewById(R.id.edt_mskh);
        edtHoten = findViewById(R.id.edt_hotenKH);
        edtDienThoai = findViewById(R.id.edt_sodienthoaiKH);
        edtKhuVuc = findViewById(R.id.edt_khuvucKH);

        layoutMskh = findViewById(R.id.layout_mskh);
        layoutHoten = findViewById(R.id.layout_hotenKH);
        layoutDienthoai = findViewById(R.id.layout_sodienthoaiKH);
        layoutKhuVuc = findViewById(R.id.layout_khuvucKH);


        spnDoiTuong = findViewById(R.id.spn_doituong);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrDoiTuong);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnDoiTuong.setAdapter(adapter);
        spnDoiTuong.setOnItemSelectedListener(new ChonDoiTuong());

        btnChapNhanThem = findViewById(R.id.btn_chapnhanThem);
        btnChapNhanSua = findViewById(R.id.btn_chapnhanSua);
        btnChapNhanThem.setEnabled(true);
        btnChapNhanSua.setEnabled(false);
        edtMskh.setEnabled(true);

        btnChapNhanThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putInt("MSKH", mskh);
                bundle.putString("HOTEN", strHoten);
                bundle.putString("DIENTHOAI", strDienThoai);
                bundle.putString("DOITUONG", strDoiTuong);
                bundle.putString("THANHTOAN", strThanhToan);
                bundle.putInt("KHUVUC", khuvuc);
                intent.putExtra("BUNDLE_THEM", bundle);
                setResult(RESULT_THEM, intent);
                finish();
            }
        });
        if (ThongTinKhachHang.flag_create == false){
            getBundle();
            btnChapNhanThem.setEnabled(false);
            btnChapNhanSua.setEnabled(true);
            edtMskh.setEnabled(false);
            btnChapNhanSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData();
                    Intent intent = getIntent();
                    Bundle bundle = new Bundle();
                    bundle.putInt("MSKH", mskh);
                    bundle.putString("HOTEN", strHoten);
                    bundle.putString("DIENTHOAI", strDienThoai);
                    bundle.putString("DOITUONG", strDoiTuong);
                    bundle.putString("THANHTOAN", strThanhToan);
                    bundle.putInt("KHUVUC", khuvuc);
                    intent.putExtra("BUNDLE_SUA", bundle);
                    setResult(RESULT_SUA, intent);
                    finish();
                }
            });

        }
    }

    public void getBundle(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        khHienTai =(KhachHang) bundle.getSerializable("KhachHang");


        edtMskh.setText(""+khHienTai.getMskh());
        edtHoten.setText(""+khHienTai.getHoten());
        edtDienThoai.setText(""+khHienTai.getDienthoai());

        for(int i =0; i< arrDoiTuong.length; i++){
            if (arrDoiTuong[i].equals(khHienTai.getDoituong()))
                spnDoiTuong.setSelection(i);
        }
        RadioGroup grpThanhToan = findViewById(R.id.grp_thanhtoan);
        RadioButton rad;
        if (khHienTai.getThanhtoan().equals("Chuyển khoản")){
            rad = findViewById(R.id.rdo_chuyenkhoan);
            rad.setChecked(true);
        }else{
            rad = findViewById(R.id.rdo_tienmat);
            rad.setChecked(true);
        }


        edtKhuVuc.setText(""+khHienTai.getKhuvuc());
    }
    public void getData(){
        String strTemp = edtMskh.getText().toString().trim();
        if(strTemp.length() == 0 || Integer.parseInt(strTemp) <= 0){
            layoutMskh.setError("Lỗi nhập mã số khách hàng");
            edtMskh.requestFocus();
            return;
        }else{
            mskh = Integer.parseInt(strTemp);
            layoutMskh.setError(null);
        }
        strTemp = edtHoten.getText().toString().trim();
        if(strTemp.length() == 0){
            layoutMskh.setError("Lỗi nhập họ tên khách hàng");
            edtHoten.requestFocus();
            return;
        }else{
            strHoten = strTemp;
            layoutHoten.setError(null);
        }

        strTemp = edtDienThoai.getText().toString().trim();
        if(strTemp.length() == 0 || strTemp.length() >10){
            layoutDienthoai.setError("Lỗi nhập số điện thoại");
            edtDienThoai.requestFocus();
            return;
        }else{
            strDienThoai = strTemp;
            layoutDienthoai.setError(null);
        }

        RadioGroup grpThanhToan = findViewById(R.id.grp_thanhtoan);
        int id = grpThanhToan.getCheckedRadioButtonId();
        RadioButton rad = findViewById(id);
        strThanhToan = rad.getText().toString();

        strTemp = edtKhuVuc.getText().toString().trim();
        if(strTemp.length() == 0){
            layoutKhuVuc.setError("Lỗi nhập khu vực");
            edtKhuVuc.requestFocus();
            return;
        }else{
            khuvuc =  Integer.parseInt(strTemp);
            layoutKhuVuc.setError(null);
        }
    }
    private class ChonDoiTuong implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            strDoiTuong = arrDoiTuong[position];
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}