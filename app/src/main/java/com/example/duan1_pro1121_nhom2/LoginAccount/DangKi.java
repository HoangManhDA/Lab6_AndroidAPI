package com.example.duan1_pro1121_nhom2.LoginAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_pro1121_nhom2.ClassProduct.NhanVien;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;

public class DangKi extends AppCompatActivity {
    EditText edTaiKhoan,edMatKhau,edMatKhau2;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        edTaiKhoan = findViewById(R.id.edAddTaiKhoan);
        edMatKhau = findViewById(R.id.edAddMatKhau);
        edMatKhau2 = findViewById(R.id.edNhapLaiMK);
        btnNext = findViewById(R.id.btnNext_DK);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TaiKhoan = edTaiKhoan.getText().toString();
                String MatKhau = edMatKhau.getText().toString();
                String MatKhau2 = edMatKhau2.getText().toString();
                SQLife sqLife = new SQLife(getApplicationContext());
                ArrayList<NhanVien> nhanViens = new ArrayList<>();
                nhanViens = sqLife.getALLNV();
                for (int i =0;i<nhanViens.size();i++)
                {
                    String TaiKhoan2 = nhanViens.get(i).getUserName();
                    if (TaiKhoan.toString().equals(TaiKhoan2.toString())==true)
                    {
                        Toast.makeText(DangKi.this, "Tài Khoản Đã Tồn Tại" , Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if(TaiKhoan.toString().length()==0&&MatKhau.toString().length()==0&&MatKhau2.toString().length()==0)
                {
                    Toast.makeText(DangKi.this, "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (MatKhau.toString().equals(MatKhau2.toString())==false)
                {
                    Toast.makeText(DangKi.this, "Mật Khẩu Không Khớp", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(DangKi.this,StepDangKi.class);
                intent.putExtra("TaiKhoan",TaiKhoan);
                intent.putExtra("MatKhau",MatKhau);
                startActivity(intent);
            }
        });
    }
}