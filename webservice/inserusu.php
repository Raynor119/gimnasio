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
$cedula=$_GET['cedula'];
$nombre=$_GET['nombre'];
$apellido=$_GET['apellido'];
$edad=$_GET['edad'];
$direccion=$_GET['direccion'];
$telefono=$_GET['telefono'];
$correo=$_GET['correo'];
$usuario=$_GET['usaurio'];
$contrasena=$_GET['contrasena'];
$datosm=$_GET['datom'];

$query = "insert into persona values('".$codigo."','".$cedula."','".$nombre."','".$apellido."','".$edad."','".$direccion."','".$telefono."','".$correo."','".$usuario."','".$contrasena."')";
$consulta = pg_query($conexion, $query);

$query1 = "insert into usuario(id_usua,dat_med) values('".$codigo."','".$datosm."')";
$consulta2 = pg_query($conexion, $query1);


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