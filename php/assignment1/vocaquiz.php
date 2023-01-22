<?php

$word = array();
$word = [["apple","사과"],["banana","바나나"],["carrot","당근"],["devil","악마"],["elephant","코끼리"],
		["fruit","과일"],["grape","포도"],["hamburger","햄버거"],["iphone","아이폰"],["jungle","정글"],
		["kingdom","왕국"],["lemon","레몬"],["money","돈"],["name","이름"],["orange","오렌지"],
		["peanut","땅콩"],["question","질문"],["rabbit","토끼"],["snake","뱀"],["tiger","호랑이"]];

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


echo "<form action='vocacheck.php' method='post'>";
for($i = 0; $i < 5; $i++){
		$row = $word[$rndlist[$i]-1];
		echo "$row[0] : <input type='text'name='word$i'><br>";
		echo "<input type='hidden' name='answer$i' value='$row[1]'>";
}
echo "<input type ='submit' value='정답확인'>";
echo "</form>";




?>