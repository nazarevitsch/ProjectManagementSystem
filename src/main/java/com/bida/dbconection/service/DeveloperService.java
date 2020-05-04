package com.bida.dbconection.service;

import com.bida.dbconection.domain.Developer;
import com.bida.dbconection.repository.DeveloperDAO;


import java.util.List;

public class DeveloperService {

    private static DeveloperDAO developerDAO = new DeveloperDAO();

    public List<Developer> findAllJavaDevelopers() {
        return developerDAO.findAllJavaDevelopers();
    }

    public List<Developer> findAllMiddleDevelopers() {
        return developerDAO.findAllMiddleDevelopers();
    }

    public List<Developer> findAllDevelopersByProjectId(long projectId) {
        return developerDAO.findAllDevelopersByProjectId(projectId);
    }

    public Integer findDevelopersSalaryByIdProject(long projectId){
        return developerDAO.findDevelopersSalaryByIdProject(projectId);
    }

    public void addDeveloper(Developer developer){
        developerDAO.addDeveloper(developer);
    }

    public List<Developer> selectAllDevelopers(){
        return developerDAO.selectAllDevelopers();
    }

    public void deleteDeveloper(Long developerId){
        developerDAO.deleteDeveloper(developerId);
    }

    public void updateDeveloper(Developer developer){
        developerDAO.updateDeveloper(developer);
    }
}
