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


$query = "select  id_rut,rutina.nombre,tiempo from ((usuario inner join persona on usuario.id_usua=persona.cod_pers) inner join rutxusua on usuario.id_usua=rutxusua.id_usua2) inner join rutina on rutxusua.id_rut2=rutina.id_rut where cod_pers='".$codigo."' ";
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
