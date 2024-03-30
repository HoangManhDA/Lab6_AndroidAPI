package com.example.duan1_pro1121_nhom2.AdapteData;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.duan1_pro1121_nhom2.ClassProduct.HoaDon;
import com.example.duan1_pro1121_nhom2.ClassProduct.HoaDonCT;
import com.example.duan1_pro1121_nhom2.ClassProduct.KhachHang;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;
import java.util.Calendar;

public class HoaDon_Adapter extends BaseAdapter {
    ArrayList<HoaDon> hoaDons;
    int MANV;
    int SOTIENTHANHTOAN;
    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public HoaDon_Adapter(ArrayList<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hoadon,parent,false);
        TextView tvMAHD,tvMAKH,tvTENKH,tvMASP,tvTENSP,tvNgayMua,tvTongTien,tvTrangThai;
        LinearLayout linearLayout;

        SQLife sqLife = new SQLife(view.getContext());
        ArrayList<KhachHang> khachHangs = sqLife.getALLKH();

                linearLayout = view.findViewById(R.id.linear_hoadon);
        tvMAHD = view.findViewById(R.id.tvRowMaHD);
        tvMAKH = view.findViewById(R.id.tvRowHD_MAKH);
        tvTENKH = view.findViewById(R.id.tvRowHD_TENKH);
        tvMASP = view.findViewById(R.id.tvRowHD_MASP);
        tvTENSP = view.findViewById(R.id.tvRowHD_TENSP);
        tvNgayMua = view.findViewById(R.id.tvRowHD_NGAYMUA);
        tvTongTien = view.findViewById(R.id.tvRowHD_TONGTIEN);
        tvTrangThai = view.findViewById(R.id.tvRowHD_TRANGTHAI);
        HoaDon hoaDon = hoaDons.get(position);
        tvMAHD.setText(hoaDon.getMaHD()+"");
        tvMAKH.setText(hoaDon.getMaKH()+"");
        for (int i = 0;i<khachHangs.size();i++)
        {
            if((tvMAKH.getText().toString()).equals(khachHangs.get(i).getMaKH()+"")==true)
            {
                tvTENKH.setText(khachHangs.get(i).getTenKH());
            }
        }
        tvMASP.setText(hoaDon.getMaSP());
        tvTENSP.setText(hoaDon.getTenSP());
        tvNgayMua.setText(hoaDon.getNgayMuaHang());
        tvTongTien.setText(hoaDon.getTongTienHang()+"");
        tvTrangThai.setText(hoaDon.getTrangThai());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View viewShow = LayoutInflater.from(v.getContext()).inflate(R.layout.show_hoadon,parent,false);
                builder.setView(viewShow);
                Dialog dialog = builder.create();
                dialog.show();
                TextView tvMAHD_show,tvHD_MAKH_show,tvHD_TENKH_show,tvHD_MASP_show,tvHD_TENSP_show,tvHD_NGAYMUA_show,tvHD_TONGTIEN_show,tvHD_TRANGTHAI_show;
                Button btnSua,btnXoa,btnHuy,btnTrue,btnFalse,btnChonNgayMua;
                tvMAHD_show = viewShow.findViewById(R.id.tvMAHD_show);
                tvHD_MAKH_show = viewShow.findViewById(R.id.tvHD_MAKH_show);
                tvHD_TENKH_show = viewShow.findViewById(R.id.tvHD_TENKH_show);
                tvHD_MASP_show = viewShow.findViewById(R.id.tvHD_MASP_show);
                tvHD_TENSP_show = viewShow.findViewById(R.id.tvHD_TENSP_show);
                tvHD_NGAYMUA_show = viewShow.findViewById(R.id.tvHD_NGAYMUA_show);
                tvHD_TONGTIEN_show = viewShow.findViewById(R.id.tvHD_TONGTIEN_show);
                tvHD_TRANGTHAI_show = viewShow.findViewById(R.id.tvHD_TRANGTHAI_show);

                tvMAHD_show.setText(tvMAHD.getText().toString());
                tvHD_MAKH_show.setText(tvMAKH.getText().toString());
                tvHD_TENKH_show.setText(tvTENKH.getText().toString());
                tvHD_MASP_show.setText(tvMASP.getText().toString());
                tvHD_TENSP_show.setText(tvTENSP.getText().toString());
                tvHD_NGAYMUA_show.setText(tvNgayMua.getText().toString());
                tvHD_TONGTIEN_show.setText(tvTongTien.getText().toString());
                tvHD_TRANGTHAI_show.setText(tvTrangThai.getText().toString());

                btnTrue = viewShow.findViewById(R.id.btnTrueTT_HD);
                btnFalse = viewShow.findViewById(R.id.btnFalseTT_HD);
                btnSua = viewShow.findViewById(R.id.btnSuaHD);
                btnXoa = viewShow.findViewById(R.id.btnXoaHD);
                btnHuy = viewShow.findViewById(R.id.btnHuyShowHD);
                btnTrue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvHD_TRANGTHAI_show.setText("Đã Thanh Toán");
                        SOTIENTHANHTOAN = Integer.parseInt(tvHD_TONGTIEN_show.getText().toString());
                    }
                });
                btnFalse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvHD_TRANGTHAI_show.setText("Chưa Thanh Toán");
                        SOTIENTHANHTOAN = 0;
                    }
                });
                btnChonNgayMua = viewShow.findViewById(R.id.btnChonNM_HD_show);
                btnChonNgayMua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis( System.currentTimeMillis() );


                        DatePickerDialog dialog = new DatePickerDialog(v.getContext(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                                        int nam = i;
                                        int thang = i1;
                                        int ngay = i2;

                                        tvHD_NGAYMUA_show.setText(ngay + " - " + (thang + 1) + " - " + nam);
                                    }
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DATE)
                        );

                        dialog.show();
                    }
                });
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int MAHD = Integer.parseInt(tvMAHD_show.getText().toString());
                        int MAKH = Integer.parseInt(tvHD_MAKH_show.getText().toString());
                        String MASP = tvHD_MASP_show.getText().toString();
                        String TENSP = tvHD_TENSP_show.getText().toString();
                        String NgayMua = tvHD_NGAYMUA_show.getText().toString();
                        int TongTien = Integer.parseInt(tvHD_TONGTIEN_show.getText().toString());
                        String TrangThai = tvHD_TRANGTHAI_show.getText().toString();

                        int MANV_ = getMANV();
                        KhachHang khachHang = sqLife.getOneKH(""+MAKH);

                        HoaDon hoaDon1 = new HoaDon(MAHD,MAKH,NgayMua,MASP,TENSP,TongTien,TrangThai);
                        sqLife.UpdateHoaDon(hoaDon1);
                        HoaDonCT hoaDonCT = new HoaDonCT(MAHD,MASP,TENSP,MAKH,khachHang.getTenKH(),khachHang.getNumberPhone(),TongTien,SOTIENTHANHTOAN,NgayMua,MANV_);
                        sqLife.UpdateHoaDonCT(hoaDonCT);
                        hoaDons.clear();
                        hoaDons.addAll(sqLife.getALLHoaDon());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sqLife.DeleteHD(tvMAHD.getText().toString());
                        sqLife.DeleteHDCT(tvMAHD.getText().toString());
                        hoaDons.clear();
                        hoaDons.addAll(sqLife.getALLHoaDon());
                        dialog.dismiss();
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
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
        return hoaDons.size();
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
