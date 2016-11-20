package bg.softuni.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.entity.ProjectModel;
import bg.softuni.service.ProjectService;

@ManagedBean(name = "listProjectsBean")
@ViewScoped
public class ListProjectBean {
	
	@Inject
	HttpServletRequest request;
	
	@EJB
	ProjectService projectService;
	
	private Long userId;
	
	@PostConstruct
	public void init(){
		String id = request.getParameter("userId");
		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			userId = Long.valueOf(id);
		}
	}
	
	public String editAction() {
		return "/page/editProject";
	}

	public String createAction() {
		return "/page/createProject";
	}
	
	public String findAllItems() {
		return "/page/listItems";
	}
	
	public List<ProjectModel> findAllProjects(Long id) {
		return projectService.findAllProjectsOfSpecUser(id);
	}
}