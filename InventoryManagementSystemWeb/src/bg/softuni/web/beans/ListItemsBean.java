package bg.softuni.web.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "listItemsBean")
@ViewScoped
public class ListItemsBean {

	@ManagedProperty("#{itemsBean}")
	private ItemsBean itemsBean;

	@PostConstruct
	public void init() {
	}

	public String editAction() {
		return "/page/editItem";
	}

	public String createAction() {
		return "/page/createItem";
	}

	public ItemsBean getItemsBean() {
		return itemsBean;
	}

	public void setItemsBean(ItemsBean itemsBean) {
		this.itemsBean = itemsBean;
	}

}