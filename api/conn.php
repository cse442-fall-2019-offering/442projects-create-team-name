<?php
$db_name = "cse442_542_2019_fall_teamv_db";
$mysql_username = "zjross";
$mysql_password = "50178127";
$server_name = "tethys.cse.buffalo.edu:3306";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
mysqli_set_charset($conn, 'utf8');
if (mysqli_connect_errno()) {
	echo "Connection error: " . mysqli_connect_error();
}
?>