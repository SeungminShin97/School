<?php
session_start();
include_once('dbcon.php');
$uid = $_SESSION['uid'];

$sql = "delete from orderlist where uid = '$uid'";
$conn->query($sql);

echo "<script>";
echo "history.back();";
echo "</script>";



?>