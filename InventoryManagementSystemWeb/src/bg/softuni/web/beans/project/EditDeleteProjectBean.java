package bg.softuni.web.beans.project;

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
import bg.softuni.service.ItemService;
import bg.softuni.service.ProjectService;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "editProjectBean")
@ViewScoped
public class EditDeleteProjectBean {

	@Inject
	HttpServletRequest request;

	@EJB
	ProjectService projectService;
	
	@EJB
	ItemService itemService;
	
	private ProjectModel project;
	
	@PostConstruct
	public void init() {
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			project = projectService.findById(Long.valueOf(id));
		}
	}
	
	public String updateAction() {
		if(validateInput()) {
			return null;
		}
		projectService.update(project);
		return "/page/listProjects?faces-redirect=true";
	}
	
	public String deleteAction() {	
		itemService.deleteAllItemsOfSpecProject(project.getId());
		projectService.delete(project);
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
		return hasErrors;
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