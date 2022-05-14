drop database if exists db_tcc_ds3t;
create database db_tcc_ds3t;

use db_tcc_ds3t;

create table tbl_genero(
	id_genero int not null auto_increment primary key,
    genero text not null
); 

create table tbl_candidato(
	id_candidato int not null auto_increment primary key,
    nome text not null,
    senha text not null,
    foto_perfil text,
	banner text,
    curriculo text,
    data_nascimento text,
    nome_social text,
    informacoes text,
    id_genero int not null,
    constraint fk_genero_candidato
    foreign key(id_genero)
    references tbl_genero(id_genero)
);

create table tbl_telefone_candidato(
	id_telefone_candidato int not null auto_increment primary key,
    numero text not null,
    candidato_id_candidato int not null,
    constraint fk_candidato_telefone_candidato
    foreign key(candidato_id_candidato)
    references tbl_candidato(id_candidato)
);

create table tbl_email_candidato(
	id_email_candidato int not null auto_increment primary key,
    email text not null,
    candidato_id_candidato int not null,
    constraint fk_candidato_email_candidato
    foreign key(candidato_id_candidato)
    references tbl_candidato(id_candidato)
);

create table tbl_experiencia_profissional(
	id_experiencia_profissional int not null auto_increment primary key,
    cargo text not null,
    data_inicio text not null,
    data_saida text,
    atribuicoes text not null,
    nome_empresa text not null,
    candidato_id_candidato int not null,
    constraint fk_candidato_experiencia_profissional
    foreign key(candidato_id_candidato)
    references tbl_candidato(id_candidato)
);

create table tbl_tipo_deficiencia(
	id_tipo_deficiencia int not null auto_increment primary key,
    tipo text not null
);

create table tbl_deficiencia(
	id_deficiencia int not null auto_increment primary key,
    deficiencia text not null,
    tipo_deficiencia_id_tipo_deficiencia int not null,
    constraint fk_tipo_deficiencia_deficiencia
    foreign key(tipo_deficiencia_id_tipo_deficiencia)
    references tbl_tipo_deficiencia(id_tipo_deficiencia)
);

create table tbl_deficiencia_candidato(
	id_deficiencia_candidato int not null auto_increment primary key,
    deficiencia_id_deficiencia int not null,
    candidato_id_candidato int not null,
    constraint fk_candidato_deficiencia_candidato
    foreign key(candidato_id_candidato)
    references tbl_candidato(id_candidato),
    constraint fk_deficiencia_deficiencia_candidato
    foreign key(deficiencia_id_deficiencia)
    references tbl_deficiencia(id_deficiencia)
);

create table tbl_estado(
	id_estado int not null auto_increment primary key,
    estado text not null,
    sigla text not null
);

create table tbl_cidade(
	id_cidade int not null auto_increment primary key,
    cidade text not null,
    estado_id_estado int not null,
    constraint fk_estado_cidade
    foreign key(estado_id_estado)
    references tbl_estado(id_estado)
);

create table tbl_endereco_candidato(
	id_endereco_candidato int not null auto_increment primary key,
    numero text,
    rua text not null,
    cep text not null,
    bairro text not null,
    cidade_id_cidade int not null,
    candidato_id_candidato int not null,
    constraint fk_candidato_endereco_candidato
    foreign key(candidato_id_candidato)
    references tbl_candidato(id_candidato),
    constraint fk_cidade_endereco_candidato
    foreign key(cidade_id_cidade)
    references tbl_cidade(id_cidade)
);

create table tbl_nivel(
	id_nivel int not null auto_increment primary key,
    nivel text not null
);

create table tbl_area_atuacao(
	id_area_atuacao int not null auto_increment primary key,
    area_atuacao text not null
);

create table tbl_curso(
	id_curso int not null auto_increment primary key,
    curso text not null,
    area_atuacao_id_area_atuacao int not null,
    nivel_id_nivel int not null,
	constraint fk_area_atuacao_curso
    foreign key(area_atuacao_id_area_atuacao)
    references tbl_area_atuacao(id_area_atuacao),
	constraint fk_nivel_curso
    foreign key(nivel_id_nivel)
    references tbl_nivel(id_nivel)
);

create table tbl_curso_candidato(
	id_curso_candidato int not null auto_increment primary key,
    candidato_id_candidato int not null,
    curso_id_curso int not null,
    constraint fk_candidato_curso_candidato
    foreign key(candidato_id_candidato)
    references tbl_candidato(id_candidato),
    constraint fk_curso_curso_candidato
    foreign key(curso_id_curso)
    references tbl_curso(id_curso)
);

create table tbl_empresa(
	id_empresa int not null auto_increment primary key,
    nome text not null,
    senha text not null,
    descricao text,
    foto_empresa text,
    banner text,
    area_atuacao_id_area_atuacao int not null,
    constraint fk_area_atuacao_empresa
    foreign key(area_atuacao_id_area_atuacao)
    references tbl_area_atuacao(id_area_atuacao)
);

create table tbl_email_empresa(
	id_email_empresa int not null auto_increment primary key,
    email text not null,
    empresa_id_empresa int not null,
    constraint fk_empresa_email_empresa
    foreign key(empresa_id_empresa)
    references tbl_empresa(id_empresa)
);

create table tbl_endereco_empresa(
	id_endereco_empresa int not null auto_increment primary key,
    numero text,
    rua text not null,
    cep text not null,
    bairro text not null,
    cidade_id_cidade int not null,
    empresa_id_empresa int not null,
    constraint fk_empresa_endereco_empresa
    foreign key(empresa_id_empresa)
    references tbl_empresa(id_empresa),
    constraint fk_cidade_endereco_empresa
    foreign key(cidade_id_cidade)
    references tbl_cidade(id_cidade)
);

create table tbl_telefone_empresa(
	id_telefone_empresa int not null auto_increment primary key,
    numero text not null,
    empresa_id_empresa int not null,
    constraint fk_empresa_telefone_empresa
    foreign key(empresa_id_empresa)
    references tbl_empresa(id_empresa)
);

create table tbl_chat_empresa(
	id_chat_empresa int not null auto_increment primary key,
    mensagem text not null,
    horario_mensagens datetime,
    id_empresa int not null,
    constraint fk_empresa_chat_empresa
    foreign key(id_empresa)
    references tbl_empresa(id_empresa)
);

create table tbl_chat_candidato(
	id_chat_candidato int not null auto_increment primary key,
    mensagem text not null,
    horario_mensagens datetime,
    id_candidato int not null,
    constraint fk_candidato_chat_candidato
    foreign key(id_candidato)
    references tbl_candidato(id_candidato)
);

create table tbl_chat(
	id_chat int not null auto_increment primary key,
    id_chat_empresa int not null,
    id_chat_candidato int not null,
	constraint fk_chat_empresa_chat
    foreign key(id_chat_empresa)
    references tbl_chat_empresa(id_chat_empresa),
	constraint fk_chat_candidato_chat
    foreign key(id_chat_candidato)
    references tbl_chat_candidato(id_chat_candidato)
);

create table tbl_local_trabalho(
	id_local_trabalho int not null auto_increment primary key,
    numero text,
    rua text not null,
    cep text not null,
    bairro text not null,
    cidade_id_cidade int not null,
    constraint fk_cidade_local_trabalho
    foreign key(cidade_id_cidade)
    references tbl_cidade(id_cidade)
);

create table tbl_suporte_pcd(
	id_suporte_pcd int not null auto_increment primary key,
    suporte text not null
);

create table tbl_horario(
	id_horario int not null auto_increment primary key,
    horario_inicio text not null,
    horario_saida text not null,
    visivel boolean
);

create table tbl_tipo_contrato(
	id_tipo_contrato int not null auto_increment primary key,
    tipo_contrato text not null
);

create table tbl_salario(
	id_salario int not null auto_increment primary key,
    salario text,
    visivel boolean
);

create table tbl_beneficio(
	id_beneficio int not null auto_increment primary key,
    beneficio text not null
);

create table tbl_vaga(
	id_vaga int not null auto_increment primary key,
    titulo text not null,
    status boolean not null,
    descricao text,
    requisitos text,
    horario_id_horario int not null,
    local_trabalho_id_local_trabalho int not null,
    tipo_contrato_id_tipo_contrato int not null,
    empresa_id_empresa int not null,
    salario_id_salario int not null,
    constraint fk_salario_salario_vaga
    foreign key(salario_id_salario)
    references tbl_salario(id_salario),
    constraint fk_local_trabalho_vaga
    foreign key(local_trabalho_id_local_trabalho)
    references tbl_local_trabalho(id_local_trabalho),
    constraint fk_horario_vaga
    foreign key(horario_id_horario)
    references tbl_horario(id_horario),
    constraint fk_tipo_contrato_vaga
    foreign key(tipo_contrato_id_tipo_contrato)
    references tbl_tipo_contrato(id_tipo_contrato),
    constraint fk_empresa_vaga
    foreign key(empresa_id_empresa)
    references tbl_empresa(id_empresa)
);

create table tbl_beneficio_vaga(
	id_beneficio_vaga int not null auto_increment primary key,
    beneficio_id_beneficio int not null,
    vaga_id_vaga int not null,
    foreign key(beneficio_id_beneficio)
    references tbl_beneficio(id_beneficio),
    foreign key(vaga_id_vaga)
    references tbl_vaga(id_vaga)
);

create table tbl_vaga_suporte_pcd(
	id_vaga_suporte_pcd int not null auto_increment primary key,
    suporte_id_suporte_pcd int not null,
    vaga_id_vaga int not null,
    foreign key(suporte_id_suporte_pcd)
    references tbl_suporte_pcd(id_suporte_pcd),
    foreign key(vaga_id_vaga)
    references tbl_vaga(id_vaga)
);

create table tbl_vaga_deficiencia(
	id_vaga_deficiencia int not null auto_increment primary key,
    deficiencia_id_deficiencia int not null,
    vaga_id_vaga int not null,
    foreign key(deficiencia_id_deficiencia)
    references tbl_deficiencia(id_deficiencia),
    foreign key(vaga_id_vaga)
    references tbl_vaga(id_vaga)
);

create table tbl_formacao_desejada(
	id_formacao_desejada int not null auto_increment primary key,
    curso_id_curso int not null,
    vaga_id_vaga int not null,
    foreign key(curso_id_curso)
    references tbl_curso(id_curso),
    foreign key(vaga_id_vaga)
    references tbl_vaga(id_vaga)
);

create table tbl_status_vaga(
	id_status_vaga int not null auto_increment primary key,
    status text not null
);

create table tbl_vaga_candidato(
	id_vaga_candidato int not null auto_increment primary key,
    status_id_status_vaga int not null,
    candidato_id_candidato int not null,
    vaga_id_vaga int not null,
    foreign key(candidato_id_candidato)
    references tbl_candidato(id_candidato),
    foreign key(vaga_id_vaga)
    references tbl_vaga(id_vaga),
	foreign key(status_id_status_vaga)
    references tbl_status_vaga(id_status_vaga)
);

create table tbl_recuperar_candidato(
	id_recuperar_candidato int not null auto_increment primary key,
    codigo int not null,
    email_id_email_candidato int not null,
    foreign key(email_id_email_candidato)
    references tbl_email_candidato(id_email_candidato)
);

create table tbl_recuperar_empresa(
	id_recuperar_empresa int not null auto_increment primary key,
    codigo int not null,
    email_id_email_empresa int not null,
    foreign key(email_id_email_empresa)
    references tbl_email_empresa(id_email_empresa)
);