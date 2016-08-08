package org.tux2586.noticeBoard.interfaces;

import org.tux2586.noticeBoard.Exceptions.UserNotFoundException;
import org.tux2586.noticeBoard.beans.User;

public interface UserDAOIntf {
	public boolean validate(User user);
	public void addUser(User user);
	public void updateUser(User user) throws UserNotFoundException;
	public User getUser(String userName, User user);
}
