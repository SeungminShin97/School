<?php

$server = "localhost"; 
$account = "root";     
$password = "";        
$database = "pos"; 
$conn = new mysqli($server, $account, $password, $database); 
if($conn -> connect_error)  
	die("Mysql 접속오류");
?>