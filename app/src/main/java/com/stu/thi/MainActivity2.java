package com.stu.thi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText edtMSSV, edtName, edtYear;
    Button btnEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtMSSV = findViewById(R.id.edtMSSV);
        edtName = findViewById(R.id.edtHoTen);
        edtYear = findViewById(R.id.edtNamSinh);
        btnEditor = findViewById(R.id.btnSua);

        Intent intent = getIntent();
        SinhVien sv = (SinhVien) intent.getSerializableExtra("SinhVien");
        edtMSSV.setText(sv.getMsv());
        edtName.setText(sv.getHoTen());
        edtYear.setText(String.valueOf(sv.getNamSinh()));

        btnEditor.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UnsafeIntentLaunch")
            @Override
            public void onClick(View v) {
                SinhVien newSinhVien = new SinhVien(edtMSSV.getText().toString().trim(), edtName.getText().toString().trim(), Integer.parseInt(edtYear.getText().toString().trim()));
                intent.putExtra("sv", newSinhVien);
                setResult(201, intent);
                finish();
            }
        });
    }
}