CREATE TABLE usuarios(
  id bigint not null auto_increment,
  nombre varchar(100) not null unique,
  contrasena varchar(100)not null,
  correo varchar(100) not null unique ,

  primary key(id)

)