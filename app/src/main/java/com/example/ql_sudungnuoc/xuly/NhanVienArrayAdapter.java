package com.example.ql_sudungnuoc.xuly;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ql_sudungnuoc.R;
import com.example.ql_sudungnuoc.dulieu.KhachHang;
import com.example.ql_sudungnuoc.dulieu.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienArrayAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int layoutID;
    ArrayList<NhanVien> nvArray;

    public NhanVienArrayAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<NhanVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.nvArray = objects;
    }
    public void setKhArray(ArrayList<NhanVien> khArray){
        this.nvArray = khArray;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return nvArray.size();
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);
        if (nvArray.size() > 0 && position >= 0 ){
            final  NhanVien nv = nvArray.get(position);
            final TextView txtSTT = convertView.findViewById(R.id.txt_sttNV);
            txtSTT.setText(""+(position+1));
            final  TextView txtHoTen = convertView.findViewById(R.id.txt_hotenNV);
            txtHoTen.setText(nv.getHoten());
            final  TextView txtTaiKhoan = convertView.findViewById(R.id.txt_taikhoanNV);
            txtTaiKhoan.setText(nv.getTaikhoan());
            final  TextView txtQuyenSD = convertView.findViewById(R.id.txt_quyensd);
            txtQuyenSD.setText(nv.getQuyensd());
        }
        return convertView;
    }
}
