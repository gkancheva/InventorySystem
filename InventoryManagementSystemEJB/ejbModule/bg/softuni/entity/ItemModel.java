package bg.softuni.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import bg.softuni.entity.base.BaseDomainObject;

@Entity
@Table(name = "ITEMS")
public class ItemModel extends BaseDomainObject{
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private String typeOfProduct;
	private String catalogNb;
	private String supplier;
	private Double purchasePrice;
	private Integer quantity;
	private ProjectModel project;

	public ItemModel() {
		super();
	}

	public ItemModel(Long id, String name, String description, String typeOfProduct, String catNb, String supplier,
			ProjectModel project, Double purchasePrice, Integer quantity) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.typeOfProduct = typeOfProduct;
		this.catalogNb = catNb;
		this.supplier = supplier;
		this.project = project;
		this.purchasePrice = purchasePrice;
		this.quantity = quantity;
	}

	@Column(name = "name", length = 200, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 400, nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "type_product", length = 200, nullable = false)
	public String getTypeOfProduct() {
		return typeOfProduct;
	}

	public void setTypeOfProduct(String typeOfProduct) {
		this.typeOfProduct = typeOfProduct;
	}

	@Column(name = "catalog_nb_sup", length = 100, nullable = false)
	public String getCatalogNb() {
		return catalogNb;
	}

	public void setCatalogNb(String catNb) {
		this.catalogNb = catNb;
	}

	@Column(name = "supplier", length = 200, nullable = false)
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	@JoinColumn(name = "project_id", referencedColumnName = "id")
	@ManyToOne
	public ProjectModel getProject() {
		return project;
	}

	public void setProject(ProjectModel project) {
		this.project = project;
	}

	@Column(name = "purchase_price", nullable = false)
	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}