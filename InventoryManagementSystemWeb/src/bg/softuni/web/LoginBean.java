package bg.softuni.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import bg.softuni.dto.UserDto;
import bg.softuni.web.beans.UsersBean;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private HttpServletRequest request;

	private String username;
	private String password;

	@ManagedProperty("#{usersBean}")
	private UsersBean usersBean;

	private static final String SUCCESS_LOGIN_REDIRECT = "/page/listItems?faces-redirect=true";
	private static final String LOGIN_PAGE_REDIRECT = "/page/login?faces-redirect=true";

	@PostConstruct
	public void init() {
		// TODO
	}

	public String login() {
		UserDto user = usersBean.validateUser(username, password);
		if (user == null) {
			MessageUtils.addErrorMessage("login.error.invalid.credentials");
			return "";
		} else {
			request.getSession().setAttribute("LOGGED_USER", user);
			return SUCCESS_LOGIN_REDIRECT;
		}
	}

	public String logout() {
		request.getSession().invalidate();
		return LOGIN_PAGE_REDIRECT;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsersBean getUsersBean() {
		return usersBean;
	}

	public void setUsersBean(UsersBean usersBean) {
		this.usersBean = usersBean;
	}

}