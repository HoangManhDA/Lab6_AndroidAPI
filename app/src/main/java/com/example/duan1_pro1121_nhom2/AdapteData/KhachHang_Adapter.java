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

import com.example.duan1_pro1121_nhom2.ClassProduct.KhachHang;
import com.example.duan1_pro1121_nhom2.ClassProduct.NhanVien;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class KhachHang_Adapter extends BaseAdapter {
    ArrayList<KhachHang> khachHangs;
    KhachHang khachHang1;
    public KhachHang_Adapter(ArrayList<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_kh,parent,false);
        TextView txtRowMaKH,txtRowTenKH,txtRowGioiTinh,txtRowPhoneKH;
        CircleImageView circleImageView ;
        txtRowMaKH = view.findViewById(R.id.txtRowMaKH);
        txtRowTenKH = view.findViewById(R.id.txtRowTenKH);
        txtRowGioiTinh = view.findViewById(R.id.txtRowGioiTinhKH);
        txtRowPhoneKH = view.findViewById(R.id.txtRowPhoneKH);
        circleImageView = view.findViewById(R.id.RowImageKH);
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
        LinearLayout linearLayout = view.findViewById(R.id.linear_rowkh);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(parent.getContext());
                View showKH = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_kh,parent,false);
                builder.setView(showKH);
                Dialog dialog= builder.create();
                dialog.show();
                TextView txtNgaySinh,txtGioiTinh;
                EditText edTenKH_show,edDiaChiKH_show,edNumberPhoneKH_show;
                Button btnSua,btnXoa,btnHuy,btnChonNgay,btnNam,btnNu;
                txtNgaySinh = showKH.findViewById(R.id.txtNS_KH_Show);
                txtGioiTinh = showKH.findViewById(R.id.txtGT_KH_Show);
                edTenKH_show = showKH.findViewById(R.id.edHT_KH_Show);
                edDiaChiKH_show = showKH.findViewById(R.id.edDC_KH_Show);
                edNumberPhoneKH_show = showKH.findViewById(R.id.edSDT_KH_Show);
                btnSua = showKH.findViewById(R.id.btnSuaKH);
                btnXoa = showKH.findViewById(R.id.btnXoaKH);
                btnHuy = showKH.findViewById(R.id.btnHuyShowKH);
                btnChonNgay = showKH.findViewById(R.id.btnChonNS_KH_Show);
                btnNam = showKH.findViewById(R.id.btnGT_KH_Nam_Show);
                btnNu = showKH.findViewById(R.id.btnGT_KH_Nu_Show);
                edTenKH_show.setText(TENKH);
                edDiaChiKH_show.setText(DiaChi);
                edNumberPhoneKH_show.setText(NumberPhone+"");
                txtGioiTinh.setText(GioiTinh);
                txtNgaySinh.setText(NgaySinh);
                btnChonNgay.setOnClickListener(new View.OnClickListener() {
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

                                        txtNgaySinh.setText(ngay + " - " + (thang + 1) + " - " + nam);
                                    }
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DATE)
                        );

                        dialog.show();
                    }
                });
                btnNam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtGioiTinh.setText("Nam");
                    }
                });
                btnNu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtGioiTinh.setText("Nữ");
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int MAKH1 = MAKH;
                        String TENKH1 = edTenKH_show.getText().toString();
                        int NumberPhone1 = Integer.parseInt(edNumberPhoneKH_show.getText().toString());
                        String DiaChi1 = edDiaChiKH_show.getText().toString();
                        String NgaySinh1 = txtNgaySinh.getText().toString();
                        String GioiTinh1 = txtGioiTinh.getText().toString();
                        khachHang1 = new KhachHang(MAKH1,TENKH1,NumberPhone1,DiaChi1,NgaySinh1,GioiTinh1,IMGKH);
                        SQLife sqLife = new SQLife(v.getContext());
                        sqLife.UpdateKH(khachHang1);
                        khachHangs.clear();
                        khachHangs.addAll(sqLife.getALLKH());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int MAKH1 = MAKH;
                        SQLife sqLife = new SQLife(v.getContext());
                        sqLife.DeleteKH(MAKH1+"");
                        khachHangs.clear();
                        khachHangs.addAll(sqLife.getALLKH());
                        notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });
            }
        });
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
