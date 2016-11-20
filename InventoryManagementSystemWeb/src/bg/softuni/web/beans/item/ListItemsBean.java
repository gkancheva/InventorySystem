package bg.softuni.web.beans.item;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.entity.ItemModel;
import bg.softuni.service.ItemService;

@ManagedBean(name = "listItemsBean")
@ViewScoped
public class ListItemsBean {
	
	@Inject
	HttpServletRequest request;
	
	@EJB
	ItemService itemService;
	
	private Long projectId;
	
	@PostConstruct
	public void init(){
		String receivedId = request.getParameter("projectId");
		if(StringUtils.isNotBlank(receivedId) && StringUtils.isNumeric(receivedId)) {
			projectId = Long.valueOf(receivedId);
		}
	}
	
	public String editAction() {
		return "/page/editItem";
	}

	public String createAction() {
		return "/page/createItem";
	}
	
	public String deleteAction() {
		return "/page/deleteItem";
	}
	
	public List<ItemModel> findAllItems() {
		return itemService.findAllItemsByProjectId(projectId);
	}
	
	public Long getProjectId() {
		return projectId;
	}
}