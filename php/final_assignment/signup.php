<!DOCTYPE html>
<html>
    <head>
        <title>회원가입</title>
        <meta charset="utf-8">
        <style>
            body {
                background-color: cadetblue;
            }
            .signup {
                background-color: white;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%,-50%);
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class=signup>
            <?php
            include_once('dbcon.php');

            $uid = $_POST['uid'];
            $pwd = $_POST['pwd'];
            $name = $_POST['name'];

            $sql = "insert into user values('$uid','$pwd','$name')";
            if($conn->query($sql)){ ?>
            <h3>회원가입을 축하드립니다!!<br></h3>
            <?php } else  {?>
            <h3>오류가 발생했습니다.<br></h3>
            <?php } ?>
            <input type="button" value="돌아가기" onclick="location.href='login.html'">
        </div>
    </body>
</html>