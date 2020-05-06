package com.bida.dbconection.service;

import com.bida.dbconection.domain.Project;
import com.bida.dbconection.repository.ProjectDAO;

import java.util.List;

public class ProjectService {
    private static ProjectDAO projectDAO = new ProjectDAO();

    public List<Project> findAllProjects(){
        return projectDAO.findAllProjects();
    }
}
