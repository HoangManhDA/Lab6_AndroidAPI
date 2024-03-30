package com.example.duan1_pro1121_nhom2.LoginAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_pro1121_nhom2.ClassProduct.NhanVien;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;
import java.util.Calendar;

public class StepDangKi extends AppCompatActivity {
    EditText edHoTen,edSDT,edDiaChi;
    TextView txtNgaySinh,txtGioiTinh;
    Button btnDangKi,btnChonNgay,btnNam,btnNu;
    Intent intent;

    ArrayList<NhanVien> nhanViens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_dang_ki);
        intent = getIntent();
        edHoTen = findViewById(R.id.edHT_NV_Add);
        edDiaChi = findViewById(R.id.edDC_NV_Add);
        edSDT = findViewById(R.id.edSDT_NV_Add);
        txtNgaySinh =findViewById(R.id.txtNS_NV_Add);
        txtGioiTinh = findViewById(R.id.txtGT_NV_Add);
        btnDangKi = findViewById(R.id.btnDangKiAdd);
        btnChonNgay = findViewById(R.id.btnChonNS_NV_Add);
        btnNam = findViewById(R.id.btnGT_NV_Nam_Add);
        btnNu = findViewById(R.id.btnGT_NV_Nu_Add);
//        Button Chọn Ngày Sinh <Start>
        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis( System.currentTimeMillis() );


                DatePickerDialog dialog = new DatePickerDialog(
                        StepDangKi.this,
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
//        Button Chọn Ngày Sinh <End>

//        Button Chọn Giới Tính Nam <Start>
        btnNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtGioiTinh.setText("Nam");
            }
        });
//        Button Chọn Giới Tính Nam <End>

//        Button Chọn Giới Tính Nữ <Start>
        btnNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtGioiTinh.setText("Nữ");
            }
        });
//        Button Chọn Giới Tính Nữ <End>

//        Button Đăng Kí <Start>
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String HOTEN = edHoTen.getText().toString();
                    String DIACHI = edDiaChi.getText().toString();
                    int PHONE = Integer.parseInt(edSDT.getText().toString());
                    String TaiKhoan = intent.getStringExtra("TaiKhoan");
                    String MatKhau = intent.getStringExtra("MatKhau");
                    String NgaySinh = txtNgaySinh.getText().toString();
                    String GioiTinh = txtGioiTinh.getText().toString();
                    int IMAGENV = R.drawable.img_inf4;
                    String VaiTro;
                    SQLife sqLife = new SQLife(getApplicationContext());
                    nhanViens = sqLife.getALLNV();
                    if(HOTEN.toString().length()==0&&DIACHI.toString().length()==0)
                    {
                        Toast.makeText(StepDangKi.this, "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(NgaySinh.toString().equals("00-00-0000"))
                    {
                        Toast.makeText(StepDangKi.this, "Vui Lòng Chọn Ngày Sinh", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (nhanViens.size()==0)
                    {
                        VaiTro = "Quản Trị";
                        NhanVien nhanVien  = new NhanVien(0,TaiKhoan,MatKhau,HOTEN,PHONE,DIACHI,NgaySinh,GioiTinh,VaiTro,IMAGENV);
                        sqLife.AddNhanVien(nhanVien);
                        intent = new Intent(StepDangKi.this,DangNhap.class);
                        startActivity(intent);
                    }
                    if (nhanViens.size()>0)
                    {

                        VaiTro = "Nhân Viên";
                        NhanVien nhanVien  = new NhanVien(0,TaiKhoan,MatKhau,HOTEN,PHONE,DIACHI,NgaySinh,GioiTinh,VaiTro,IMAGENV);
                        sqLife.AddNhanVien(nhanVien);
                        intent = new Intent(StepDangKi.this,DangNhap.class);
                        startActivity(intent);
                    }
                }catch (Exception e){
                    Toast.makeText(StepDangKi.this, "Số Điện Thoại Phải Là Số và Không Được Để Trống", Toast.LENGTH_SHORT).show();
                }

            }
        });
//        Button Đăng Kí <End>
    }
}