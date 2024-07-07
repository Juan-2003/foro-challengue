CREATE TABLE topicos(
    id bigint not null auto_increment,
    mensaje varchar(1000) not null,
    titulo_publicacion varchar(100) not null,
    fecha_publicacion datetime not null,
    estatus tinyint not null,
    usuario_id bigint not null,
    curso_id bigint not null,

    primary key(id),
    foreign key (usuario_id) references usuarios(id),
    foreign key (curso_id) references cursos(id)

)