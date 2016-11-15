package bg.softuni.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.dto.UserDto;

@ManagedBean(name = "usersBean")
@SessionScoped
public class UsersBean {

	private List<UserDto> users;

	@PostConstruct
	public void init() {
		users = new ArrayList<UserDto>();
		users.add(new UserDto("admin", "123", "Georgi", "Novakov", "georgi@gmail.com"));
		users.add(new UserDto("a", "a", "Aaa", "Novakov", "aaa@gmail.com"));
		users.add(new UserDto("b", "b", "Bbb", "Novakov", "bbb@gmail.com"));
		users.add(new UserDto("c", "c", "Ccc", "Novakov", "ccc@gmail.com"));
		users.add(new UserDto("d", "d", "Ddd", "Novakov", "ddd@gmail.com"));
		users.add(new UserDto("e", "e", "Eee", "Novakov", "eee@gmail.com"));
	}

	public UserDto validateUser(String username, String password) {

		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return null;
		}

		for (UserDto currentUser : users) {
			if (username.equals(currentUser.getUsername()) && password.equals(currentUser.getPassword())) {
				return currentUser;
			}
		}

		return null;
	}

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}
}
