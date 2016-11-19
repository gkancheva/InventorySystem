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

import bg.softuni.entity.ItemModel;
import bg.softuni.service.ItemService;
import bg.softuni.service.ProjectService;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "createItemBean")
@ViewScoped
public class CreateItemBean {
	
	@Inject
	HttpServletRequest request;

	@EJB
	ItemService itemService;
	
	@EJB
	ProjectService projectService;
	
	private ItemModel item;

	@PostConstruct
	public void init() {
		item = new ItemModel();
		String id = request.getParameter("projectId");
		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			item.setProject(projectService.findById(Long.valueOf(id)));
		}
	}
	
	public String createAction() {
		if(!validateInput()) {
			return null;
		}
		itemService.save(item);
		return "/page/listProjects?faces-redirect=true";
	}
	
	public ItemModel getItem() {
		return item;
	}
	
	public void setItem(ItemModel item) {
		this.item = item;
	}
	
	protected boolean validateInput() {
		boolean hasErrors = false;
		if (StringUtils.isBlank(item.getName())) {
			MessageUtils.addErrorMessage("error.required.item.name");
			hasErrors = true;
		}
		if (StringUtils.isBlank(item.getDescription())) {
			MessageUtils.addErrorMessage("error.required.item.description");
			hasErrors = true;
		}
		if (StringUtils.isBlank(item.getTypeOfProduct())) {
			MessageUtils.addErrorMessage("error.required.item.typeOfProduct");
			hasErrors = true;
		}
		
		if (StringUtils.isBlank(item.getCatalogNb())) {
			MessageUtils.addErrorMessage("error.required.item.catalogNb");
			hasErrors = true;
		}
		if (StringUtils.isBlank(String.valueOf(item.getQuantity()))) {
			MessageUtils.addErrorMessage("error.required.item.quantity");
			hasErrors = true;
		}
		
		if (StringUtils.isBlank(item.getSupplier())) {
			MessageUtils.addErrorMessage("error.required.item.supplier");
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
