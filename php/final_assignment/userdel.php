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
<?php
session_start();
include_once('dbcon.php');
$uid = $_SESSION['uid'];

$sql = "delete from credit where uid = '$uid'";
$conn->query($sql);

$sql = "delete from menu where uid = '$uid'";
$conn->query($sql);

$sql = "delete from orderlist where uid = '$uid'";
$conn->query($sql);

$sql = "delete from ordertable where uid = '$uid'";
$conn->query($sql);



$sql = "delete from user where uid = '$uid'";
if($conn->query($sql)){
    session_destroy();?>
    <?php
} else {?>

<?php
}

?>
        <div class="login">
            <h3>회원탈퇴 완료</h3>
            <input type="button" value="확인" onclick="location.href='login.html'">
        </div>
    </body>
</html>