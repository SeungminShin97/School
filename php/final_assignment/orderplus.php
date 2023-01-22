<?php
session_start();
include_once('dbcon.php');
$uid = $_SESSION['uid'];

$name = $_GET['name'];

$sql = "select * from menu where name = '$name'";
$result = $conn->query($sql);
$row = $result->fetch_assoc();
$price = $row['price'];


$sql = "select * from orderlist where name = '$name'";
$result = $conn->query($sql);
if($result->num_rows==0){
    $sql = "insert into orderlist(uid,name,price,cnt) values('$uid','$name',$price,1)";
    $conn->query($sql);
} else {
    $row=$result->fetch_assoc();
    $cnt = $row['cnt'] + 1;
    $sql = "update orderlist set cnt = '$cnt' where name='$name'";
    $conn->query($sql);
}

echo "<script>";
echo "history.back();";
echo "</script>";



?>