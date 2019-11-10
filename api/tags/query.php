<?php
require "../conn.php";

$sql = "SELECT tag From Tags";

if ($stmt = mysqli_prepare($conn, $sql)) {
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