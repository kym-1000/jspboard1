 
	member
	
	create table bm(
 	email varchar(100) not null,
    pwd varchar(100) not null,
    nick varchar(100) not null,
    reg_at datetime default current_timestamp,
    last_login datetime,
    primary key(email));
    
    board
    
    private int bindex;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	
	
	
alter table board

add constraint fk_board_writer

foreign key (writer) references member2(email)

on update cascade

on delete no action;



alter table comment

add constraint fk_comment_writer

foreign key (writer) references member2(email)

on update cascade

on delete no action;



alter table comment

add constraint fk_comment_bno

foreign key (bno) references board(bno)

on update cascade

on delete no action;

    
    