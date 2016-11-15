package bg.softuni.dto;

import java.io.Serializable;

public class ItemDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private String typeOfProduct;
	private String catalogueNumber;
	private String supplier;
	private String project;
	private Double purchasePrice;
	private Integer quantity;

	public ItemDto() {
		super();
	}

	public ItemDto(String name, String description, String typeOfProduct, String catalogueNumber, String supplier,
			String project, Double purchasePrice, Integer quantity) {
		super();
		this.name = name;
		this.description = description;
		this.typeOfProduct = typeOfProduct;
		this.catalogueNumber = catalogueNumber;
		this.supplier = supplier;
		this.project = project;
		this.purchasePrice = purchasePrice;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeOfProduct() {
		return typeOfProduct;
	}

	public void setTypeOfProduct(String typeOfProduct) {
		this.typeOfProduct = typeOfProduct;
	}

	public String getCatalogueNumber() {
		return catalogueNumber;
	}

	public void setCatalogueNumber(String catalogueNumber) {
		this.catalogueNumber = catalogueNumber;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}