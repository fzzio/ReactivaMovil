<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	
    if (isset($_POST['nombre']) ) {
        // Obtener parámetro idMeta
        $nombre = $_POST['nombre'];
		$pass = $_POST['pass'];
		if($nombre=='erick' && $pass=='rocafuerte'){
			echo 'Login exitoso, Bienvenido a Reactiva Movil';
		}else{
			echo 'errorrrrrrr login no valido';
		}
		
    }else{
		echo 'no habia parametro nombre y/o pass';
	}
}else{
	echo 'se fue por get';
}

?>