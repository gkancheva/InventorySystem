package bg.softuni.web.beans.item;

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
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "editItemBean")
@ViewScoped
public class EditDeleteItemBean {

	@Inject
	HttpServletRequest request;

	@EJB
	ItemService itemService;
	
	private ItemModel item;
	
	@PostConstruct
	public void init() {
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			item = itemService.findById(Long.valueOf(id));
		}
	}
	
	public String updateAction() {
		if(validateInput()) {
			return null;
		}
		itemService.update(item);
		return "/page/listProjects?faces-redirect=true";
	}
	
	public String deleteAction() {
		itemService.delete(item);
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