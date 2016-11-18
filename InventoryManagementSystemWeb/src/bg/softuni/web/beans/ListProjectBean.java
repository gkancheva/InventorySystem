package bg.softuni.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bg.softuni.entity.ProjectModel;
import bg.softuni.service.ProjectService;

@ManagedBean(name = "listProjectsBean")
@ViewScoped
public class ListProjectBean {
	@EJB
	ProjectService projectService;
	
	@PostConstruct
	public void init(){}
	
	public String editAction() {
		return "/page/editProject";
	}

	public String createAction() {
		return "/page/createProject";
	}
	
	public String findAllItems() {
		return "/page/listItems";
	}
	
	public List<ProjectModel> findAllProjects() {
		return projectService.findAllProjects();
	}
}