
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
        </style>
    </head>
    <body>
    
        <div class="login">
        <?php
        include_once('dbcon.php');
        $chk = $_GET['chk'];
        $count = count($chk);
        for($i=0; $i<$count; $i++){
            $no = $chk[$i];
            $sql = "delete from menu where seqno = $no";
            if($conn->query($sql)) {
            }
            else {?>
                <h3>삭제 실패</h3>
                <h4><?=$conn->error?></h4><?php
            }
        }
        ?>  
        <h3>삭제완료</h3>
        <input type="button" value="확인" onclick="location.href='setting.html'"></td>
        </div>
    </body>
</html>