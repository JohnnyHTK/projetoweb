create schema produtora;

create user 'user'@'localhost' identified by '123';

GRANT ALL PRIVILEGES ON * . * TO user@'localhost';

use produtora;

create table usr_usuario(
    usr_id bigint unsigned primary key auto_increment,
    usr_login varchar(50),
    usr_senha varchar(50),
    usr_auth varchar(1),
    constraint use_login_uk unique (usr_login)
);

INSERT INTO usr_usuario (usr_login, usr_senha,usr_auth)
VALUES ("admin","123","A");

INSERT INTO usr_usuario (usr_login, usr_senha,usr_auth)
VALUES ("jonathan","123","U");

INSERT INTO usr_usuario (usr_login, usr_senha,usr_auth)
VALUES ("lucas","123","U");

INSERT INTO usr_usuario (usr_login, usr_senha,usr_auth)
VALUES ("hercules","123","A");

INSERT INTO usr_usuario (usr_login, usr_senha,usr_auth)
VALUES ("victor","123","U");


create table pes_pessoa(
	pes_id bigint unsigned primary key auto_increment,
    pes_nome varchar(50),
    pes_cpf bigint,
    tipo varchar(1),
    atr_fama varchar(50),
    dub_especialidade varchar(50),
    constraint pes_cpf_uk unique (pes_cpf)
);

create table fmg_filmagem (
	fmg_id bigint unsigned primary key auto_increment,
    fmg_nome varchar(50) not null,
    fmg_ano date not null,
    fmg_duracao float not null,
    diretor bigint unsigned,
    constraint fmg_pes_fk foreign key (diretor)
		references pes_pessoa(pes_id)
);

create table nov_novela (
	fmg_id bigint unsigned,
    nov_capitulo bigint not null,
    nov_desc_cap varchar(100) not null,
    constraint nov_fmg_fk foreign key (fmg_id)
		references fmg_filmagem(fmg_id)
);

create table flm_filme (
	fmg_id bigint unsigned,
    flm_descricao varchar(100) not null,
    constraint flm_fmg_fk foreign key (fmg_id)
		references fmg_filmagem(fmg_id)
);

create table atu_atuacao (
	pes_id bigint unsigned,
    fmg_id bigint unsigned,
    constraint atu_pk primary key(pes_id, fmg_id),
    constraint atu_fmg_fk foreign key (fmg_id)
		references fmg_filmagem(fmg_id),
	constraint atu_pes_fk foreign key (pes_id)
		references pes_pessoa(pes_id)
);
    -- SINGLE TABLE
    --pes_nome, pes_cpf, tipo, atr_fama, dub_especialidade

    --Ator
    INSERT INTO pes_pessoa (pes_nome,pes_cpf,tipo,atr_fama) VALUES ("Quinn Wilder","93763878699","A","Musica"),("Dustin Bean","52440659999","A","Musica"),("Brennan Morales","09728535499","A","Musica"),("Nicholas Sims","74659153299","A","Musica"),("Hakeem Knowles","91212127699","A","Musica"),("Edward Johnson","68289740399","A","Musica"),("Howard Langley","90467712099","A","Musica"),("Leo Lamb","44681811299","A","Musica"),("Kieran Pate","83399027899","A","Musica"),("John Freeman","12594184599","A","Musica");
    INSERT INTO pes_pessoa (pes_nome,pes_cpf,tipo,atr_fama) VALUES ("Ezekiel Roman","42529622899","A","Dança"),("Ian Guzman","45984811899","A","Dança"),("Palmer Henson","34534772199","A","Dança"),("Cruz Franco","00238568899","A","Dança"),("Nathaniel Henderson","41619676399","A","Dança"),("Tate Valencia","16264101599","A","Dança"),("Ulric Salas","56330091999","A","Dança"),("Brendan Gallagher","06629287299","A","Dança"),("Carlos Frye","90895166399","A","Dança"),("Cyrus Walters","96993123799","A","Dança");
    INSERT INTO pes_pessoa (pes_nome,pes_cpf,tipo,atr_fama) VALUES ("Jin Sharpe","62675184299","A","Teatro"),("Abraham Simon","79562768299","A","Teatro"),("Thomas Wilson","28959721399","A","Teatro"),("Gabriel Fuller","03919352999","A","Teatro"),("Declan Crosby","57306068399","A","Teatro"),("Hamilton Marquez","57624627999","A","Teatro"),("Dexter Grimes","66486668599","A","Teatro"),("Baxter Mitchell","84523451199","A","Teatro"),("Ray Fisher","03434600399","A","Teatro"),("Oleg Mcgee","59272003999","A","Teatro");

    --Duble
    INSERT INTO pes_pessoa (pes_nome,pes_cpf,tipo,dub_especialidade) VALUES ("Craig Allison","83567987299","D","Defesa Pessoal"),("Kennan Rosales","53686759399","D","Defesa Pessoal"),("Stewart Peck","66849958299","D","Defesa Pessoal"),("Drew Hart","63707715299","D","Defesa Pessoal"),("Kareem Conway","54561407299","D","Defesa Pessoal"),("Salvador Kane","93671361599","D","Defesa Pessoal"),("Paki Strickland","90782791999","D","Defesa Pessoal"),("Philip Lewis","35282487699","D","Defesa Pessoal"),("Odysseus Meyers","58411891599","D","Defesa Pessoal"),("Tarik Love","25994182299","D","Defesa Pessoal");
    INSERT INTO pes_pessoa (pes_nome,pes_cpf,tipo,dub_especialidade) VALUES ("Christian Miranda","02212491399","D","Encenação"),("Sean Lowery","25205926599","D","Encenação"),("Grant Potter","38353503599","D","Encenação"),("Beau Daugherty","80977316399","D","Encenação"),("Gabriel Long","37841565099","D","Encenação"),("Brock Dejesus","50722039299","D","Encenação"),("Brian Clayton","06515319499","D","Encenação"),("Dexter Horn","79964562799","D","Encenação"),("Brett Cardenas","04494598399","D","Encenação"),("Jeremy Taylor","20595893199","D","Encenação");
    INSERT INTO pes_pessoa (pes_nome,pes_cpf,tipo,dub_especialidade) VALUES ("Dolan Barlow","95632595899","D","Direção Automobilistica"),("Benjamin Day","20948806099","D","Direção Automobilistica"),("Yoshio Macias","42856869799","D","Direção Automobilistica"),("Rajah Buckner","44706482799","D","Direção Automobilistica"),("Gray Cook","49988920699","D","Direção Automobilistica"),("Stephen Cooper","73606834399","D","Direção Automobilistica"),("Wylie Rogers","52942429199","D","Direção Automobilistica"),("Marvin Puckett","01971010399","D","Direção Automobilistica"),("Talon Pitts","20836275699","D","Direção Automobilistica"),("Bert Glass","67824655399","D","Direção Automobilistica");

    --Diretor
    INSERT INTO pes_pessoa (pes_nome,pes_cpf,tipo) VALUES ("Aaron Mckee","04716449699","T"),("Brock Palmer","26360791699","T"),("Robert Strong","69216890999","T"),("Hu West","13543007599","T"),("Brennan Thomas","36875847199","T"),("Ishmael Mathis","14668245099","T"),("Benjamin Barrett","68952508299","T"),("Trevor Bowen","69449000499","T"),("Alexander Benson","76680205799","T"),("Clarke Barnes","67228286799","T");

    --fmg_filmagem
    INSERT INTO fmg_filmagem (fmg_nome,fmg_ano,fmg_duracao,diretor) VALUES ("convallis convallis dolor. Quisque","23/06/2020",4,"Aaron Mckee"),("aliquam iaculis, lacus pede","21/11/2019",3,"Aaron Mckee"),("gravida molestie arcu. Sed","04/02/2020",4,"Aaron Mckee"),("Nam porttitor scelerisque neque.","22/08/2021",2,"Aaron Mckee"),("Nam ac nulla. In","10/09/2019",4,"Aaron Mckee"),("tincidunt. Donec vitae erat","14/02/2020",1,"Aaron Mckee"),("at sem molestie sodales.","15/07/2020",4,"Aaron Mckee"),("orci lacus vestibulum lorem,","16/05/2020",1,"Aaron Mckee"),("velit. Aliquam nisl. Nulla","16/03/2020",3,"Aaron Mckee"),("Mauris blandit enim consequat","27/12/2019",3,"Aaron Mckee");
    INSERT INTO fmg_filmagem (fmg_nome,fmg_ano,fmg_duracao,diretor) VALUES ("non enim commodo hendrerit.","17/03/2021",4,"Brock Palmer"),("sapien imperdiet ornare. In","27/04/2021",3,"Brock Palmer"),("eu odio tristique pharetra.","21/03/2021",4,"Brock Palmer"),("non arcu. Vivamus sit","09/08/2020",1,"Brock Palmer"),("volutpat. Nulla dignissim. Maecenas","20/12/2020",2,"Brock Palmer"),("nec urna et arcu","24/09/2019",1,"Brock Palmer"),("amet ultricies sem magna","12/10/2020",1,"Brock Palmer"),("placerat velit. Quisque varius.","09/08/2021",1,"Brock Palmer"),("dolor quam, elementum at,","23/10/2019",1,"Brock Palmer"),("Morbi non sapien molestie","27/08/2021",2,"Brock Palmer");
    INSERT INTO fmg_filmagem (fmg_nome,fmg_ano,fmg_duracao,diretor) VALUES ("pharetra, felis eget varius","09/10/2019",1,"Robert Strong"),("Quisque libero lacus, varius","22/04/2021",1,"Robert Strong"),("ac sem ut dolor","13/04/2020",4,"Robert Strong"),("ac facilisis facilisis, magna","14/06/2020",4,"Robert Strong"),("non magna. Nam ligula","13/03/2021",2,"Robert Strong"),("placerat, orci lacus vestibulum","30/11/2019",3,"Robert Strong"),("sem molestie sodales. Mauris","11/05/2020",3,"Robert Strong"),("feugiat. Sed nec metus","26/02/2021",3,"Robert Strong"),("felis orci, adipiscing non,","10/09/2019",3,"Robert Strong"),("Cras dolor dolor, tempus","12/06/2021",3,"Robert Strong");
    INSERT INTO fmg_filmagem (fmg_nome,fmg_ano,fmg_duracao,diretor) VALUES ("ante bibendum ullamcorper. Duis","22/10/2019",4,"Hu West"),("lectus ante dictum mi,","02/12/2020",4,"Hu West"),("magnis dis parturient montes,","27/11/2019",1,"Hu West"),("cursus vestibulum. Mauris magna.","23/04/2020",4,"Hu West"),("Nunc quis arcu vel","04/06/2020",2,"Hu West"),("felis. Donec tempor, est","01/02/2020",3,"Hu West"),("pellentesque. Sed dictum. Proin","07/06/2021",1,"Hu West"),("dolor, nonummy ac, feugiat","09/02/2020",4,"Hu West"),("quam. Curabitur vel lectus.","27/11/2020",1,"Hu West"),("lacus. Etiam bibendum fermentum","16/10/2020",1,"Hu West");

    INSERT INTO fmg_filmagem (fmg_nome,fmg_ano,fmg_duracao,diretor) VALUES ("sapien molestie orci tincidunt","16/03/2020",2,"Brennan Thomas"),("tellus. Suspendisse sed dolor.","17/05/2021",1,"Brennan Thomas"),("arcu. Nunc mauris. Morbi","28/11/2020",1,"Brennan Thomas"),("vel turpis. Aliquam adipiscing","16/03/2020",4,"Brennan Thomas"),("ac arcu. Nunc mauris.","06/10/2019",3,"Brennan Thomas"),("ornare. In faucibus. Morbi","06/01/2021",2,"Brennan Thomas"),("lectus pede, ultrices a,","25/11/2020",1,"Brennan Thomas"),("Nam ligula elit, pretium","10/08/2021",3,"Brennan Thomas"),("hendrerit consectetuer, cursus et,","29/04/2021",3,"Brennan Thomas"),("Praesent eu dui. Cum","25/12/2019",2,"Brennan Thomas");
    INSERT INTO fmg_filmagem (fmg_nome,fmg_ano,fmg_duracao,diretor) VALUES ("ligula eu enim. Etiam","08/10/2020",1,"Brennan Thomas"),("vel, vulputate eu, odio.","19/03/2021",3,"Brennan Thomas"),("tincidunt dui augue eu","21/02/2021",4,"Brennan Thomas"),("nunc sed pede. Cum","28/02/2021",3,"Brennan Thomas"),("mauris sagittis placerat. Cras","18/10/2020",2,"Brennan Thomas"),("turpis. In condimentum. Donec","27/07/2020",4,"Brennan Thomas"),("velit. Aliquam nisl. Nulla","09/01/2020",3,"Brennan Thomas"),("In nec orci. Donec","17/02/2021",2,"Brennan Thomas"),("Maecenas mi felis, adipiscing","31/10/2020",3,"Brennan Thomas"),("Sed pharetra, felis eget","10/10/2019",2,"Brennan Thomas");

