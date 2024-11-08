package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "users")
public class Users {

	@Id
	private String email;
	private String lastName;
	private String firstName;
	private String password;
	private String birthMonth;
	private String gender;
	private String profile;
	private int birthDate;
	private int birthYear;
	
	
	
	public Users( String firstName,String lastName,String email, String password,int birthDate, String birthMonth,  int birthYear, String gender,
			String profile) {
		
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
		this.birthMonth = birthMonth;
		this.gender = gender;
		this.profile = profile;
		this.birthDate = birthDate;
		this.birthYear = birthYear;
	}
	public Users() {
		
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public int getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(int birthDate) {
		this.birthDate = birthDate;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	
	
}
