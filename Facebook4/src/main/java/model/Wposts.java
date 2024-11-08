package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "wposts")
public class Wposts {

	@Id
	private int wid;
	private String sender;
	private String message;
	private String dop;
	private String newPost;
	public Wposts(int wid, String sender, String message, String dop, String newPost) {
		
		this.wid = wid;
		this.sender = sender;
		this.message = message;
		this.dop = dop;
		this.newPost = newPost;
	}
	public String getNewPost() {
		return newPost;
	}
	public void setNewPost(String newPost) {
		this.newPost = newPost;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDop() {
		return dop;
	}
	public void setDop(String dop) {
		this.dop = dop;
	}
	public Wposts() {
		
	}
	
	
	
	
}
