package bg.softuni.service;

import java.util.List;

import javax.ejb.Local;

import bg.softuni.entity.ItemModel;

@Local
public interface ItemService {
	List<ItemModel> findAllItemsByProjectId(Long projectId);
	ItemModel save(ItemModel entity);
	ItemModel update(ItemModel entity);
	void delete (ItemModel entity);
	void deleteAllItemsOfSpecProject(Long projectId);
	ItemModel findById(Long id);
	ItemModel checkIfItemExists(String name, Long id);
}