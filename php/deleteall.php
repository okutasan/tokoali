`<?php
require_once 'koneksi.php';
 
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
 
if($_SERVER['REQUEST_METHOD'] == 'POST')
{
    $DefaultId = 0;
    
    $InsertSQL = "TRUNCATE keranjang";
     
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