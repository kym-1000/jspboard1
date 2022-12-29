package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class memberVO {
	
//	create table bm(
//		    email varchar(100) not null,
//		    pwd varchar(100) not null,
//		    nick varchar(100) not null,
//		    reg_at datetime default current_timestamp,
//		    last_login datetime,
//		    primary key(email));
	
	private String email;
	private String pwd;
	private String nick;
	private String reg_at;
	private LocalTime last_login;
	
	public memberVO() {}

	public memberVO(String email, String pwd, String nick, String reg_at) {
		this.email = email;
		this.pwd = pwd;
		this.nick = nick;
		this.reg_at = reg_at;
	}

	public memberVO(String email, String nick, String reg_at) {
		this.email = email;
		this.nick = nick;
		this.reg_at = reg_at;
	}
	
	public memberVO(String nick, String reg_at) {
		this.nick = nick;
		this.reg_at = reg_at;
	}

	public memberVO(String reg_at) {
		this.reg_at = reg_at;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getReg_at() {
		return reg_at;
	}

	public void setReg_at(String reg_at) {
		this.reg_at = reg_at;
	}

	public LocalTime getLast_login() {
		return last_login;
	}

	public void setLast_login(LocalTime now) {
		this.last_login = now;
	}
	
	
	
	
	

	
}
