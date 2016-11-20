package bg.softuni.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import bg.softuni.entity.base.BaseDomainObject;

@Entity
@Table(name = "PROJECTS")
public class ProjectModel extends BaseDomainObject {

	private static final long serialVersionUID = 1L;
	private String name;
	private UserModel user;
	private String customer;
	private List<ItemModel> items;
	
	public ProjectModel() {
		
	}
	
	public ProjectModel(Long id, String name, UserModel user, double price, double salePrice,
			String customer, List<ItemModel> items) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.customer = customer;
		this.items = items;
	}
	
	@Column(name = "name", length = 100, nullable = false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne()
	public UserModel getUser() {
		return user;
	}
	
	public void setUser(UserModel user) {
		this.user = user;
	}
	
	@Column(name = "customer", length = 100, nullable = false)
	public String getCustomer() {
		return customer;
	}
	
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.LAZY)
	public List<ItemModel> getItems() {
		return items;
	}
	
	public void setItems(List<ItemModel> items) {
		this.items = items;
	}
	

}