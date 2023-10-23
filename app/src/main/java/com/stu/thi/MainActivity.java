package com.stu.thi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String[] exampleData = {"DH52006010 \n Crodic Crystal \n 2001", "DH52006010 \n Crodic Crystal2 \n 2001"};
    ArrayList<String> dssv;

    ArrayAdapter<String> adapter;

    Button btnThem, btnEdit;

    ListView lv;

    EditText txtMSSV, txtHoTen, txtNamSinh;

    int index = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThem = findViewById(R.id.btnGuiThongTin);
        btnEdit = findViewById(R.id.btnEdit);
        txtMSSV = findViewById(R.id.txtMSSV);
        txtHoTen = findViewById(R.id.txtHoten);
        txtNamSinh = findViewById(R.id.txtNamSinh);
        lv = findViewById(R.id.lv);

        dssv = new ArrayList<>();

        for(int i = 0; i < exampleData.length; i++){
            String sinhvien = exampleData[i];
            dssv.add(sinhvien);
        }

        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_single_choice,dssv);
        lv.setAdapter(adapter);
        lv.setChoiceMode(1);
        adapter.notifyDataSetChanged();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = txtMSSV.getText().toString();
                String name = txtHoTen.getText().toString();
                String year = txtNamSinh.getText().toString();
                String result = code + " \n " + name + " \n " + year;
                dssv.add(result);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index == -1){
                    Toast.makeText(MainActivity.this, "Thông Có Thông Tin Cần Edit", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                SinhVien sv = new SinhVien(txtMSSV.getText().toString().trim(), txtHoTen.getText().toString().trim(), Integer.parseInt(txtNamSinh.getText().toString().trim()));
                myIntent.putExtra("SinhVien", sv);
                startActivityForResult(myIntent, 200);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dssv.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Xoá Thành Công", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                String sv = dssv.get(position);
                String[] tt = sv.split("\n");
                SinhVien sinhvien = new SinhVien(tt[0].trim(), tt[1].trim(), Integer.parseInt(tt[2].trim()));
                txtMSSV.setText(sinhvien.getMsv());
                txtHoTen.setText(sinhvien.getHoTen());
                txtNamSinh.setText(String.valueOf(sinhvien.getNamSinh()));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == 201){
            SinhVien sv = (SinhVien) data.getSerializableExtra("sv");
            String result = sv.toString();
            dssv.set(index, result);
            index = -1;
            adapter.notifyDataSetChanged();
        }
    }
}