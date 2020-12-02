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


$query = "DELETE FROM rutxusua where id_usua2 = '".$codigo."'";
$consulta = pg_query($conexion, $query);

$query3 = "DELETE FROM asistencia where id_usua3 = '".$codigo."'";
$consulta3 = pg_query($conexion, $query3);

$query1 = "DELETE FROM usuario where id_usua = '".$codigo."'";
$consulta1 = pg_query($conexion, $query1);

$query2 = "DELETE FROM persona where cod_pers = '".$codigo."'";
$consulta2 = pg_query($conexion, $query2);



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