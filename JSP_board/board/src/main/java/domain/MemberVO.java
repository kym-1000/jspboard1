package domain;

public class MemberVO {
	
	
//	create table member(
//			email varchar(50),
//			pwd varchar(50),
//			nick varchar(50),
//			reg_at datetime default now(),
//			last_login datetime,
//			primary key(email));
	
	private String email;
	private String pwd;
	private String nick;
	private String reg_at;
	private String last_login;
	private String image_file;
	
	
	
	public String getImage_file() {
		return image_file;
	}

	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}

	public MemberVO() {} // 기본생성자 

	public MemberVO(String email, String pwd, String nick, String reg_at, String last_login) {
		this.email = email;
		this.pwd = pwd;
		this.nick = nick;
		this.reg_at = reg_at;
		this.last_login = last_login;
	}

	public MemberVO(String email, String pwd, String nick, String reg_at) {
		this.email = email;
		this.pwd = pwd;
		this.nick = nick;
		this.reg_at = reg_at;
	}

	public MemberVO(String email, String pwd, String nick) {
		this.email = email;
		this.pwd = pwd;
		this.nick = nick;
	}

	public MemberVO(String email, String pwd) {
		this.email = email;
		this.pwd = pwd;
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

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

	@Override
	public String toString() {
		return "MemberVO [email=" + email + ", pwd=" + pwd + ", nick=" + nick + ", reg_at=" + reg_at + ", last_login="
				+ last_login + "]";
	}
	
	
}
