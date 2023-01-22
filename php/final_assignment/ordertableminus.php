<?php
session_start();
include_once('dbcon.php');
$uid = $_SESSION['uid'];

$tablenum = $_GET['tablenum'];
$name = $_GET['name'];

echo "$uid / $tablenum / $name";
$sql = "select * from ordertable where uid = '$uid' and tablenum = $tablenum and name = '$name'";
$result = $conn->query($sql);
$row = $result->fetch_assoc();
if($result->num_rows > 0) {
	if($row['cnt'] == 1) {
		echo "1<br>";
		$sql = "delete from ordertable where uid = '$uid' and tablenum = $tablenum and name = '$name'";
		$conn->query($sql);
	}
	else {
		$cnt = $row['cnt'] - 1;
		$sql = "update ordertable set cnt = $cnt where uid = '$uid' and tablenum = $tablenum and name = '$name'";
		$conn->query($sql);
	}
}

echo "<script>";
echo "history.back();";
echo "</script>";

?>