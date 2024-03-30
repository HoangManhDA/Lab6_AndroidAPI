package com.example.duan1_pro1121_nhom2.SelectTable;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.duan1_pro1121_nhom2.ClassProduct.LoaiSP;
import com.example.duan1_pro1121_nhom2.ClassProduct.SanPham;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;
import java.util.Calendar;

public class AdapterSelect_SP extends BaseAdapter {
    ArrayList<SanPham> sanPhams;

    public AdapterSelect_SP(ArrayList<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sanpham,parent,false);
        TextView txtRowMaSP,txtRowTenSP,txtRowSLSP,txtRowTrangThai;
        txtRowMaSP = view.findViewById(R.id.txtRowMaSP);
        txtRowTenSP = view.findViewById(R.id.txtRowNameSP);
        txtRowSLSP = view.findViewById(R.id.txtRowSoluongSP);
        txtRowTrangThai = view.findViewById(R.id.txtRowTrangThaiSP);
        SanPham sanPham = sanPhams.get(position);
        int MASP =sanPham.getMASP();
        String TENSP = sanPham.getNAMESP();
        int SLSP_ = sanPham.getSOLUONGNHAP();
        txtRowMaSP.setText(MASP+"");
        txtRowTenSP.setText(TENSP);
        txtRowSLSP.setText(SLSP_+"");
        if (SLSP_==0)
        {
            txtRowTrangThai.setText("Hết Hàng");
            txtRowTrangThai.setTextColor(Color.parseColor("#ED2213"));
        }
        if(SLSP_>0)
        {
            txtRowTrangThai.setText("Còn Hàng");
            txtRowTrangThai.setTextColor(Color.parseColor("#4CAF50"));
        }

        return view;
    }

    @Override
    public int getCount() {
        return sanPhams.size();
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