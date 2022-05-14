
use db_tcc_ds3t;

insert into tbl_genero(genero) values("PREFIRO_NAO_INFORMAR"), ("MASCULINO"), ("FEMININO"), ("NAO_BINARIO");

insert into tbl_tipo_deficiencia(tipo) values("AUDITIVA"), ("VISUAL"), ("MOTORA"), ("INTELECTUAL");

insert into tbl_deficiencia(tipo_deficiencia_id_tipo_deficiencia, deficiencia) values(1, "SURDEZ TOTAL"), (1, "SURDEZ PARCIAL"),
																	(2, "CEGUEIRA"), (2, "DALTONISMO"), 
                                                                    (3, "MONOPLEGIA"), (3, "HAMIPLEGIA"), (3, "PARAPLEGIA"), (3, "TETRAPLEGIA"), (3, "AMPUTAÇÃO"),
                                                                    (4, "SÍNDROME DE DOWN"), (4, "AUTISMO"), (4, "SÍNFROME DE ASPERGER"), (4, "TDAH");
                                  
insert into tbl_area_atuacao(area_atuacao) values("CIÊNCIAS EXATAS E DA TERRA"), ("CIÊNCIAS BIOLÓGICAS"), ("ENGENHARIAS"), ("CIÊNCIAS DA SAÚDE"), ("CIÊNCIAS AGRÁRIAS"), ("CIÊNCIAS HUMANAS"), ("LINGUISTÍCAS, LETRAS E ARTES"), ("CIÊNCIAS SOCIAIS APLICADAS");

insert into tbl_nivel(nivel) values("TÉCNICO"), ("SUPERIOR"), ("MESTRADO");

insert into tbl_curso(curso, area_atuacao_id_area_atuacao, nivel_id_nivel) values ("DESENVOLVIMENTO DE SISTEMAS", 3, 1), ("ANÁLISE E DESENVOLVIMENTO DE SISTEMAS", 3, 2), ("ENGENHARIA DE SOFTWARE", 3, 2);

insert into tbl_suporte_pcd(suporte) values("ACOMPANHEMENTO"), ("CÃO-GUIA"), ("RAMPA DE ACESSO"), ("AMBIENTE CONTROLADO"), ("TRADUTOR DE LIBRAS"), ("DOCUMENTO EM BRAILLE");

insert into tbl_tipo_contrato(tipo_contrato) values("CLT"), ("ESTÁGIO"), ("PJ"), ("INFORMAL");

insert into tbl_beneficio(beneficio) values("VALE TRANSPORTE"), ("VALE REFEIÇÃO"), ("PLANO DE SAÚDE"), ("CONVÊNIO MÉDICO"), ("PLANO ODONTOLÓGICO");

insert into tbl_status_vaga(status) values("CANDIDATAR"), ("SALVAR"), ("DISPENSAR");

select * from tbl_tipo_contrato;
select * from tbl_vaga;