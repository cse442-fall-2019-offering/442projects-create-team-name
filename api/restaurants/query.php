<?php
require "../conn.php";

$sql = "SELECT * FROM Restaurants";

# If query should filter by tags
if (isset($_GET['tags'])){
	$num_tags = count($_GET['tags']);
	
	# Create list of '?' to put in SQL statement
	$q_array = array_fill(0, $num_tags, '?');
	
	# Create a list of 's' to use when preparing
	$var_array = array_fill(0, $num_tags, 's');
	$var_str = implode($var_array);
		
	# Create prepared SQL statement
	$sql = $sql . " WHERE id IN (
		SELECT DISTINCT restaurant_id FROM Restaurant_Tags WHERE tag_id IN (
			SELECT id FROM Tags WHERE tag IN (" . implode(', ', $q_array) . ") 
		) 
	)";
}

# Prepare, Bind and execute
if($stmt = mysqli_prepare($conn, $sql)) {
	if(isset($_GET['tags'])){
		# Create parameter array to send to bind_params
		$bind_params = $_GET['tags'];
		array_unshift($bind_params, $stmt, $var_str);

		# Bind the parameters
		call_user_func_array("mysqli_stmt_bind_param", $bind_params);
	}
	
	mysqli_stmt_execute($stmt);
	# If there are results, add them all to an array and encode in JSON
	if($result = mysqli_stmt_get_result($stmt)) {
		$resultArray = array();
		while($row = mysqli_fetch_object($result)) {
			$resultArray[] = $row;
		}
		echo json_encode($resultArray);
	}

	mysqli_stmt_close($stmt);
}

mysqli_close($conn);
?>