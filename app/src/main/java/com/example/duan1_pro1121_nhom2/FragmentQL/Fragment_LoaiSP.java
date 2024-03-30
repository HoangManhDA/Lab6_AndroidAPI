package com.example.duan1_pro1121_nhom2.FragmentQL;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.duan1_pro1121_nhom2.AdapteData.LoaiSP_Adapter;
import com.example.duan1_pro1121_nhom2.ClassProduct.LoaiSP;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;

public class Fragment_LoaiSP extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_loaisp,container,false);
        ListView listView = view.findViewById(R.id.lvLoaiSP);
        SQLife sqLife = new SQLife(container.getContext());
        ArrayList<LoaiSP> loaiSPS = sqLife.getALL_LSP();
        LoaiSP_Adapter adapter = new LoaiSP_Adapter(loaiSPS);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Button btnADD = view.findViewById(R.id.btnAddLoaiSP);
        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(container.getContext());
                View showAdd_LSP = LayoutInflater.from(container.getContext()).inflate(R.layout.show_add_lsp,container,false);
                builder.setView(showAdd_LSP);
                Dialog dialog= builder.create();
                dialog.show();
                EditText edTenLSP_ADD;
                edTenLSP_ADD = showAdd_LSP.findViewById(R.id.edTenLSP_ADD);
                Button btnSAVE;
                btnSAVE = showAdd_LSP.findViewById(R.id.btnAddLoaiSP_);
                btnSAVE.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String NAME_LSP = edTenLSP_ADD.getText().toString();
                        LoaiSP loaiSP = new LoaiSP(0,NAME_LSP);
                        sqLife.AddLoaiSP(loaiSP);
                        ArrayList<LoaiSP> loaiSPS = sqLife.getALL_LSP();
                        LoaiSP_Adapter adapter = new LoaiSP_Adapter(loaiSPS);
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        dialog.cancel();
                    }
                });


            }
        });
        return view;
    }
}
