package org.tux2586.noticeBoard.DAO;

import org.bson.Document;
import org.slf4j.LoggerFactory;
import org.tux2586.noticeBoard.Exceptions.UserNotFoundException;
import org.tux2586.noticeBoard.beans.User;
import org.tux2586.noticeBoard.controllers.BaseController;
import org.tux2586.noticeBoard.data.MongoConnection;
import org.tux2586.noticeBoard.interfaces.UserDAOIntf;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Filters.*;

public class UserDAOImpl implements UserDAOIntf {
	
	private static final String LOG_PREFIX = "********************";
	private MongoConnection conn = new MongoConnection();
	private MongoCollection collection;
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	private static final String COLLECTION_NAME  = "users";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "email";
	private static final String CONTACT_NO = "contactNo";
	private static final String USER_NAME = "userName";
	private static final String PASSWORD = "passwordHash";
	

	public void addUser(User user){
		MongoConnection conn = new MongoConnection();
		conn.init();
		collection = conn.getCollection(COLLECTION_NAME);
		Document usr = new Document();
		usr.append("_id", user.getuName())
			.append(FIRST_NAME, user.getfName())
			.append(LAST_NAME, user.getlName())
			.append(USER_NAME, user.getuName())
			.append(EMAIL,user.getEmail())
			.append(CONTACT_NO, user.getContactNo())
			.append(PASSWORD,user.getPassword());
		
		collection.insertOne(usr);
		conn.close();
	}
	public boolean validate(User user) {
		conn.init();
		Document usr = conn.getDocument(user.getuName(),COLLECTION_NAME);
		conn.close();
		
		if(usr == null){
			return false;
		}
		
		boolean isValid = user.getPassword().equals(usr.get(PASSWORD));

		if(isValid){
			user.setfName(usr.getString(FIRST_NAME));
			user.setlName(usr.getString(LAST_NAME));
			user.setEmail(usr.getString(EMAIL));
			user.setContactNo(usr.getString(CONTACT_NO));
		}
		return isValid;
	}
	public void updateUser(User user) throws UserNotFoundException {
		conn.init();
		collection = conn.getCollection(COLLECTION_NAME);
		UpdateResult result = collection.updateOne(eq("_id",user.getuName()), combine(set(FIRST_NAME, user.getfName()),
																	 set(LAST_NAME, user.getlName()),
																	 set(EMAIL, user.getEmail()),
																	 set(CONTACT_NO, user.getContactNo())));
		
		if(result.getMatchedCount() == 0){
			throw new UserNotFoundException("User " + user.getuName() + " not found.");
		}
	}

	public User getUser(String userName, User user) {
		conn.init();
		collection = conn.getCollection(COLLECTION_NAME);
		Document usr = (Document) collection.find(new Document("_id",userName)).first();
		user.setuName(userName);
		user.setEmail(usr.getString(EMAIL));
		user.setfName(usr.getString(FIRST_NAME));
		user.setlName(usr.getString(LAST_NAME));
		user.setContactNo(usr.getString(CONTACT_NO));
		
		conn.close();
		return user;
	}

}
