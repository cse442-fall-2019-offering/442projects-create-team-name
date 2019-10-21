<?php
$db_name = "cse442_542_2019_fall_teamv_db";
$mysql_username = "zjross";
$mysql_password = "50178127";
$server_name = "tethys.cse.buffalo.edu:3306";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

if($conn) {
echo "Successful Connection";
}

else {
echo "Connection was not successful...";
}

?>
