<?php
session_start();
include_once('dbcon.php');
$uid = $_SESSION['uid'];

$tablenum = $_GET['tablenum'];

$sql = "select * from orderlist where uid ='$uid'";/* orderlist */
$result = $conn->query($sql);
while($row = $result->fetch_assoc()) {
	$cnta = 0;
    $name = $row['name'];
    $price = $row['price'];
    $cnt = $row['cnt'];
    $sqla = "select * from ordertable where name = '$name' and tablenum='$tablenum'";
    $resulta = $conn->query($sqla);
    echo "$uid / $tablenum / $name / $price / $cnt <br>";
    if($resulta->num_rows==0){
        $sqla = "insert into ordertable(uid, tablenum, name, price, cnt) 
        values('$uid','$tablenum','$name',$price,$cnt)";
        $conn->query($sqla);
    } else {
        echo "1<br>";
        $rowa=$resulta->fetch_assoc();
        $cnta = $rowa['cnt'] + $cnt;
        $sqlb= "update ordertable set cnt = '$cnta' where uid = '$uid' and tablenum = '$tablenum' and name = '$name' ";
        $conn->query($sqlb);

    }
}
$sql = "delete from orderlist where uid = '$uid'";
$conn->query($sql);
echo "<script>";
echo "history.back();";
echo "</script>";


?>