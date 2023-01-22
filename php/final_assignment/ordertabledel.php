<?php
session_start();
include_once('dbcon.php');
$uid = $_SESSION['uid'];
$tablenum = $_GET['tablenum'];
$sql = "delete from ordertable where uid = '$uid' and tablenum = '$tablenum'";
$conn->query($sql);

echo "<script>";
echo "history.back();";
echo "</script>";



?>