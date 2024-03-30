package com.example.duan1_pro1121_nhom2;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.duan1_pro1121_nhom2.ClassProduct.HoaDonCT;
import com.example.duan1_pro1121_nhom2.ClassProduct.NhanVien;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.FragmentQL.Fragment_HoaDon;
import com.example.duan1_pro1121_nhom2.FragmentQL.Fragment_KhachHang;
import com.example.duan1_pro1121_nhom2.FragmentQL.Fragment_LoaiSP;
import com.example.duan1_pro1121_nhom2.FragmentQL.Fragment_NhanVien;
import com.example.duan1_pro1121_nhom2.FragmentQL.Fragment_SanPham;
import com.example.duan1_pro1121_nhom2.FragmentQL.Fragment_ThongKe;
import com.example.duan1_pro1121_nhom2.LoginAccount.DangNhap;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    Intent intent;


    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        String vaiTro = intent.getStringExtra("VAITRO");
        if(vaiTro.equals("Quản Trị")==true)
        {
            setContentView(R.layout.activity_main);
        }
        if (vaiTro.equals("Nhân Viên")==true)
        {
            setContentView(R.layout.activity_main2);
        }




//
        // Bắt Đầu Xử Lí Toolbar
        toolbar = findViewById(R.id.toolBar_);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_menu_white_18);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("HOME");
        // Kết Thúc Xử Lí Toolbar
        // ánh xạ drawer vs navigation
        drawerLayout = findViewById(R.id.Draw_layout);
        navigationView = findViewById(R.id.Draw_nav);
        //
        // start edit Nav_header
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide( View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened( View drawerView) {
                // sửa nội dung nav_header
                TextView txtNameHeader,txtVaiTroHeader,txtDoanhThuHeader;
                txtNameHeader = findViewById(R.id.tvName_header);
                txtVaiTroHeader = findViewById(R.id.tvVaiTro_header);
                txtDoanhThuHeader = findViewById(R.id.tvDoanhThu_header);

                NhanVien nhanVien = (NhanVien) intent.getSerializableExtra("infomation");
                txtNameHeader.setText("Họ Tên: "+nhanVien.getTenNV());
                txtVaiTroHeader.setText("Vai Trò: "+nhanVien.getVaiTro());
                int DoanhThu = 0;
                txtDoanhThuHeader.setText("Doanh Thu: "+DoanhThu+" VNĐ");


            }

            @Override
            public void onDrawerClosed( View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        // end edit Nav_header
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_main,new Fragment_SanPham()).commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                NhanVien nhanVien = (NhanVien) intent.getSerializableExtra("infomation");
                switch (item.getItemId())
                {
                    case R.id.mnu_QLSanPham:
                        fragmentManager.beginTransaction().replace(R.id.fragment_main,new Fragment_SanPham()).commit();
                        getSupportActionBar().setTitle("Quản Lý Sản Phẩm");
                        break;
                    case R.id.mnu_QLLoaiSanPham:
                        fragmentManager.beginTransaction().replace(R.id.fragment_main,new Fragment_LoaiSP()).commit();
                        getSupportActionBar().setTitle("Quản Lý Loại Sản Phẩm");
                        break;
                    case R.id.mnu_QLNhanVien:
                        fragmentManager.beginTransaction().replace(R.id.fragment_main,new Fragment_NhanVien()).commit();
                        getSupportActionBar().setTitle("Quản Lý Nhân Viên");
                        break;
                    case R.id.mnu_QLKhachHang:
                        fragmentManager.beginTransaction().replace(R.id.fragment_main,new Fragment_KhachHang()).commit();
                        getSupportActionBar().setTitle("Quản Lý Khách Hàng");
                        break;
                    case R.id.mnu_QLHoaDon:
                        fragmentManager.beginTransaction().replace(R.id.fragment_main,new Fragment_HoaDon(nhanVien.getMaNV())).commit();
                        getSupportActionBar().setTitle("Quản Lý Hoá Đơn ");
                        break;
                    case R.id.mnu_ThongKe:
                        fragmentManager.beginTransaction().replace(R.id.fragment_main,new Fragment_ThongKe()).commit();
                        getSupportActionBar().setTitle("Thống Kê");
                        break;
                    case R.id.mnu_QLTaiKhoanCaNhan:

                        fragmentManager.beginTransaction().replace(R.id.fragment_main,new Fragment_HoaDon(nhanVien.getMaNV())).commit();
                        getSupportActionBar().setTitle("Thông Tin Tài Khoản");
                        break;
                    case R.id.mnu_DangXuat:
                        DangXuat();
                        break;
                }
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });
    }


    public void DangXuat()
    {
        intent = new Intent(MainActivity.this, DangNhap.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if(item.getItemId()== android.R.id.home )
        {
            drawerLayout.openDrawer(navigationView);
        }

        return super.onOptionsItemSelected(item);
    }


}
