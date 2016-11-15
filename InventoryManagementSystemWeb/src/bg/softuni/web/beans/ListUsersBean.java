package bg.softuni.web.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "listUsersBean")
@ViewScoped
public class ListUsersBean {

	@ManagedProperty("#{usersBean}")
	private UsersBean usersBean;

	@PostConstruct
	public void init() {
	}

	public String editAction() {
		return "/page/editUser";
	}

	public String createAction() {
		return "/page/createUser";
	}

	public UsersBean getUsersBean() {
		return usersBean;
	}

	public void setUsersBean(UsersBean usersBean) {
		this.usersBean = usersBean;
	}

}