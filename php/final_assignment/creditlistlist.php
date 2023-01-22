<?php
session_start();
include_once('dbcon.php');
$uid = $_SESSION['uid'];
$creditno = $_GET['creditno'];
$tablenum = $_GET['tablenum'];

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
$sql = "select * from creditlist where creditno = '$creditno'";
$result = $conn->query($sql);
$no = 1;
if($result->num_rows >0) {
?>
	<div class="credit">
		<table class="creditlist">
			<tr>
				<th id="colblack">주문내역</th><th id="colblack">가격</th><th>수량</th><th></th>
			</tr>
			<?php
			while($row = $result->fetch_assoc()) {
			?>
			<tr>
				<td><?=$row['name']?></td>
				<td><?=$row['price']?></td>
				<td><?=$row['cnt']?></td>
				<td></td>
			</tr>
			<?php 
			}
}
?>
			<tr>
				<th id="colblack">주문번호</th><th><?=$creditno?></th><th id="colblack">테이블번호</th><th><?=$tablenum?></th>
			</tr>
		</table>
		<a class="btn" href="creditlist.php">뒤로가기</a></p>
	</div>
</body>
</html>