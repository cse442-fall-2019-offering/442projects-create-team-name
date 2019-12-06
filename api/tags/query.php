<?php
require "../conn.php";

$sql = "SELECT tag From Tags";

if (isset($_GET['rest_id'])){
    $sql = $sql . " WHERE id IN (SELECT tag_id FROM Restaurant_Tags WHERE restaurant_id = ?)";
}

if ($stmt = mysqli_prepare($conn, $sql)) {
    if(isset($_GET['rest_id'])){
        mysqli_stmt_bind_param($stmt,'i',$_GET['rest_id']);
    }

    mysqli_stmt_execute($stmt);

    if ($result = mysqli_stmt_get_result($stmt)) {
        $resultArray = array();
        while($row = mysqli_fetch_assoc($result)) {
            $resultArray[] = $row['tag'];
        }
        echo json_encode($resultArray);
    }

    mysqli_stmt_close($stmt);
}

mysqli_close($conn);
?>