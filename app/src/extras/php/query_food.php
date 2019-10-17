<?php
require "conn.php";

$selection_name = "";
$mysql_qry = "select * from Food where name like '$selection_name';";
$result = mysqli_query($conn, $mysql_qry);
if(mysqli_num_rows($result) > 0) {
echo "Found result.";
}
else {
echo "No results found..."
}
?>
