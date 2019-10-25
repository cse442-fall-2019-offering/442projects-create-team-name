<?php
require "conn.php";

# Query the results containing all the tags
$sql = "SELECT * FROM Food"; 
if (isset($_GET['tags'])) {
	foreach ($_GET['tags'] as $tag){
		$sql = "SELECT * FROM (" . $sql . ") AS T
					WHERE tag1='" . $tag . "' OR 
					tag2='" . $tag . "' OR 
					tag3='" . $tag . "'";
	}
}

# If there are results, add them all to an array and encode in JSOn
if ($result = mysqli_query($conn, $sql))
{
	$resultArray = array();
	 
	while($row = mysqli_fetch_object($result)) {
		$resultArray[] = $row;
	}
	 
	echo json_encode($resultArray);
}

mysqli_close($conn);
?>