<?php
require_once "koneksi.php";

$conn = new mysqli($servername, $username, $password, $dbname);

$query = "SELECT * FROM keranjang";
$data = mysqli_query($conn, $query);

$result = array();

while($row = mysqli_fetch_array($data))
{
    array_push($result, array(
        'nama_barang'   => $row['nama_barang'],
        'harga_barang'  => $row['harga_barang'],
        'url_file'      => $row['url_file']
    ));
}

if($result == NULL)
{
    echo json_encode(array('result' => 'Error', 'Message' => 'Data Kosong'));
}
else
{
    echo json_encode(array('result' => $result), TRUE);
}

?>