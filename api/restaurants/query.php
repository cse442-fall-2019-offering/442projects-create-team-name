<?php
require "../conn.php";

# Get the tags to include
if (isset($_GET['tags'])) $tags = $_GET['tags'];
else $tags = array();

# Get the tags to exclude
if (isset($_GET['exc'])) $exc = $_GET['exc'];
else $exc = array();

# If query should filter by tags
$num_tags = count($tags);
$num_ex = count($exc);

# Create a list of 's' to use when preparing
$var_str = implode(array_fill(0, $num_tags + $num_ex, 's'));

# Create prepared SQL statement
$sql =
    "SELECT * FROM Restaurants WHERE id IN (
        SELECT DISTINCT restaurant_id FROM Restaurant_Tags" .
            # If there are tags to filter by, include the WHERE clause
            ($num_tags > 0 ? " WHERE tag_id IN ( 
                SELECT id FROM Tags WHERE tag IN (" . implode(', ', array_fill(0, $num_tags, '?')) . ") )" : "") .
            ") " .
            # If there are tags to exclude, include the EXCEPT clause
            ($num_ex > 0 ? " AND id NOT IN (
            SELECT DISTINCT restaurant_id FROM Restaurant_Tags WHERE tag_id IN (
                SELECT id FROM Tags WHERE tag IN (" . implode(', ', array_fill(0, $num_ex, '?')) . ") ) )" : "");

# Prepare, Bind and execute
if($stmt = mysqli_prepare($conn, $sql)) {
	if($num_tags + $num_ex > 0){
		# Create parameter array to send to bind_params
        $bind_params = array($stmt, $var_str);
		$bind_params = array_merge($bind_params, $tags, $exc);

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