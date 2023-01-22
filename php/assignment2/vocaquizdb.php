<?php
#서버 접속
$server = "localhost";
$account = "root";
$password = "";
$database = "quizdb";
$conn = new mysqli($server, $account, $password, $database);
if($conn->connect_error)
	die("Mysql 접속오류");
 
$rndlist =array();

for($i = 0; $i < 5; $i++){
	while(true){
		$rnd = mt_rand(1,20);
		$cnt = 0;
		foreach($rndlist as $a){
			if($a == $rnd )
				$cnt++;
		}
		if($cnt == 0) { 
		$rndlist[] = $rnd;
		break;
		}
	}
}

//echo "$row[0] : <input type='text'name='word$i'><br>";
//echo "<input type='hidden' name='answer$i' value='$row[1]'>";

echo "<form action='vocacheckdb.php' method='post'>";
$sql = "select * from voca";
for($i = 0; $i < 5; $i++){
	$result = $conn->query($sql);
	while(true){
		$row = $result->fetch_assoc();
		if($row['seqno'] == $rndlist[$i])
			break;
	}
	$eout = $row['eword']; 
	$snum = $row['seqno'];
	echo "$eout : <input type='text' name='word$i'><br>";
	echo "<input type='hidden' name='answer$i' value='$snum'>";
}
echo "<input type ='submit' value='정답확인'>";
echo "</form>";
/*
$sql = "select * from voca";
$result = $conn->query($sql);
if(isset($result)) {
	$row = $result->fetch_assoc();
	echo $row['eword'];
}
*/


?>