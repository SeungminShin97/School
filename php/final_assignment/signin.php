
<!DOCTYPE html>
<html>
    <head>
        <title>포스기</title>
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
        </style>
    </head>
    <body>
        <div class="login">
            <?php
            session_start();
            include_once('dbcon.php');
            $uid=$_POST['uid'];
            $pwd=$_POST['pwd'];
            $sql="select * from user where uid = '$uid' and pwd = '$pwd'";
            $result=$conn->query($sql);
            if($result->num_rows > 0) {
                $r = $result->fetch_assoc();
                $name = $r['name'];
                $_SESSION['uid'] =  $uid;
                $_SESSION['name'] = $name;
            ?>
            <h3>환영합니다!!!</h3>
            <input type="button" value="확인" onclick="location.href='main.php'">
            <?php } else { ?>
            <p>아이디 혹은 패스워드가 틀렸습니다.</p>
            <input type="button" value="뒤로가기" onclick="location.href='login.html'">
            <?php }?>
        </div>
    </body>
</html>