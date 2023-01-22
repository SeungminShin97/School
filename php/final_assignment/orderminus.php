<?php
session_start();
include_once('dbcon.php');
$uid = $_SESSION['uid'];

$name = $_GET['name'];



$sql = "select * from orderlist where name = '$name'";
$result = $conn->query($sql);
if($result->num_rows > 0){
	$row=$result->fetch_assoc();
	if($row['cnt'] == 1){
		$sql = "delete from orderlist where uid = '$uid' and name = '$name'";
		$conn->query($sql);
	}
	else{
		$cnt = $row['cnt'] - 1;
		$sql = "update orderlist set cnt = '$cnt' where name = '$name'";
		$conn->query($sql);
	}
}


echo "<script>";
echo "history.back();";
echo "</script>";



?>