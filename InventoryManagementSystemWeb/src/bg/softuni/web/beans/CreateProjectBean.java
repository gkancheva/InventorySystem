package bg.softuni.web.beans;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.entity.ProjectModel;
import bg.softuni.service.ProjectService;
import bg.softuni.service.UserService;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "createProjectBean")
@ViewScoped
public class CreateProjectBean {
	
	@Inject
	HttpServletRequest request;

	@EJB
	ProjectService projectService;
	
	@EJB
	UserService userService;
	
	private ProjectModel project;

	@PostConstruct
	public void init() {
		project = new ProjectModel();
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			project.setUser(userService.findById(Long.valueOf(id)));
		}
	}
	
	public String createAction() {
		if(!validateInput()) {
			return null;
		}
		projectService.save(project);
		return "/page/listProjects?faces-redirect=true";
	}
	
	public ProjectModel getProject() {
		return project;
	}
	
	public void setProject(ProjectModel project) {
		this.project = project;
	}
	
	protected boolean validateInput() {
		boolean hasErrors = false;
		if (StringUtils.isBlank(project.getName())) {
			MessageUtils.addErrorMessage("error.required.project.name");
			hasErrors = true;
		}
		if (StringUtils.isBlank(project.getCustomer())) {
			MessageUtils.addErrorMessage("error.required.project.customer");
			hasErrors = true;
		}

		if (hasErrors) {
			return false;
		}

		return true;
	}
	
	public boolean hasErrors() {
		Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages();
		for (; messages.hasNext();) {
			FacesMessage message = messages.next();
			if (message.getSeverity().compareTo(FacesMessage.SEVERITY_ERROR) == 0) {
				return true;
			}
		}
		return false;
	}
	
}
