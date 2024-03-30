package com.example.duan1_pro1121_nhom2.SelectTable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duan1_pro1121_nhom2.ClassProduct.KhachHang;
import com.example.duan1_pro1121_nhom2.ClassProduct.LoaiSP;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterSelect_KH extends BaseAdapter {
    ArrayList<KhachHang> khachHangs;
    public AdapterSelect_KH(ArrayList<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_kh,parent,false);
        TextView txtRowMaKH,txtRowTenKH,txtRowGioiTinh,txtRowPhoneKH;
        CircleImageView circleImageView ;
        txtRowMaKH = view.findViewById(R.id.txtRowMaKH_SELECT);
        txtRowTenKH = view.findViewById(R.id.txtRowTenKH_SELECT);
        txtRowGioiTinh = view.findViewById(R.id.txtRowGioiTinhKH_SELECT);
        txtRowPhoneKH = view.findViewById(R.id.txtRowPhoneKH_SELECT);
        circleImageView = view.findViewById(R.id.RowImageKH_SELECT);
        KhachHang khachHang = khachHangs.get(position);
        int MAKH = khachHang.getMaKH();
        String TENKH = khachHang.getTenKH();
        int NumberPhone = khachHang.getNumberPhone();
        String DiaChi = khachHang.getDiaChi();
        String GioiTinh = khachHang.getGioiTinh();
        String NgaySinh = khachHang.getNgaySinh();
        int IMGKH = khachHang.getImageKH();
        txtRowMaKH.setText("Mã Khách Hàng: "+MAKH+"");
        txtRowTenKH.setText("Tên Khách Hàng: "+TENKH);
        txtRowGioiTinh.setText("Giới Tính: "+GioiTinh);
        txtRowPhoneKH.setText("NumberPhone: "+NumberPhone);
        circleImageView.setImageResource(IMGKH);
        return view;
    }

    @Override
    public int getCount() {
        return khachHangs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}