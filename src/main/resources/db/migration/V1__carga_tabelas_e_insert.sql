create table tb_carro (
        id integer generated by default as identity,
        data_fabricacao date,
        marca varchar(255),
        modelo varchar(255),
        status varchar(255),
        primary key (id));

create table tb_funcionario (
    id integer generated by default as identity,
    matricula integer,
    nome varchar(255),
    primary key (id));
    
create table tb_role (
       id bigint generated by default as identity,
        authority varchar(255),
        primary key (id));
        
create table tb_user (
       id integer generated by default as identity,
        password varchar(255),
        username varchar(255),
        primary key (id));
        
create table tb_user_role (
       user_id integer not null,
        role_id bigint not null,
        primary key (user_id, role_id));
        
create table tb_viagem (
       id integer generated by default as identity,
        data_entrega timestamp,
        data_retirada timestamp,
        carro_id integer,
        usuario_id integer,
        primary key (id));
        
alter table tb_user_role 
       add constraint fk_tb_role_id
       foreign key (role_id) 
       references tb_role;
       
alter table tb_user_role 
       add constraint fk_tb_user_id
       foreign key (user_id) 
       references tb_user;
       
alter table tb_viagem 
       add constraint fk_tb_carro_id 
       foreign key (carro_id) 
       references tb_carro;
       
alter table tb_viagem 
       add constraint fk_tb_funcionario_id
       foreign key (usuario_id) 
       references tb_funcionario;
        
