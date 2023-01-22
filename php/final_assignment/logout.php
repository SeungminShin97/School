<?php
session_start();
session_destroy();
echo "<script>";
echo "history.back();";
echo "</script>";
?>