`<?php
require_once 'koneksi.php';
 
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
 
if($_SERVER['REQUEST_METHOD'] == 'POST')
{
    $DefaultId = 0;
    
    $gambar=$_POST['url_file'];
    $nama = $_POST['nama_barang'];
    $harga = $_POST['harga_barang'];
    
     
    $InsertSQL = "INSERT INTO keranjang (nama_barang, harga_barang,url_file) values('$nama', '$harga','$gambar')";
     
    if(mysqli_query($conn, $InsertSQL)){
        echo "Berhasil Menambahkan Data ".$nama;
    }
    else{
        echo "Gagal Menambahkan Data";
    }
    mysqli_close($conn);
}
else
{
    echo "Gagal";
}
 
?>`