package bg.softuni.service;

import java.util.List;

import javax.ejb.Local;

import bg.softuni.entity.ProjectModel;

@Local
public interface ProjectService {
	List<ProjectModel> findAllProjects();
	List<ProjectModel> findAllProjectsOfSpecUser(Long userId);
	ProjectModel save(ProjectModel entity);
	ProjectModel update(ProjectModel entity);
	void delete(ProjectModel entity);
	ProjectModel findById(Long id);
	ProjectModel checkIfPorjectExists(String name, Long id);
}