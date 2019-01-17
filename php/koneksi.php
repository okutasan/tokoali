<?php
// define('HOST','localhost');
// define('USER','root');
// define('PASS','');
// define('DB','db_barang');

//Define your host here.
$servername = "localhost";
//Define your database username here.
$username = "absh5686";
//Define your database password here.
$password = "SKs5cpWV2sG962";
//Define your database name here.
$dbname = "absh5686_barang";
 
// $conn = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
// $conn = new mysqli($servername, $username, $password, $dbname);

if(mysqli_connect_errno()){
        die("Database connnection failed " . "(" .
            mysqli_connect_error() . " - " . mysqli_connect_errno() . ")"
                );
    }
?>