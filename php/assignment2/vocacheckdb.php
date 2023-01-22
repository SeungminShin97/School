<?php
$word = array();
$answer = array();
$score = 0;

$word[] = $_POST['word0'];
$word[] = $_POST['word1'];
$word[] = $_POST['word2'];
$word[] = $_POST['word3'];
$word[] = $_POST['word4'];

$answer[] = $_POST['answer0'];
$answer[] = $_POST['answer1'];
$answer[] = $_POST['answer2'];
$answer[] = $_POST['answer3'];
$answer[] = $_POST['answer4'];

#서버 접속
$server = "localhost";
$account = "root";
$password = "";
$database = "quizdb";
$conn = new mysqli($server, $account, $password, $database);
if($conn->connect_error)
	die("Mysql 접속오류");
 
$sql = "select * from voca";
for($i = 0; $i < 5; $i++){
	$result = $conn->query($sql);
	while(true){
		$row = $result->fetch_assoc();
		if($row['seqno'] == $answer[$i])
			break;
	}
}

for($i = 0; $i < 5; $i++){
	$result = $conn->query($sql);
	while(true){
		$row = $result->fetch_assoc();
		if($row['seqno'] == $answer[$i])
			break;
	}
	$kout = $row['kword']; 
	echo "$i ". $word[$i]." -------> 정답 : $kout";
	if($word[$i] == $row['kword']){
		echo " : ( O )";
		$score += 5;
	}
	else
		echo " : ( X )";
	echo "<br>";
}

echo " 총 점수 : $score";






?>