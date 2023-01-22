<!DOCTYPE html>
<html>
    <head>
        <title>메뉴 삭제</title>
        <meta charset="utf-8">
        <style>
            body {
                background-color: cadetblue;
            }
            .login {
                background-color: white;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%,-50%);
                text-align: center;
            }
            table {
                float: left;
                border: 2px solid black;
				  border-collapse: collapse;
				  margin: 10px 10px;
            }
            table td, th, tr {
                text-align: center;
                border: 2px solid black;
            }
            .btn {
			padding: 16px 20px;
			border: none;
			cursor: pointer;
			width: 70%;
			opacity: 0.9;
			margin-top: 10px;
		}
		.btn:hover {
			background-color: burlywood;
		}
        p a {
                width: 100px;
                height: 100px;
                padding: 10px 100px;
                margin: 10px 10px;
                text-decoration: none;
                color: black;
            }
        p a:hover{
            background-color: burlywood;
        }
        </style>
    </head>
    <body>
        <div class="login">
            <h1>메뉴 삭제</h1>
            <form action="delmenudetail.php" method="get">
            <?php
            session_start();
            include_once('dbcon.php');
            $uid = $_SESSION['uid'];
            $sql = "select * from menu where uid = '$uid' and cat = 'main'";
            $result = $conn->query($sql);
            ?>
                <table>
                    <tr><td row="3">메인메뉴</td></tr>
                    <tr><th></th><th></th><th>메뉴명</th><th>가격</th></tr>
                    <?php
                    if($result->num_rows >0) {
                        $no = 1;
                        while($row = $result->fetch_assoc()) { ?>
                    <tr>
                        <td><input type="checkbox" name="chk[]" value="<?=$row['seqno']?>"></td>
                        <td><?=$no?></td>
                        <td><?=$row['name']?></td>
                        <td><?=$row['price']?></td>
                    </tr>
                    <?php $no++; }} ?>       
                </table>
            <?php 
            $sql = "select * from menu where uid = '$uid' and cat = 'side'";
            $result = $conn->query($sql);
            ?>
                <table>
                    <tr><td row="3">사이드메뉴</td></tr>
                    <tr><th></th><th></th><th>메뉴명</th><th>가격</th></tr>
                    <?php
                    if($result->num_rows >0) {
                        $no = 1;
                        while($row = $result->fetch_assoc()) { ?>
                    <tr>
                        <td><input type="checkbox" name="chk[]" value="<?=$row['seqno']?>"></td>
                        <td><?=$no?></td>
                        <td><?=$row['name']?></td>
                        <td><?=$row['price']?></td>
                    </tr>
                    <?php $no++; }} ?>       
                </table>  
                <?php 
            $sql = "select * from menu where uid = '$uid' and cat = 'drink'";
            $result = $conn->query($sql);
            ?>
                <table>
                    <tr><td row="3">음료</td></tr>
                    <tr><th></th><th></th><th>메뉴명</th><th>가격</th></tr>
                    <?php
                    if($result->num_rows >0) {
                        $no = 1;
                        while($row = $result->fetch_assoc()) { ?>
                    <tr>
                        <td><input type="checkbox" name="chk[]" value="<?=$row['seqno']?>"></td>
                        <td><?=$no?></td>
                        <td><?=$row['name']?></td>
                        <td><?=$row['price']?></td>
                    </tr>
                    <?php $no++; }} ?>       
                </table>
                <button type="submit" class="btn">삭제하기</button>
                <p><a href="setting.html">뒤로가기</a></p>
            </form> 
        </div>
    </body>
</html>