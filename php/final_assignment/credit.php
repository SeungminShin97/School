<?php
session_start();
include_once('dbcon.php');
$totalprice = $_GET['totalprice'];
$tablenum = $_GET['tablenum'];
$uid = $_SESSION['uid'];

$creditno = date("Y").date("m").date("d")."-%";
$sql = "select max(creditno) maxno from credit where creditno like'$creditno'";
$result = $conn->query($sql);
if(isset($result) && $result->num_rows>0) {
	$row = $result->fetch_assoc();
	$maxno=$row['maxno'];
	if(isset($maxno)){
		$no = substr($maxno, 9, 3) + 1;  //현재 마지막 주문번호 순번을 하나 증가
		$creditno = date("Y").date("m").date("d")."-".sprintf("%03d",$no);
	}
	else $creditno = date("Y").date("m").date("d")."-001"; 
}
else if(isset($result) && $result->num_rows==0) 
	$creditno = date("Y").date("m").date("d")."-001"; 

$orddate = date("Y/m/d");

$sql = "insert into credit values('$creditno','$orddate','$uid',$tablenum,$totalprice)";
$conn->query($sql);

$sql = "select * from ordertable where uid = '$uid' and tablenum = '$tablenum'";
$result = $conn->query($sql);
if(isset($result) && $result->num_rows>0) {
	while($row = $result->fetch_assoc()){
		$name = $row['name'];
		$cnt = $row['cnt'];
		$price = $row['price'];
		$sql = "insert into creditlist(creditno, name, price, cnt) values('$creditno','$name',$price,$cnt)";
		$conn->query($sql);
		echo $conn->error;
	}
}

$sql = "delete from ordertable where uid ='$uid' and tablenum = '$tablenum'";
$conn->query($sql);

echo "<script>";
echo "history.back();";
echo "</script>";

?>

