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


for($i = 0; $i < 5; $i++){
	echo "$i . $word[$i] -------> 정답 : $answer[$i]";
	
	if($word[$i] == $answer[$i]){
		echo " : ( O )";
		$score += 5;
	}
	else
		echo " : ( X )";
	echo "<br>";
}

echo " 총 점수 : $score";






?>