<!doctype html>
<html>
    <head>
        <title>포스기</title>
        <meta charset="utf-8">
        <link href="main.css" type="text/css" rel="stylesheet">
        <?php
        session_start();
        include_once('dbcon.php');
        ?>
        <style>
            body {
                background-color: cadetblue;
            }
            .topnav {
                background-color: #333;
                overflow: hidden;
                margin-bottom: 30px;
            }
            .topnav a{
                color: #f2f2f2;
                text-align: center;
                text-decoration: none;
                font-size: 17px;
                padding: 14px 30px;
                float: left;          
                border: 2px solid white;
            }
            .topnav a:hover{
                background-color: #ddd;
                color: black;
            }
            .signup {
                background-color: white;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%,-50%);
                text-align: center;
            }
            .signup a {
                text-decoration: none;
                color: black;
                font-size: 30px;
                background: white;
            }
            .signup a:hover {
                background: gray;
            }
        
        </style>
    </head>
    <body>
        <?php
        if(!isset($_SESSION['uid'])){?>
        <div class=signup>
            <h3>로그인을 해주세요</h3>
            <a href="login.html">로그인</a>
        </div>
        <?php
        } else {?>
        <div class="topnav"> 
            <a href="main.php">홈</a>
            <a href="creditlist.php">결제내역</a>
            <a href="setting.html">설정</a>
            <a href="logout.php">로그아웃</a>
        </div>
        <div class="grid">
            <?php 
            for($i=1; $i<26; $i++){ ?>
                <div id="box<?=$i?>" class="box" onclick="location.href='order.php?tablenum=<?=$i?>&menu=main'">
                     <p><?=$i?></p>
						<p>
							<?php
							$uid = $_SESSION['uid'];
							$sql = " select * from ordertable where uid = '$uid' and tablenum = '$i'";
							$result = $conn->query($sql);
							if($result->num_rows > 0){
								$cnt = 0;
								$nprice = 0;
								while($row = $result->fetch_assoc()) {
									if($cnt < 2){?>
										<p><?=$row['name']?>....<?=$row['cnt']?></p>
									<?php
									} 
									$cnt++;
									$nprice += $row['price'] * $row['cnt'];
								}?>
									<p> ... <p>
									<p>주문 갯수 : <?=$cnt?> <p>
									<p> 금   액  : <?=$nprice?><p>
							<?php
							}
							?>
						</p>
                </div>
            <?php } ?>
        </div>
        <?php }?>
    </body>
</html>