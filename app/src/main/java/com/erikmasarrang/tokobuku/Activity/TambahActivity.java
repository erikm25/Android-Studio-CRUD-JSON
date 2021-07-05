package com.erikmasarrang.tokobuku.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class TambahActivity extends AppCompatActivity {
    private EditText etJudulBuku, etPenerbitBuku, etGenreBuku, etHargaBuku;
    private Button btnSimpan;
    private String judulbuku, penerbitbuku, genrebuku, hargabuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etJudulBuku = findViewById(R.id.et_judulbuku);
        etPenerbitBuku = findViewById(R.id.et_penerbitbuku);
        etGenreBuku = findViewById(R.id.et_genrebuku);
        etHargaBuku = findViewById(R.id.et_hargabuku);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                judulbuku = etJudulBuku.getText().toString();
                penerbitbuku = etPenerbitBuku.getText().toString();
                genrebuku = etGenreBuku.getText().toString();
                hargabuku = etHargaBuku.getText().toString();

                if(judulbuku.trim().equals("")){
                    etJudulBuku.setError("Judul Buku Harus Diisi");
                }
                else if(penerbitbuku.trim().equals("")){
                    etPenerbitBuku.setError("Penerbit Buku Harus Diisi");
                }
                else if(genrebuku.trim().equals("")){
                    etGenreBuku.setError("Genre Buku Harus Diisi");
                }
                else if(hargabuku.trim().equals("")){
                    etHargaBuku.setError("Harga Buku Harus Diisi");
                }
                else{
                    createData();
                }
            }
        });
    }

    private void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(judulbuku, penerbitbuku, genrebuku, hargabuku);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
