<?php
require("koneksi.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $judulbuku= $_POST["judulbuku"];
    $penerbitbuku = $_POST["penerbitbuku"];
    $genrebuku = $_POST["genrebuku"];
    $hargabuku = $_POST["hargabuku"];

    $perintah = "INSERT INTO tbl_tokobuku (judulbuku,penerbitbuku, genrebuku, hargabuku) VALUES('$judulbuku','$penerbitbuku','$genrebuku','$hargabuku')";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek      = mysqli_affected_rows($konek);

    if($cek > 0){
        $response["kode"] = 1;
        $response["pesan"] = "Simpan Data Berhasil";
    }
    else{
        $response["kode"] = 0;
        $response["pesan"] = "Gagal Menyimpan Data";
    }
}
else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak Ada Post Data";
}

echo json_encode($response);
mysqli_close($konek);