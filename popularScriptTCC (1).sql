use db_tcc_ds3t;

insert into tbl_genero(genero) values("PREFIRO_NAO_INFORMAR"), ("MASCULINO"), ("FEMININO"), ("NAO_BINARIO");

insert into tbl_tipo_deficiencia(tipo) values("AUDITIVA"), ("VISUAL"), ("MOTORA"), ("INTELECTUAL");

insert into tbl_deficiencia(id_tipo_deficiencia, deficiencia) values(1, "SURDEZ TOTAL"), (1, "SURDEZ PARCIAL"),
																	(2, "CEGUEIRA"), (2, "DALTONISMO"), 
                                                                    (3, "MONOPLEGIA"), (3, "HAMIPLEGIA"), (3, "PARAPLEGIA"), (3, "TETRAPLEGIA"), (3, "AMPUTAÇÃO"),
                                                                    (4, "SÍNDROME DE DOWN"), (4, "AUTISMO"), (4, "SÍNFROME DE ASPERGER"), (4, "TDAH");
                                  
select * from tbl_deficiencia_candidato;
