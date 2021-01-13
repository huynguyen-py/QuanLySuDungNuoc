package com.example.ql_sudungnuoc.giaodien;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ql_sudungnuoc.R;
import com.example.ql_sudungnuoc.dulieu.KhachHang;
import com.example.ql_sudungnuoc.dulieu.Publics;
import com.example.ql_sudungnuoc.dulieu.SuDung;
import com.example.ql_sudungnuoc.xuly.SuDungAdapter;

import java.text.DecimalFormat;

public class SuDungNuoc extends AppCompatActivity {

    TextView txtKhachHang;
    EditText edtChiSoMoi, edtThanhTien;
    TextInputLayout layoutChiSoMoi;
    CheckBox chkThanhToan;
    SuDungAdapter sdAdapter;
    SuDung sdHienTai;
    KhachHang khHienTai;
    boolean ghichiso, dathanhtoan;
    int cscu, csmoi, tieuthu;
    double thanhtien;
    DecimalFormat decDinhDang;
    String strThongTin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sudungnuoc);
        txtKhachHang = findViewById(R.id.txt_khachhang);
        edtChiSoMoi = findViewById(R.id.edt_csmoi);
        edtThanhTien = findViewById(R.id.edt_thanhtien);
        chkThanhToan = findViewById(R.id.chk_thanhtoan);

        layoutChiSoMoi = findViewById(R.id.layout_csmoi);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ghichiso = bundle.getBoolean("GhiChiSo");
        khHienTai =(KhachHang) bundle.getSerializable("KhachHang");
        sdAdapter = new SuDungAdapter(this);
        sdHienTai = sdAdapter.getSuDung(khHienTai.getMskh(), Publics.THANG, Publics.NAM);

        if ((sdHienTai != null) && (sdHienTai.isDathanhtoan())){
            Toast.makeText(this, "Khách hàng đã thanh toán", Toast.LENGTH_LONG).show();
            finish();
        }
        if ((sdHienTai == null) && (!ghichiso)){
            Toast.makeText(this, "Lỗi chưa ghi chỉ số mới", Toast.LENGTH_LONG).show();
            finish();
        }
        decDinhDang = new DecimalFormat("#,###.00");
        ThongTinKhachHang();
    }
    private void ThongTinKhachHang(){
        strThongTin = "Họ tên khách hàng: "+ khHienTai.getHoten().toString();
        strThongTin += "\nSố điện thoại: "+ khHienTai.getDienthoai().toString();
        strThongTin += "\nĐối tượng: "+khHienTai.getDoituong().toString();
        strThongTin += "\n Hình thức thanh toán: "+khHienTai.getThanhtoan().toString();
        strThongTin += "\n Thời gian: tháng "+ Publics.THANG + " năm "+ Publics.NAM;

        cscu = sdAdapter.getChiCu(khHienTai.getMskh(), Publics.THANG, Publics.NAM);
        strThongTin += "\n Chỉ số cũ: "+ cscu;
        txtKhachHang.setText(strThongTin);
        if (sdHienTai != null){
            csmoi = sdHienTai.getChisomoi();
            thanhtien = sdHienTai.getThanhtien();
            chkThanhToan.setChecked(sdHienTai.isDathanhtoan());
            edtChiSoMoi.setText(""+ csmoi);
            edtThanhTien.setText(""+decDinhDang.format(thanhtien));
        }else{
            edtChiSoMoi.setText("");
            edtThanhTien.setText("");
        }
        edtChiSoMoi.requestFocus();
    }

    private double getDonGia(String strDoiTuong){
        if (strDoiTuong.equals("Hộ nghèo"))
            return Publics.DGHONGHEO;
        if (strDoiTuong.equals("Hộ bình thường"))
            return Publics.DGBINHTHUONG;
       if (strDoiTuong.equals("Cơ quan"))
            return Publics.DGCOQUAN;
        if (strDoiTuong.equals("Sản xuất"))
            return Publics.DGSANXUAT;
        return Publics.DGKINHDOANH;
    }
    public boolean TinhTien(View view){
        String strCSMoi = edtChiSoMoi.getText().toString();
        if (strCSMoi.length () < 1) {
            layoutChiSoMoi.setError ("Lỗi chưa nhập chi số mới!");
            edtChiSoMoi.requestFocus ();
            edtChiSoMoi.selectAll ();
        return false; }
        else {
            Integer.parseInt (strCSMoi);
            if (csmoi < cscu) {
                layoutChiSoMoi.setError ("Lỗi nhập chi số mới!");
                edtChiSoMoi.requestFocus () ;
                edtChiSoMoi.selectAll ();
            return false;
            }
            else {
                layoutChiSoMoi.setError(null);
                tieuthu = csmoi - cscu;
                double dongia = getDonGia(khHienTai.getDoituong()) + Publics.DGNUOCTHAI;
                thanhtien = tieuthu * dongia;
                String strThanhTien = decDinhDang.format(thanhtien);
                edtThanhTien.setText(strThanhTien);
                return true;
            }
        }
    }
    public void GoiSMS(String sodt, String noidung)
    {
        Intent intent =   new Intent (Intent.ACTION_SENDTO);
        intent = intent.setType ("vnd.android-dir/mns-sms");
        intent.setData (Uri.parse ("smsto:" + sodt));
        intent.putExtra ("sms body", noidung);
        if (intent.resolveActivity (getPackageManager ()) != null)
        {
            startActivity(intent);
        }
    }
    public void LuuThongTin (View view) {
        if (!TinhTien(view)) {
            return;
        }
        dathanhtoan = chkThanhToan.isChecked();
        if (sdHienTai == null) {
            sdHienTai = new SuDung(khHienTai.getMskh(), Publics.THANG, Publics.NAM, cscu, csmoi
                    , thanhtien, dathanhtoan, Publics.MSSV);
            sdAdapter.insertSuDung(sdHienTai);
            Toast.makeText(this, "Đã lưu thông tin sử dụng nước"
                    , Toast.LENGTH_LONG).show();
        } else {
            sdHienTai.setChisomoi(csmoi);
            sdHienTai.setThanhtien(thanhtien);
            sdHienTai.setDathanhtoan(dathanhtoan);
            sdAdapter.updateSuDung(sdHienTai);
            Toast.makeText(this, "Đã cập nhật thông tin sử dụng nước", Toast.LENGTH_LONG).show();
        }
        strThongTin += "\nChi số mới: " + csmoi;
        strThongTin += "\nTiêu thụ: " + tieuthu;
        strThongTin += "\nThành tiền: " + decDinhDang.format(thanhtien);
        if (dathanhtoan)
            strThongTin += "\nKhách hàng đã thanh toán";
        GoiSMS(khHienTai.getDienthoai(), strThongTin);
    }
    public void DongActivity (View view) { finish (); }
}