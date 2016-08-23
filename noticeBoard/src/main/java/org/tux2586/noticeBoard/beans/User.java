package org.tux2586.noticeBoard.beans;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.tux2586.noticeBoard.DAO.UserDAOImpl;
import org.tux2586.noticeBoard.Exceptions.UserNotFoundException;
import org.tux2586.noticeBoard.data.MongoConnection;
import org.tux2586.noticeBoard.interfaces.UserDAOIntf;

import sun.misc.BASE64Encoder;

public class User {
	
	private String fName;
	private String lName;
	private String uName;
	private String password;
	private String email;
	private String contactNo;
	private ArrayList<EatRecord> eatHistory = new ArrayList<EatRecord>();
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = makePasswordHash(password, "BLOODBORNE");
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public User(){
		super();
	}
	
	public User(String userName){
		super();
		uName = userName;
		UserDAOIntf userDAO = new UserDAOImpl();
		userDAO.getUser(userName, this);
	}
	
	private String makePasswordHash(String password, String salt) {
        try {
            String saltedAndHashed = password + "," + salt;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(saltedAndHashed.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            return encoder.encode(hashedBytes) + "," + salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
        }
    }
	
	public ArrayList<EatRecord> getEatHistory() {
		return eatHistory;
	}
	
	public void save(){
		UserDAOIntf userDAO = new UserDAOImpl();
		userDAO.addUser(this);
	}
	
	public boolean loginUser(){
		UserDAOIntf userDAO = new UserDAOImpl();
		return userDAO.validate(this);
	}
	
	public void update() throws UserNotFoundException{
		UserDAOIntf userDAO = new UserDAOImpl();
		userDAO.updateUser(this);
	}
	
	public void loadEatHistory() throws UserNotFoundException{
		UserDAOIntf userDAO = new UserDAOImpl();
		userDAO.getEatHistory(this);
	}
	
}
