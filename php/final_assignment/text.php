<!doctype html>
<html>
    <head>
        <title>포스기</title>
        <meta charset="utf-8">
        <link rel='stylesheet' type='text/css' href='style.php' media="screen">
        <style>
            body {
                background-color: cadetblue;
            }
            .topnav {
                background-color: #333;
                overflow: hidden;
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
            .grid > div {
                border: 2px solid #ffa94d;
                border-radius: 5px;
                background-color: #ffd8a8;
                padding: 10px;
                /*width: 50px;
                height: 50px;*/
            }
            .grid {
                background-color: gray;
                border: 1px dotted blue;
                height: 70%;
                display: grid;
                /*grid-template-columns: 200px auto 30%;
                grid-template-columns: repeat(3,1fr);
                grid-template-columns: 200px repeat(2,1fr);
                grid-auto-rows: 100px;
                grid-template-rows: 100px 200px;
                align-content: flex-end;  flex-end 아래서부터, flex-cente 가운데
                align-content: space-around;
                align-items: stretch;*/
                column-gap: 10px; 
                row-gap: 10px;
                grid-template-areas: 
                    "b1 b1 b1 b1"
                    "b2 b3 b3 b3"
                    "b4 b3 b3 b3"
                    "b5 b5 b6 b6"
                ;
            }
            .box1 {
                /*grid-row-start: 1;
                grid-row-end: 2;
                grid-column-start: 1;
                grid-column-end: 2;
                grid-area: 1/1/2/2;*/
                grid-area: b1;
            }
            .box2 {
                /*grid-row-start: 1;
                grid-row-end: 2;
                grid-column-start: 3;
                grid-column-end: 4;*/
                grid-area: b2;
            }
            .box3 {
                /*grid-row-start: 1;
                grid-row-end: 2;
                grid-column-start: 3;
                grid-column-end: 4;
                grid-area: 1/4/2/5;*/
                grid-area: b3;
            }
            .box4 {
                /*grid-row-start: 2;
                grid-row-end: 3;
                grid-column-start: 1;
                grid-column-end: 2;*/
                grid-area: b4;
            }
            .box5 {
                /*grid-row-start: 2;
                grid-row-end: 3;
                grid-column-start: 2;
                grid-column-end: 4;*/
                grid-area: b5;
            }
            .box6 {
                /*grid-row-start: 2;
                grid-row-end: 3;
                grid-column-start: 4;
                grid-column-end: 5;*/
                grid-area: b6;
                font-size: 50px;
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="topnav"> 
            <a href="#home">Home</a>
            <a href="#home">Menu</a>
            <a href="#home">News</a>
            <a href="#home">About</a>
        </div>

        <div class="grid">
            <div class="box1">1</div>
            <div class="box2">2</div>            
            <div class="box3">3</div>
            <div class="box4">4</div>
            <div class="box5">5</div>
            <div class="box6">6</div>
        </div>
    </body>
</html>