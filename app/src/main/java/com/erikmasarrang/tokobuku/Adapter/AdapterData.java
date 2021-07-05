package com.erikmasarrang.tokobuku.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.erikmasarrang.tokobuku.API.APIRequestData;
import com.erikmasarrang.tokobuku.API.RetroServer;
import com.erikmasarrang.tokobuku.Activity.MainActivity;
import com.erikmasarrang.tokobuku.Activity.UbahActivity;
import com.erikmasarrang.tokobuku.Model.DataModel;
import com.erikmasarrang.tokobuku.Model.ResponseModel;
import com.erikmasarrang.tokobuku.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listData;
    private List<DataModel> listTokoBuku;
    private int idTokoBuku;

    public AdapterData(Context ctx, List<DataModel> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position);

        holder.tvId.setText(String.valueOf(dm.getId()));
        holder.tvJudulBuku.setText(dm.getJudulBuku());
        holder.tvPenerbitBuku.setText(dm.getPenerbitBuku());
        holder.tvGenreBuku.setText(dm.getGenreBuku());
        holder.tvHargaBuku.setText(dm.getHargaBuku());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvJudulBuku, tvPenerbitBuku, tvGenreBuku, tvHargaBuku;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvJudulBuku = itemView.findViewById(R.id.tv_judulbuku);
            tvPenerbitBuku = itemView.findViewById(R.id.tv_penerbitbuku);
            tvGenreBuku = itemView.findViewById(R.id.tv_genrebuku);
            tvHargaBuku = itemView.findViewById(R.id.tv_hargabuku);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih Operasi yang Akan Dilakukan");
                    dialogPesan.setTitle("Perhatian");
                    dialogPesan.setIcon(R.mipmap.ic_launcher_round);
                    dialogPesan.setCancelable(true);

                    idTokoBuku = Integer.parseInt(tvId.getText().toString());

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteData();
                            dialogInterface.dismiss();
                            Handler hand = new Handler();
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((MainActivity) ctx).retrieveData();
                                }
                            }, 1000);
                        }
                    });

                    dialogPesan.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getData();
                            dialogInterface.dismiss();
                        }
                    });

                    dialogPesan.show();

                    return false;
                }
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteData(idTokoBuku);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> ambilData = ardData.ardGetData(idTokoBuku);

            ambilData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    listTokoBuku = response.body().getData();

                    int varIdTokoBuku = listTokoBuku.get(0).getId();
                    String varJudulBuku = listTokoBuku.get(0).getJudulBuku();
                    String varPenerbitBuku = listTokoBuku.get(0).getPenerbitBuku();
                    String varGenreBuku = listTokoBuku.get(0).getGenreBuku();
                    String varHargaBuku = listTokoBuku.get(0).getHargaBuku();

                    //Toast.makeText(ctx, "Kode : "+kode+" | Pesan : "+pesan+ " | Data : "+varIdTokoBuku+" | "+varJudulBuku + " | "+varPenerbitBuku+" | "+varGenreBuku+," "+varHargaBuku, Toast.LENGTH_SHORT).show();

                    Intent kirim = new Intent(ctx, UbahActivity.class);
                    kirim.putExtra("xId", varIdTokoBuku);
                    kirim.putExtra("xJudulBuku", varJudulBuku);
                    kirim.putExtra("xPenerbitBuku", varPenerbitBuku);
                    kirim.putExtra("xGenreBuku", varGenreBuku);
                    kirim.putExtra("xHargaBuku", varHargaBuku);
                    ctx.startActivity(kirim);
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
