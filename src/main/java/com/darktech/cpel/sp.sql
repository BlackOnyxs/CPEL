DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `CareerSearch`(IN `name` VARCHAR(100))
BEGIN
	select * from carreras where nombre_carrera like concat('%', name, '%')
    order by idcarrera asc limit 10;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `CarrerSave`(IN `idCareer` INT, IN `nameCareer` VARCHAR(20))
BEGIN
    IF EXISTS
        (
        SELECT
            idcarrera
        FROM
            carreras
        WHERE
            idcarrera = idCareer
    ) THEN
UPDATE
    carreras
SET
    nombre_carrera = nameCareer
WHERE
    idcarrera = idCareer;
SELECT
    *
FROM
    carreras
WHERE
    idcarrera = idCareer; ELSE
INSERT INTO carreras(idcarrera, nombre_carrera)
VALUES(NULL, nameCareer);
SELECT
    *
FROM
    carreras
ORDER BY
    idcarrera
DESC
LIMIT 1;
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EquipmentCreateOrUpdate`(IN `idEquipment` INT, IN `idCategory` INT, IN `description` VARCHAR(16), IN `idState` INT, IN `model` VARCHAR(16), IN `inventoryBoard` VARCHAR(16), IN `purchaseDate` VARCHAR(16), IN `image` BLOB)
BEGIN
    IF EXISTS
        (
        SELECT
            idequipo
        FROM
            equipos
        WHERE
            idequipo = idEquipment
    ) THEN
UPDATE
    equipos
SET
    idcategoria = idCategory,
    descripcion = description,
    idestado_equipo = idState,
    modelo = model,
    placa_inventario = inventoryBoard,
    fecha_compra = purchaseDate,
    foto = image
WHERE
    idequipo = idEquipment ; 
 SELECT
    E.idequipo,
    E.idcategoria,
    C.nombre_categoria,
    E.descripcion,
    E.idestado_equipo,
    EE.descripcion as 'estado_descipcion',
    E.modelo,
    E.placa_inventario,
    E.fecha_compra,
    E.foto
FROM
    equipos E
JOIN categoria_equipos C ON
    C.idcategoria = E.idcategoria
JOIN estado_equipos EE ON
    EE.idestado_equipo = E.idestado_equipo
    where E.idequipo = idEquipment;
    
    ELSE
INSERT INTO equipos(
    idequipo,
    idcategoria,
    descripcion,
    idestado_equipo,
    modelo,
    placa_inventario,
    fecha_compra,
    foto
)
VALUES(
    NULL,
    idCategory,
    description,
    idState,
    model,
    inventoryBoard,
    purchaseDate,
    image
) ;
SELECT
    E.idequipo,
    E.idcategoria,
    C.nombre_categoria,
    E.descripcion,
    E.idestado_equipo,
    EE.descripcion as 'estado_descipcion',
    E.modelo,
    E.placa_inventario,
    E.fecha_compra,
    E.foto
FROM
    equipos E
JOIN categoria_equipos C ON
    C.idcategoria = E.idcategoria
JOIN estado_equipos EE ON
    EE.idestado_equipo = E.idestado_equipo
    order by idequipo desc limit 1;
END IF ;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EquipmentListASC`(IN `skip` INT)
begin 
	select E.idequipo, E.idcategoria, C.nombre_categoria, E.descripcion, E.idestado_equipo, EE.descripcion as 'estado_descipcion', E.modelo, E.placa_inventario, E.fecha_compra, E.foto from equipos E 
    join categoria_equipos C on C.idcategoria = E.idcategoria
    join estado_equipos EE on EE.idestado_equipo = E.idestado_equipo
    order by E.idequipo asc 
    limit 10
    offset skip;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EquipmentListDESC`(IN `skip` INT)
begin 
	select E.idequipo, E.idcategoria, C.nombre_categoria, E.descripcion, E.idestado_equipo, EE.descripcion as 'estado_descipcion', E.modelo, E.placa_inventario, E.fecha_compra, E.foto from equipos E 
    join categoria_equipos C on C.idcategoria = E.idcategoria
    join estado_equipos EE on EE.idestado_equipo = E.idestado_equipo
    order by E.fecha_compra DESC 
    limit 10
    offset skip;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EquipmentLoanById`(IN `idPrestamo` INT)
BEGIN
    	select P.idprestamo, P.fecha_solicitud, E.idequipo, E.modelo, P.cedula_usuario, (CONCAT( U.primer_nombre,' ', U.primer_apellido)) as 'Solicitante', P.cedula_operador_prestamo, (Concat(O.primer_nombre, ' ', O.primer_apellido)) as 'Operador', P.idestado_devolucion, EE.estado_devolucion, P.observacion from prestamos as P 
        join operadores as O on P.cedula_operador_prestamo = O.cedula 
        join equipos as E on E.idequipo = P.idequipo
        join usuarios as U on U.cedula = P.cedula_usuario
        join estado_devolucion as EE on EE.idestado_devolucion = P.idestado_devolucion
        where p.idprestamo = idPrestamo;
    END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EquipmentLoanDESC`(IN `skip` INT)
BEGIN
    SELECT
        P.idprestamo,
        P.fecha_solicitud,
        E.idequipo,
        E.modelo,
        P.cedula_usuario,
        (
            CONCAT(
                U.primer_nombre,
                ' ',
                U.primer_apellido
            )
        ) AS 'Solicitante',
        P.fecha_devolucion,
        P.cedula_operador_prestamo, (Concat(OP.primer_nombre, ' ', OP.primer_apellido)) as 'OperadorS', P.cedula_operador_devolucion, (Concat(OD.primer_nombre, ' ', OD.primer_apellido)) as 'OperadorD', 
        P.idestado_devolucion,
        EE.estado_devolucion,
        P.observacion
    FROM
        prestamos AS P
    JOIN operadores AS OP
    ON
        P.cedula_operador_prestamo = OP.cedula
    Join operadores as OD
    on 
    	P.cedula_operador_devolucion = OD.cedula
    JOIN equipos AS E
    ON
        E.idequipo = P.idequipo
    JOIN usuarios AS U
    ON
        U.cedula = P.cedula_usuario
    JOIN estado_devolucion AS EE
    ON
        EE.idestado_devolucion = P.idestado_devolucion
    ORDER BY
        P.idprestamo DESC
    LIMIT 10 OFFSET skip ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EquipmentLoanListASC`(IN `skip` INT)
BEGIN
    	select P.idprestamo, P.fecha_solicitud, E.idequipo, E.modelo, P.cedula_usuario, (CONCAT( U.primer_nombre,' ', U.primer_apellido)) as 'Solicitante', P.cedula_operador_prestamo, (Concat(OP.primer_nombre, ' ', OP.primer_apellido)) as 'OperadorS',P.fecha_devolucion, P.cedula_operador_devolucion, (Concat(OD.primer_nombre, ' ', OD.primer_apellido)) as 'OperadorD', P.idestado_devolucion, EE.estado_devolucion, P.observacion from prestamos as P 
        join operadores as OP on P.cedula_operador_prestamo = OP.cedula 
        join operadores as OD on P.cedula_operador_devolucion = OD.cedula 
        join equipos as E on E.idequipo = P.idequipo
        join usuarios as U on U.cedula = P.cedula_usuario
        join estado_devolucion as EE on EE.idestado_devolucion = P.idestado_devolucion
        order by P.idprestamo ASC 
        limit 10 
        OFFSET skip;
    END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EquipmentLoanSave`(IN `idLoan` INT, IN `requestDate` VARCHAR(50), IN `idEquipment` INT, IN `idUser` VARCHAR(16), IN `idRequestOperator` VARCHAR(16), IN `returnDate` VARCHAR(50), IN `idReturnOperator` VARCHAR(16), IN `idState` INT, IN `observation` VARCHAR(50))
BEGIN
    IF EXISTS
        (
        SELECT
            idprestamo
        FROM
            prestamos
        WHERE
            idprestamo = idLoan
    ) THEN
UPDATE
    prestamos
SET
    fecha_devolucion = returnDate,
    cedula_operador_devolucion = idReturnOperator,
    idestado_devolucion = idState,
    observacion = observation
WHERE
    idprestamo = idLoan ;
SELECT
    P.idprestamo,
    P.fecha_solicitud,
    E.idequipo,
    E.modelo,
    P.cedula_usuario,
    (
        CONCAT(
            U.primer_nombre,
            ' ',
            U.primer_apellido
        )
    ) AS 'Solicitante',
    P.cedula_operador_prestamo,
    (
        CONCAT(
            OP.primer_nombre,
            ' ',
            OP.primer_apellido
        )
    ) AS 'OperadorS',
    P.fecha_devolucion,
    P.cedula_operador_devolucion,
    (
        CONCAT(
            OD.primer_nombre,
            ' ',
            OD.primer_apellido
        )
    ) AS 'OperadorD',
    P.idestado_devolucion,
    EE.estado_devolucion,
    P.observacion
FROM
    prestamos AS P
JOIN operadores AS OP
ON
    P.cedula_operador_prestamo = OP.cedula
JOIN operadores AS OD
ON
    P.cedula_operador_devolucion = OD.cedula
JOIN equipos AS E
ON
    E.idequipo = P.idequipo
JOIN usuarios AS U
ON
    U.cedula = P.cedula_usuario
JOIN estado_devolucion AS EE
ON
    EE.idestado_devolucion = P.idestado_devolucion
WHERE
    P.idprestamo = idLoan ; ELSE
INSERT INTO prestamos(
    idprestamo,
    fecha_solicitud,
    idequipo,
    cedula_usuario,
    cedula_operador_prestamo,
    fecha_devolucion,
    cedula_operador_devolucion,
    idestado_devolucion,
    observacion
)
VALUES(
    NULL,
    requestDate,
    idEquipment,
    idUser,
    idRequestOperator,
    returnDate,
    idReturnOperator,
    idState,
    observation
) ;
SELECT
    P.idprestamo,
    P.fecha_solicitud,
    E.idequipo,
    E.modelo,
    P.cedula_usuario,
    (
        CONCAT(
            U.primer_nombre,
            ' ',
            U.primer_apellido
        )
    ) AS 'Solicitante',
    P.cedula_operador_prestamo,
    (
        CONCAT(
            OP.primer_nombre,
            ' ',
            OP.primer_apellido
        )
    ) AS 'OperadorS',
    P.fecha_devolucion,
    P.cedula_operador_devolucion,
    (
        CONCAT(
            OD.primer_nombre,
            ' ',
            OD.primer_apellido
        )
    ) AS 'OperadorD',
    P.idestado_devolucion,
    EE.estado_devolucion,
    P.observacion
FROM
    prestamos AS P
JOIN operadores AS OP
ON
    P.cedula_operador_prestamo = OP.cedula
JOIN operadores AS OD
ON
    P.cedula_operador_devolucion = OD.cedula
JOIN equipos AS E
ON
    E.idequipo = P.idequipo
JOIN usuarios AS U
ON
    U.cedula = P.cedula_usuario
JOIN estado_devolucion AS EE
ON
    EE.idestado_devolucion = P.idestado_devolucion
ORDER BY
    idequipo
DESC
LIMIT 1 ;
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EquipmentLoanSearch`(IN `searchValue` VARCHAR(20))
BEGIN
    	select P.idprestamo, P.fecha_solicitud, E.idequipo, E.modelo, P.cedula_usuario, (CONCAT( U.primer_nombre,' ', U.primer_apellido)) as 'Solicitante', P.cedula_operador_prestamo, (Concat(OP.primer_nombre, ' ', OP.primer_apellido)) as 'OperadorS', P.cedula_operador_devolucion, (Concat(OD.primer_nombre, ' ', OD.primer_apellido)) as 'OperadorD', 
        P.idestado_devolucion, EE.estado_devolucion, P.observacion, P.fecha_devolucion from prestamos as P 
        join operadores as OP on P.cedula_operador_prestamo = OP.cedula 
        join operadores as OD on P.cedula_operador_prestamo = OD.cedula 
        join equipos as E on E.idequipo = P.idequipo
        join usuarios as U on U.cedula = P.cedula_usuario
        join estado_devolucion as EE on EE.idestado_devolucion = P.idestado_devolucion
        where P.fecha_solicitud like concat('%', searchValue, '%') or U.cedula like concat('%', searchValue, '%') or P.idestado_devolucion = (convert(searchValue, int)) or E.descripcion like concat('%', searchValue, '%') or P.cedula_operador_prestamo like concat('%', searchValue, '%') or P.cedula_operador_devolucion like concat('%', searchValue, '%') or E.modelo like concat('%', searchValue, '%')
        order by P.fecha_solicitud asc limit 10;
    END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EquipmentSearch`(IN `searchValue` VARCHAR(16))
begin 
	select E.idequipo, E.idcategoria, C.nombre_categoria, E.descripcion, E.idestado_equipo, EE.descripcion as 'estado_descipcion', E.modelo, E.placa_inventario, E.fecha_compra, E.foto from equipos E 
    join categoria_equipos C on C.idcategoria = E.idcategoria
    join estado_equipos EE on EE.idestado_equipo = E.idestado_equipo
    where E.modelo like concat('%', searchValue, '%') or E.placa_inventario like concat('%', searchValue, '%') or C.nombre_categoria like concat('%', searchValue, '%') or EE.descripcion  like concat('%', searchValue, '%')
    order by E.idequipo asc
    limit 10;
    
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `OperatorCreateOrUpdate`(in idUser varchar(16), firstName varchar(16), lastname varchar(16))
begin 
	if exists ( select cedula from operadores where cedula like idUser) then 
    	update operadores 
        	set primer_nombre = firstName, primer_apellido = lastname
        where cedula = idUser;
    else 
    	insert into operadores(cedula, primer_nombre, primer_apellido)values(idUser, firstName, lastname);
    end if;
    select * from operadores where cedula like idUser;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `OperatorListASC`(IN `skip` INT)
begin 
	select * from operadores 
    order by primer_apellido asc
    limit 10
    OFFSET skip;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `OperatorListDesc`(IN `skip` INT)
begin 
	select * from operadores 
    order by primer_apellido DESC
    limit 10
    OFFSET skip;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `OperatorSearch`(IN `searchValue` VARCHAR(16))
begin 
	select * from operadores
    where cedula like concat('%', name, '%') or primer_nombre like concat('%', name, '%') or primer_apellido like concat('%', name, '%')
    order by primer_apellido asc 
    limit 10;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `UserCreateOrUpdate`(IN `idUser` VARCHAR(16), IN `firstName` VARCHAR(25), IN `lastname` VARCHAR(25), IN `phone` VARCHAR(25), IN `email` VARCHAR(50), IN `idCareer` INT, IN `userType` INT)
BEGIN
    IF EXISTS
        (
        SELECT
            cedula
        FROM
            usuarios
        WHERE
            cedula = idUser
    ) THEN
UPDATE
    usuarios
SET
    primer_nombre = firstName,
    primer_apellido = lastname,
    telefono = phone,
    correo = email,
    idcarrera = idCareer,
    tipousuario = userType
WHERE
    cedula = idUser ; ELSE
INSERT INTO usuarios(
    cedula,
    primer_nombre,
    primer_apellido,
    telefono,
    correo,
    idcarrera,
    tipousuario
)
VALUES(
    idUser,
    firstName,
    lastname,
    phone,
    email,
    idCareer,
    userType
) ;
END IF;
select U.cedula, U.primer_nombre, U.primer_apellido, U.telefono, U.correo, T.tipousuario, T.descripcion as 'tipo_usuario', C.idcarrera, C.nombre_carrera as 'career_name'
    from usuarios U 
    join tipos_usuarios T on T.tipousuario = U.tipousuario
    join carreras C on C.idcarrera = U.idcarrera
    where U.cedula = idUser;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `UserSearch`(IN `searchValue` VARCHAR(15))
BEGIN
	select U.cedula, U.primer_nombre, U.primer_apellido, U.telefono, U.correo, T.tipousuario, T.descripcion as 'tipo_usuario', C.idcarrera, C.nombre_carrera as 'career_name'
    from usuarios U 
    join tipos_usuarios T on T.tipousuario = U.tipousuario
    join carreras C on C.idcarrera = U.idcarrera 
    where U.cedula like concat('%', searchValue, '%') or U.primer_nombre like concat('%', searchValue, '%') or U.primer_apellido like concat('%', searchValue, '%');
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `UsersListASC`(IN `skip` INT)
BEGIN
	select U.cedula, U.primer_nombre, U.primer_apellido, U.telefono, U.correo, T.tipousuario, T.descripcion as 'tipo_usuario', C.idcarrera, C.nombre_carrera as 'career_name'
    from usuarios U 
    join tipos_usuarios T on T.tipousuario = U.tipousuario
    join carreras C on C.idcarrera = U.idcarrera
    order by U.primer_apellido ASC
    limit 10
    OFFSET skip;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `UsersListDSC`(IN `skip` INT)
BEGIN
	select U.cedula, U.primer_nombre, U.primer_apellido, U.telefono, U.correo, T.tipousuario, T.descripcion as 'tipo_usuario', C.idcarrera, C.nombre_carrera as 'career_name'
    from usuarios U 
    join tipos_usuarios T on T.tipousuario = U.tipousuario
    join carreras C on C.idcarrera = U.idcarrera
    order by U.primer_apellido DESC
    limit 10
    OFFSET skip;
END$$
DELIMITER ;
