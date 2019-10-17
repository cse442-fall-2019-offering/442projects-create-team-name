<?php
require "conn.php";

$selection_tag = "";
$mysql_qry = "select * from Tags where tag like '$selection_tag';";
$result = mysqli_query($conn, $mysql_qry);
if(mysqli_num_rows($result) > 0) {
echo "Found result.";
}
else {
echo "No results found..."
}
?>
