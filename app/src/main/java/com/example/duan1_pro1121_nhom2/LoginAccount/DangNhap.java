package com.example.duan1_pro1121_nhom2.LoginAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_pro1121_nhom2.ClassProduct.NhanVien;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.MainActivity;
import com.example.duan1_pro1121_nhom2.R;

import java.io.Serializable;
import java.util.ArrayList;

public class DangNhap extends AppCompatActivity {
    Button btnDangKi,btnDangNhap;
    EditText edTaiKhoan,edMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKi = findViewById(R.id.btnDangKi);
        edTaiKhoan = findViewById(R.id.edTaiKhoan);
        edMatKhau = findViewById(R.id.edMatKhau);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TaiKhoan = edTaiKhoan.getText().toString();
                String MatKhau = edMatKhau.getText().toString();
                SQLife sqLife = new SQLife(getApplicationContext());
                ArrayList<NhanVien> nhanViens = new ArrayList<>();
                nhanViens = sqLife.getALLNV();
                boolean check = false;
                for (int i =0;i<nhanViens.size();i++)
                {
                    String TaiKhoan2 = nhanViens.get(i).getUserName();
                    String MatKhau2 = nhanViens.get(i).getPassWord();

                    if (TaiKhoan.toString().equals(TaiKhoan2.toString())==true)
                    {
                        if (MatKhau.toString().equals(MatKhau2.toString())==true)
                        {
                            String VAITRO = nhanViens.get(i).getVaiTro();
                            NhanVien nhanVienput = nhanViens.get(i);
                            Intent intent = new Intent(DangNhap.this, MainActivity.class);
                            intent.putExtra("VAITRO",VAITRO);
                            intent.putExtra("infomation", (Serializable) nhanVienput);

                            ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);

                            startActivity(intent);
                            return;
                        }
                        if (MatKhau.toString().equals(MatKhau2.toString())==false)
                        {
                            Toast.makeText(DangNhap.this, "Mật Khẩu Chưa Chính Xác", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    check = false;


                }
                if (check==false)
                {
                    Toast.makeText(DangNhap.this, "Tài Khoản Không Tồn Tại", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this,DangKi.class);
                startActivity(intent);
            }
        });
    }
}