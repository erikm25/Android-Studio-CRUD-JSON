package com.erikmasarrang.tokobuku.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.erikmasarrang.tokobuku.API.APIRequestData;
import com.erikmasarrang.tokobuku.API.RetroServer;
import com.erikmasarrang.tokobuku.Model.ResponseModel;
import com.erikmasarrang.tokobuku.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private int xId;
    private String xJudulBuku, xPenerbitBuku, xGenreBuku, xHargaBuku;
    private EditText etJudulBuku, etPenerbitBuku, etGenreBuku, etHargaBuku;
    private Button btnUbah;
    private String yJudulBuku, yPenerbitBuku, yGenreBuku, yHargaBuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent terima = getIntent();
        xId = terima.getIntExtra("xId", -1);
        xJudulBuku = terima.getStringExtra("xJudulBuku");
        xPenerbitBuku = terima.getStringExtra("xPenerbitBuku");
        xGenreBuku = terima.getStringExtra("xGenreBuku");
        xHargaBuku = terima.getStringExtra("xHargaBuku");

        etJudulBuku = findViewById(R.id.et_judulbuku);
        etPenerbitBuku = findViewById(R.id.et_penerbitbuku);
        etGenreBuku = findViewById(R.id.et_genrebuku);
        etHargaBuku = findViewById(R.id.et_hargabuku);
        btnUbah = findViewById(R.id.btn_ubah);

        etJudulBuku.setText(xJudulBuku);
        etPenerbitBuku.setText(xPenerbitBuku);
        etGenreBuku.setText(xGenreBuku);
        etHargaBuku.setText(xHargaBuku);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yJudulBuku = etJudulBuku.getText().toString();
                yPenerbitBuku = etPenerbitBuku.getText().toString();
                yGenreBuku = etGenreBuku.getText().toString();
                yHargaBuku = etHargaBuku.getText().toString();

                updateData();
            }
        });
    }

    private void updateData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> ubahData = ardData.ardUpdateData(xId, yJudulBuku, yPenerbitBuku, yGenreBuku, yHargaBuku);

        ubahData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}