<?php
session_start();
include_once('dbcon.php');
$uid = $_SESSION['uid'];

$tablenum = $_GET['tablenum'];
$name = $_GET['name'];


$sql = "select * from ordertable where name = '$name'";
$result = $conn->query($sql);
$row = $result->fetch_assoc();
$cnt = $row['cnt'] + 1;
$sql = "update ordertable set cnt = '$cnt' where uid = '$uid' and tablenum = '$tablenum' and name = '$name'";
$conn->query($sql);


echo "<script>";
echo "history.back();";
echo "</script>";


?>