<!DOCTYPE html>
<html>
    <head>
        <title>메뉴 생성</title>
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
            <h1>메뉴 생성</h1>
            <form action="makemenu.php" method="post">
                <table>
                <?php
                session_start();
                include_once('dbcon.php');

                $menuname = $_POST['menuname'];
                $cat = $_POST['cat'];
                $price = $_POST['price'];
                $uid = $_SESSION['uid'];

                $sql = "insert into menu(uid, name, cat, price) values ('$uid','$menuname','$cat',$price)";
                if($conn->query($sql)) {?>
                    <h4>메뉴 생성 성공</h4>
                <?php
                } else {?>
                    <h4>메뉴 생성 실패</h4>
                <?php echo $conn->error;}
                ?>
                    <tr>
                        <td></td>
                        <td><input type="button" value="설정창" onclick="location.href='setting.html'"></td>
                        <td><input type="button" value="추가로 만들기" onclick="location.href='makemenu.html'"></td>
                    </tr>
                </table>
            </form> 
        </div>
    </body>
</html>