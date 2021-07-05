package com.erikmasarrang.tokobuku.API;

import com.erikmasarrang.tokobuku.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> ardCreateData(
            @Field("judulbuku") String judulbuku,
            @Field("penerbitbuku") String penerbitbuku,
            @Field("genrebuku") String genrebuku,
            @Field("hargabuku") String hargabuku
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
        @Field("id") int id
    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(
            @Field("id") int id,
            @Field("judulbuku") String judulbuku,
            @Field("penerbitbuku") String penerbitbuku,
            @Field("genrebuku") String genrebuku,
            @Field("hargabuku") String hargabuku

    );
}