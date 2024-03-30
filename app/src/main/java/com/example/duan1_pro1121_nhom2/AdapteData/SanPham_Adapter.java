package com.example.duan1_pro1121_nhom2.AdapteData;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
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
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.example.duan1_pro1121_nhom2.ClassProduct.LoaiSP;
import com.example.duan1_pro1121_nhom2.ClassProduct.SanPham;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;
import com.example.duan1_pro1121_nhom2.SelectTable.AdapterSelect_LSP;

import java.util.ArrayList;
import java.util.Calendar;

public class SanPham_Adapter extends BaseAdapter {
    ArrayList<SanPham> sanPhams;

    public SanPham_Adapter(ArrayList<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
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
        LinearLayout linearLayout = view.findViewById(R.id.linear_SanPham);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(parent.getContext());
                View showSP = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_sp,parent,false);
                builder.setView(showSP);
                Dialog dialog= builder.create();
                dialog.show();
                SanPham sanPham1 = sanPhams.get(position);
                TextView tvMaSP_show,tvNgayNhap,tvMaLoai,tvSLB;
                EditText edTenSP_show,edGiaNhap_show,edSLN_show;
                Button btnChonNgay,btnSuaMaLoai,btnSua,btnXoa,btnHuyShow;

                tvMaSP_show = showSP.findViewById(R.id.tvMaSP_Show);
                edTenSP_show = showSP.findViewById(R.id.ed_TenSP_Show);
                edGiaNhap_show = showSP.findViewById(R.id.ed_GiaNhap_Show);
                tvNgayNhap = showSP.findViewById(R.id.tvNgayNhap);
                edSLN_show = showSP.findViewById(R.id.ed_SLN_Show);
                tvSLB = showSP.findViewById(R.id.tvSLB_show);
                tvMaLoai =showSP.findViewById(R.id.tvMaLoai);

                btnChonNgay = showSP.findViewById(R.id.btnChonNN_SP_show);
                btnSuaMaLoai = showSP.findViewById(R.id.btnSuaLSP_SP_show);
                btnSua = showSP.findViewById(R.id.btnSuaSP);
                btnXoa = showSP.findViewById(R.id.btnXoaSP);
                btnHuyShow = showSP.findViewById(R.id.btnHuyShowSP);

                tvMaSP_show.setText(sanPham1.getMASP()+"");
                edTenSP_show.setText(sanPham1.getNAMESP());
                edGiaNhap_show.setText(""+sanPham1.getGIANHAP());
                tvNgayNhap.setText(""+sanPham1.getNGAYNHAP());
                edSLN_show.setText(""+sanPham1.getSOLUONGNHAP());
                tvSLB.setText(""+sanPham1.getSOLUONGDABAN());
                tvMaLoai.setText(""+sanPham1.getMALOAISP());

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

                btnSuaMaLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                        View view1 = LayoutInflater.from(v.getContext()).inflate(R.layout.select_table_maloai,parent,false);
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
                                tvMaLoai.setText(loaiSPS.get(position).getMaLoai()+"");
                                dialog1.dismiss();
                            }
                        });
                    }
                });

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int MASP1 = Integer.parseInt(tvMaSP_show.getText().toString());
                        String TENSP1 = edTenSP_show.getText().toString();
                        int GIANHAP1 = Integer.parseInt(edGiaNhap_show.getText().toString());
                        String NGAYNHAP1 = tvNgayNhap.getText().toString();
                        int SLN1 = Integer.parseInt(edSLN_show.getText().toString());
                        int MALOAI1 = Integer.parseInt(tvMaLoai.getText().toString());
                        int SLB1 = Integer.parseInt(tvSLB.getText().toString());
                        SanPham sanPham1 = new SanPham(MASP1,TENSP1,GIANHAP1,NGAYNHAP1,SLN1,SLB1,MALOAI1);
                        SQLife sqLife = new SQLife(v.getContext());
                        sqLife.UpdateSP(sanPham1);
                        sanPhams.clear();
                        sanPhams.addAll(sqLife.getALLSP());
                        notifyDataSetChanged();
                        dialog.dismiss();


                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLife sqLife = new SQLife(v.getContext());
                        int MASP1 = Integer.parseInt(tvMaSP_show.getText().toString());
                        sqLife.DeleteSP(MASP1+"");
                        sanPhams.clear();
                        sanPhams.addAll(sqLife.getALLSP());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnHuyShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
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
