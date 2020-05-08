package com.bida.dbconection.service;

import com.bida.dbconection.domain.Developer;
import com.bida.dbconection.domain.ProgramingLanguage;
import com.bida.dbconection.domain.SkillsLevel;
import com.bida.dbconection.repository.DeveloperDAO;


import java.util.List;

public class DeveloperService {

    private static DeveloperDAO developerDAO = new DeveloperDAO();

    public List<Developer> findAllDevelopersByProgramingLanguage(ProgramingLanguage programingLanguage) {
        return developerDAO.findAllDevelopersByProgramingLanguage(programingLanguage);
    }

    public List<Developer> findAllDevelopersBySkillLevel(SkillsLevel skillLevel) {
        return developerDAO.findAllDevelopersBySkillsLevel(skillLevel);
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

    public List<Developer> findAllDevelopers(){
        return developerDAO.findAllDevelopers();
    }

    public void deleteDeveloper(Long developerId){
        developerDAO.deleteDeveloper(developerId);
    }

    public void updateDeveloper(Developer developer){
        developerDAO.updateDeveloper(developer);
    }
}
