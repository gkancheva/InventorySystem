package bg.softuni.web.beans.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import bg.softuni.entity.UserModel;
import bg.softuni.service.UserService;
import bg.softuni.web.utils.GeneralUtils;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private HttpServletRequest request;

	@EJB 
	UserService userService;
	private String username;
	private String password;

	private static final String SUCCESS_LOGIN_REDIRECT = "/page/listProjects?faces-redirect=true";
	private static final String LOGIN_PAGE_REDIRECT = "/page/login?faces-redirect=true";
	private static final String REGISTER_REDIRECT = "/page/register?faces-redirect=true";

	@PostConstruct
	public void init() {
		
	}

	public String login() {
		String encryptedPass = GeneralUtils.encodeSha256Password(password);
		UserModel userModel = userService.loginUser(username, encryptedPass);
		if (userModel == null) {
			MessageUtils.addErrorMessage("login.error.invalid.credentials");
			return "";
		} else {
			request.getSession().setAttribute("LOGGED_USER", userModel);
			return SUCCESS_LOGIN_REDIRECT;
		}
	}
	
	public String register() {
		return REGISTER_REDIRECT;
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

}