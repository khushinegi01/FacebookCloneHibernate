package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "friends")
public class Friend {
	
	@Id
	private int fid;
	private String  sender;
	private String  receiver;
	private int stat;
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	
	
	
	
	public Friend() {
		
		
	}
	
	
	public Friend(int fid, String sender, String receiver, int stat) {
		
		this.fid = fid;
		this.sender = sender;
		this.receiver = receiver;
		this.stat = stat;
	}
	
	
}
