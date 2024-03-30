package com.example.duan1_pro1121_nhom2.SelectTable;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.duan1_pro1121_nhom2.ClassProduct.LoaiSP;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;

public class AdapterSelect_LSP extends BaseAdapter {
    ArrayList<LoaiSP> loaiSPS;
    int MaLoaiSelect;
    public AdapterSelect_LSP(ArrayList<LoaiSP> loaiSPS) {
        this.loaiSPS = loaiSPS;
    }

    public int getMaLoaiSelect() {
        return MaLoaiSelect;
    }

    public void setMaLoaiSelect(int maLoaiSelect) {
        MaLoaiSelect = maLoaiSelect;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_loaisp,parent,false);
        TextView txtRowMaLoaiSP,txtRowTenLoaiSP;
        txtRowMaLoaiSP = view.findViewById(R.id.txtRowMaLoaiSP);
        txtRowTenLoaiSP = view.findViewById(R.id.txtRowNameLoaiSP);
        LoaiSP loaiSP = loaiSPS.get(position);
        int MALOAISP =loaiSP.getMaLoai();
        String TENLOAISP = loaiSP.getTenLoai();

        txtRowMaLoaiSP.setText(MALOAISP+"");
        txtRowTenLoaiSP.setText(TENLOAISP);
        return view;
    }

    @Override
    public int getCount() {
        return loaiSPS.size();
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
