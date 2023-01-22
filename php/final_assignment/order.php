<?php
session_start();
include_once('dbcon.php');
$uid = $_SESSION['uid'];

$tablenum = $_GET['tablenum'];
$menu = $_GET['menu'];


?>
<!doctype html>
<html>
<head>
    <title>주문</title>
    <meta charset="utf-8">
    <style>
        body {
                background-color: cadetblue;
                height: 100vh;
        }
        .grid > div {
            background-color:beige;
            border: 2px solid black;
            border-radius: 5px;
        }
        .grid {
            height: 85%;
            display: grid;
            column-gap: 10px;
            row-gap: 10px;
            grid-template-areas:
                "b1 b3"
                "b2 b3"
            ;
        }
        .box1 { 
            grid-area: b1;
        }
        .box2 {
            grid-area: b2;
        }
        .box3 { 
            grid-area: b3;
        }
        li {
            list-style-type: none;
        }
        li:hover {
            background:white;
        }
        li:active {
            background:red;
        }
        .d1 {
            background:red;
        }
        table {
            margin: 30px;
            border-collapse: collapse;
        }
        a {
            text-decoration: none;
            color: black;
            background: skyblue;
            padding:10px 10px;
            border-radius: 5px;

        }
        a:hover {
            background:white;
            cursor: pointer;
        }
        .orderlist {
            background:white;
            width: 80%;
        }
        .orderlist tr{
            background:gray;
        }
        .orderlist td {
            background:white;
            padding: 10px 10px;
            
        }
		 .orderlist a {
			 padding: 5px 10px;
			 color: white;
			 background: black;
			 border-radius: 20px;
		 }
		 .orderlist a:hover {
		 color: black;
		 background:rgba(0,0,0,0.2);
		 }
		 #pm {
			 text-align: center;
			 padding:5px;

		 }
        #colwhite {
            background:white;
        }
        #colblack {
            background:gray;
        }

        .btn {
            float: left;
            background: violet;
            margin: 5px 10px;
        }
    </style>
</head>
<body>
    <div class="grid">
<!----------------------------------------------그리드 1----------------------------------------->
       <div class="box1">
           <table class="orderlist">
				<tr>
                   <th id="colwhite">기존 메뉴명</th><th>가격</th><th id="colwhite">수량</th><th id="colwhite"></th><th id="colwhite"></th>
              </tr>
<?php
$nnprice = 0;
$nncnt = 0;
$sql = "select * from ordertable where uid = '$uid' and tablenum = '$tablenum'";
$result = $conn->query($sql);
if($result->num_rows > 0){
    while($row = $result->fetch_assoc()){?>
              <tr>
					<td id="colblack"><?=$row['name']?></td>
					<td><?=$row['price']?></td>
					<td id="colblack"><?=$row['cnt']?></td>
					<td id="pm"><a href="ordertableplus.php?tablenum=<?=$tablenum?>&name=<?=$row['name']?>">+</a><td>
					<td id="pm"><a href="ordertableminus.php?tablenum=<?=$tablenum?>&name=<?=$row['name']?>">-</a><td>
				</tr>
<?php
$nnprice += $row['price'] * $row['cnt'];
$nncnt += $row['cnt'];
    }
}
?>
                <tr>
                    <td>총가격</td>
                    <td id="colblack"><?=$nnprice?></td>
                    <td><?=$nncnt?></td>
                </tr>
           </table>
		   
		   
		   
		   
           <table class="orderlist">
              <tr>
                   <th>추가 메뉴명</th><th id="colwhite">가격</th><th>수량</th><th id="colwhite"></th><th id="colwhite"></th>
              </tr>
<?php
$nprice = 0;
$ncnt = 0;
$sql = "select * from orderlist where uid = '$uid'";
$result = $conn->query($sql);
if($result->num_rows > 0){
    while($row = $result->fetch_assoc()){?>
              <tr>
					<td><?=$row['name']?></td>
					<td id="colblack"><?=$row['price']?></td>
					<td><?=$row['cnt']?></td>
					<td id="pm"><a href="orderplus.php?name=<?=$row['name']?>">+</a><td>
					<td id="pm"><a href="orderminus.php?name=<?=$row['name']?>">-</a><td>
				</tr>
<?php
$nprice += $row['price'] * $row['cnt'];
$ncnt += $row['cnt'];
    }
}
?>
                <tr>
                    <td id="colblack">총가격</td>
                    <td><?=$nprice?></td>
                    <td id="colblack"><?=$ncnt?></td>
                </tr>
            </table>
        </div>
<!----------------------------------------------그리드 2----------------------------------------->
        <div class="box2">
<?php

?>
            <table class="orderlist">
				  <tr>
				      <td id="colblack">테이블 번호</td>
					   <td><?=$tablenum?></td>
				  </tr>
                <tr>
                    <td>메뉴수</td>
					   <td id="colblack"><?=$nncnt?></td>
                </tr>
                <tr>
                    <td id="colblack">금액</td>
					   <td><?=$nnprice?></td>
                </tr>
            </table>
			<p><a class="btn" id="btno" href="credit.php?tablenum=<?=$tablenum?>&totalprice=<?=$nnprice?>">결제</a>
				<a class="btn" id="btno" href="ordertabledel.php?tablenum=<?=$tablenum?>">기존주문취소</a>
				<a class="btn" id="btno" href="orderlistdel.php">추가주문취소</a>
			</p>
				
        </div>
<!----------------------------------------------그리드 3----------------------------------------->
        <div class="box3">
            <table>
            <ul>
                <td><li><a href="order.php?tablenum=<?=$tablenum?>&menu=main">메인메뉴</a></li><td>
                <td><li><a href="order.php?tablenum=<?=$tablenum?>&menu=side">사이드메뉴</a></li><td>
                <td><li><a href="order.php?tablenum=<?=$tablenum?>&menu=drink">음료</a></li><td>
            </ul>
            </table>
<?php
if($menu == 'main'){
    $sql = "select * from menu where uid = '$uid' and cat = 'main'";
    $result = $conn->query($sql);
        if($result->num_rows >0){
while($row = $result->fetch_assoc()){?>
                <tr>
                    <td><a onclick="location.href='orderplus.php?name=<?=$row['name']?>'"><?=$row['name']?></a></td>
                </tr>
<?php
        }
    }
} else if($menu == 'side'){
    $sql = "select * from menu where uid = '$uid' and cat = 'side'";
    $result = $conn->query($sql);
        if($result->num_rows >0){
while($row = $result->fetch_assoc()){?>
                <tr>
                    <td><a onclick="location.href='orderplus.php?name=<?=$row['name']?>'"><?=$row['name']?></a></td>
                </tr>
<?php
        }
    }
} else {
    $sql = "select * from menu where uid = '$uid' and cat = 'drink'";
    $result = $conn->query($sql);
        if($result->num_rows >0){
while($row = $result->fetch_assoc()){?>
                <tr>
                    <td><a onclick="location.href='orderplus.php?name=<?=$row['name']?>'"><?=$row['name']?></a></td>
                </tr>
<?php
        }
    }
}
?>

            </table>
        </div>
    </div>
    <p><a class="btn" id="btno" href="orderproc.php?tablenum=<?=$tablenum?>">주문</a>
    <a class="btn" href="main.php">뒤로가기</a></p>
</body>
</html>