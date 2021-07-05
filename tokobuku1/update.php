<?php
require("koneksi.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $id = $_POST["id"];
    $judulbuku= $_POST["judulbuku"];
    $penerbitbuku = $_POST["penerbitbuku"];
    $genrebuku = $_POST["genrebuku"];
    $hargabuku = $_POST["hargabuku"];
    
    $perintah = "UPDATE tbl_tokobuku SET judulbuku = '$judulbuku', penerbitbuku = '$penerbitbuku', genrebuku = '$genrebuku', hargabuku ='$hargabuku' WHERE id = '$id'";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek      = mysqli_affected_rows($konek);

    if($cek > 0){
        $response["kode"] = 1;
        $response["pesan"] = "Data Berhasil Diubah";
    }
    else{
        $response["kode"] = 0;
        $response["pesan"] = "Data Gagal Diubah";
    }
}
else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak Ada Post Data";
}

echo json_encode($response);
mysqli_close($konek);