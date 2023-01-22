<?php
session_start();
include_once('dbcon.php');
$uid = $_SESSION['uid'];

?>
<!doctype html>
<html>
<head>
	<title>결제</title>
	<meta charset="utf-8">
	<style>
		body {
			background-color: cadetblue;
		}
		.credit {
			background-color: white;
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%,-50%);
			text-align: center;
		}
		table {
            margin: 30px;
            border-collapse: collapse;
        }
        a {
            text-decoration: none;
            color: black;
            background:white;
            border-radius: 5px;
        }
        a:hover {
			 background: skyblue;
            cursor: pointer;
        }
        .creditlist {
            background:white;
            width: 90%;
        }
        .creditlist tr{
            background:gray;
        }
        .creditlist td {
            background:white;
            padding: 10px 10px;
            
        }
		#colblack {
            background:gray;
        }
		.btn {
            float: left;
            background: violet;
			padding:10px 10px;
            margin: 5px 10px;
        }
	</style>
</head>
<body>
<?php
$sql = "select * from credit where uid = '$uid'";
$result = $conn->query($sql);
if($result->num_rows >0) {
?>
	<div class="credit">
		<table class="creditlist">
			<tr>
				<th id="colblack">주문날짜</th><th id="colblack">테이블번호</th><th>총 금액</th><th>내역</th>
			</tr>
			<?php
			while($row = $result->fetch_assoc()) {
			?>
			<tr>
				<td><?=$row['date']?></td>
				<td><?=$row['tablenum']?></td>
				<td><?=$row['totalprice']?></td>
				<td><a href="creditlistlist.php?creditno=<?=$row['creditno']?>&tablenum=<?=$row['tablenum']?>">상세보기(클릭)</a></td>
			</tr>
			<?php 
			}
}
?>
		</table>
		<a class="btn" href="main.php">뒤로가기</a></p>
	</div>
</body>
</html>