<?php


$host = "127.0.0.1";


$port = "5432";


$user = "postgres";


$database="GIMNASIO_BD";


$password="67895421d";


$conexion=pg_connect("host=$host port=$port user=$user dbname=$database password=$password");



//si fallara la conexion con la BD 



if (!$conexion) { 


echo "error en la conexion"; 


}


else


{ 

$codigo=$_GET['codigo'];
$id=$_GET['id_rut'];

$query = "DELETE FROM rutxusua where id_rut2 = '".$id."' and id_usua2 = '".$codigo."'";
$consulta = pg_query($conexion, $query);


if(!$consulta){


}else{


while($row = pg_fetch_array($consulta)){


$flag[] =$row; 


} 


print(json_encode($flag));


}


pg_close($conexion);


}



?>