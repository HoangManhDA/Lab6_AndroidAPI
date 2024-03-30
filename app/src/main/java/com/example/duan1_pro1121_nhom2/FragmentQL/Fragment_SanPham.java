package com.example.duan1_pro1121_nhom2.FragmentQL;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.duan1_pro1121_nhom2.AdapteData.LoaiSP_Adapter;
import com.example.duan1_pro1121_nhom2.AdapteData.SanPham_Adapter;
import com.example.duan1_pro1121_nhom2.ClassProduct.LoaiSP;
import com.example.duan1_pro1121_nhom2.ClassProduct.SanPham;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.LoginAccount.StepDangKi;
import com.example.duan1_pro1121_nhom2.R;
import com.example.duan1_pro1121_nhom2.SelectTable.AdapterSelect_LSP;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_SanPham extends Fragment {
    int MALOAISP_;

    public int getMALOAISP_() {
        return MALOAISP_;
    }

    public void setMALOAISP_(int MALOAISP_) {
        this.MALOAISP_ = MALOAISP_;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_sp,container,false);
        ListView lvDanhSach;
        Button btnAddSanPham;
        lvDanhSach = view.findViewById(R.id.lvSP);
        btnAddSanPham = view.findViewById(R.id.btnAddSP);
        SQLife sqLife = new SQLife(view.getContext());
        ArrayList<SanPham> sanPhams = sqLife.getALLSP();
        lvDanhSach.setAdapter(new SanPham_Adapter(sanPhams));
        btnAddSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View viewShowSP = inflater.inflate(R.layout.show_add_sp,container,false);
                builder.setView(viewShowSP);
                Dialog dialog = builder.create();
                dialog.show();
                EditText edTenSP,edGiaNhap,edSLN;
                TextView tvNgayNhap,tvMaLoai;
                Button btnChonNgay,btnThemMaLoai,btnSave;
                edTenSP = viewShowSP.findViewById(R.id.ed_TenSP_Add);
                edSLN = viewShowSP.findViewById(R.id.ed_SLN_Add);
                edGiaNhap = viewShowSP.findViewById(R.id.ed_GiaNhap_Add);
                tvNgayNhap = viewShowSP.findViewById(R.id.tvNgayNhap_Add);
                tvMaLoai = viewShowSP.findViewById(R.id.tvMaLoai_Add);
                btnChonNgay = viewShowSP.findViewById(R.id.btnChonNN_SP_Add);
                btnThemMaLoai = viewShowSP.findViewById(R.id.btnAddLSP_SP_Add);
                btnSave = viewShowSP.findViewById(R.id.btnSaveSP);

                btnThemMaLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                        View view1 = LayoutInflater.from(v.getContext()).inflate(R.layout.select_table_maloai,container,false);
                        builder1.setView(view1);
                        Dialog dialog1 = builder1.create();
                        dialog1.show();
                        SQLife sqLife = new SQLife(view1.getContext());
                        ArrayList<LoaiSP> loaiSPS = sqLife.getALL_LSP();
                        AdapterSelect_LSP adapterSelect_lsp = new AdapterSelect_LSP(loaiSPS);
                        ListView lvShowLoaiSP = view1.findViewById(R.id.lvShowSelectLSP);
                        lvShowLoaiSP.setAdapter(adapterSelect_lsp);
                        lvShowLoaiSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(parent.getContext(), "Position "+position, Toast.LENGTH_SHORT).show();
                                tvMaLoai.setText(loaiSPS.get(position).getMaLoai()+"");
                                dialog1.dismiss();
                            }
                        });

                    }
                });
                btnChonNgay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis( System.currentTimeMillis() );


                        DatePickerDialog dialog = new DatePickerDialog(
                                v.getContext(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                                        int nam = i;
                                        int thang = i1;
                                        int ngay = i2;

                                        tvNgayNhap.setText(ngay + " - " + (thang + 1) + " - " + nam);
                                    }
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DATE)
                        );

                        dialog.show();
                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String TenSP = edTenSP.getText().toString();
                            int SLN =Integer.parseInt(edSLN.getText().toString());
                            int GIANHAP =Integer.parseInt(edGiaNhap.getText().toString());
                            String NgayNhap = tvNgayNhap.getText().toString();
                            int MALOAI = Integer.parseInt(tvMaLoai.getText().toString());

                            SanPham sanPham = new SanPham(0,TenSP,GIANHAP,NgayNhap,SLN,0,MALOAI);
                            sqLife.AddSP(sanPham);
                            sanPhams.clear();
                            sanPhams.addAll(sqLife.getALLSP());
                            lvDanhSach.setAdapter(new SanPham_Adapter(sanPhams));
                            dialog.dismiss();
                        }catch (Exception e)
                        {

                        }
                    }
                });




            }
        });
        return view;
    }
}
