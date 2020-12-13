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

$codigo=$_GET['fecha'];


$query = "SELECT id_fac,factura.id_usua,descuento.nombre as nombred,descuento.monto as precioD,precio.nombre,precio.dias,precio.precio,factura.monto,fecha_inicial,estado,fecha_final,cedula FROM (((factura inner join precio on factura.id_prec=precio.id_pre) inner join descuento on factura.id_descuento=descuento.id_desc) inner join usuario on factura.id_usua=usuario.id_usua) inner join persona on usuario.id_usua=persona.cod_pers  where fecha_inicial = '".$codigo."'";
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