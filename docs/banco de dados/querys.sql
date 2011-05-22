SELECT
     MONTHNAME(p.momentoRegistroPresenca) AS month,
     count(*) as presencaMensal,
     c.nome,
     o.nome,
     p.dataPresenca,
     c.nome
FROM
     obreiro o,
     congregacao c,
     presencaobreiro p
where
     o.congregacao_fk = c.idCongregacao AND
     o.cpf = p.obreiro_cpf AND
     YEAR(p.momentoRegistroPresenca) = 2011 AND
     p.obreiroPresente = true
     group by month, c.nome;
	 
	 
	 
	insert into reuniao (data, horario, local, reuniaoStatus) values('04/20/2011', '00:30:02', 'Rua da Esquina', 'INATIVA');
	insert into presencaobreiro(dataPresenca, momentoPresenca, obreiroPresente, obreiro_cpf) values ('20/05/2011', '00:31:16', false, '29675850507');
	
	
	
	insert into presencaobreiro(obreiroPresente, obreiro_cpf) values (false, '29675850507');
	insert into presencaobreiro(obreiroPresente, obreiro_cpf) values (false, '29802758400');
	insert into presencaobreiro(obreiroPresente, obreiro_cpf) values (false, '31273800893');
	insert into presencaobreiro(obreiroPresente, obreiro_cpf) values (false, '35615252880');
	insert into presencaobreiro(obreiroPresente, obreiro_cpf) values (false, '45261946972');
	insert into presencaobreiro(obreiroPresente, obreiro_cpf) values (false, '51254418563');
	
	
	update presencaobreiro set dataPresenca='04/20/2011', momentoPresenca='00:31:02', obreiroPresente=true, momentoRegistroPresenca=1303270322;
	
	
	update presencaobreiro
  set
    dataPresenca='04/20/2011',
    momentoPresenca='00:31:16',
    obreiroPresente=true,
    momentoRegistroPresenca=FROM_UNIXTIME(1303270276)
  where
    idPresencaObreiro=13;
	
	update presencaobreiro
  set
    dataPresenca='04/20/2011',
    momentoPresenca='00:35:00',
    obreiroPresente=true,
    momentoRegistroPresenca=FROM_UNIXTIME(1303274100)
  where
    idPresencaObreiro=14;
	
	update presencaobreiro
  set
    dataPresenca='04/20/2011',
    momentoPresenca='00:31:02',
    obreiroPresente=true,
    momentoRegistroPresenca=FROM_UNIXTIME(1303270322)
  where
    idPresencaObreiro=15;
	
	
	update presencaobreiro
  set
    dataPresenca='04/20/2011',
    momentoPresenca='00:33:02',
    obreiroPresente=true,
    momentoRegistroPresenca=FROM_UNIXTIME(1303270382)
  where
    idPresencaObreiro=17;
	
	insert into reuniao_presencaobreiro (Reuniao_idReuniao, listaDePresencaObreiro_IdPresencaObreiro) values (3, 13);
	insert into reuniao_presencaobreiro (Reuniao_idReuniao, listaDePresencaObreiro_IdPresencaObreiro) values (3, 14);
	insert into reuniao_presencaobreiro (Reuniao_idReuniao, listaDePresencaObreiro_IdPresencaObreiro) values (3, 15);
	insert into reuniao_presencaobreiro (Reuniao_idReuniao, listaDePresencaObreiro_IdPresencaObreiro) values (3, 16);
	insert into reuniao_presencaobreiro (Reuniao_idReuniao, listaDePresencaObreiro_IdPresencaObreiro) values (3, 17);
	
	insert into reuniaosessionstatus(SESSION_END_TIME, SESSION_START_TIME, sessaoAtiva) values(FROM_UNIXTIME(1303273860), FROM_UNIXTIME(1303270260), false);
	
	update reuniao set sessionStatus_idReuniaoSessionStatus=3 where idReuniao=3;