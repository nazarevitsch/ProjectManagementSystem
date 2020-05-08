package com.bida.dbconection.repository;

import com.bida.dbconection.domain.Developer;
import com.bida.dbconection.domain.ProgramingLanguage;
import com.bida.dbconection.domain.SkillsLevel;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDAO {

    private ConnectionFactory connection;

    private static final String selectAllDevelopersByLanguageSkills = "" +
            "select distinct id_developer, name, age, sex, it_company_id, salary from developers\n" +
            "join developers_skills ds ON developers.id_developer = ds.developer_id\n" +
            "join skills s ON s.id_skills = ds.skill_id\n" +
            "where s.programing_language = '%s'";

    private static final String selectAllDevelopersByLevelSkills = "" +
            "select distinct id_developer, name, age, sex, it_company_id, salary from developers\n" +
            "join developers_skills ds ON developers.id_developer = ds.developer_id\n" +
            "join skills s ON s.id_skills = ds.skill_id\n" +
            "where s.level = '%s'";

    private static final String selectDevelopersByProject = "select distinct * from developers\n" +
            "join developers_projects dp on developers.id_developer = dp.developer_id\n" +
            "join projects p on dp.project_id = p.id_project\n" +
            "where p.id_project = %s";

    private static final String selectDevelopersSalaryByIdProject = "select * from\n" +
            "(select project_id, sum(salary) as cost from developers_projects\n" +
            "join developers on developers.id_developer = developers_projects.developer_id\n" +
            "group by project_id) as foo where project_id = %s";

    private static final String insertIntoDeveloper = "insert into developers\n" +
            "(id_developer, name, age, sex, salary, it_company_id)\n" +
            "values (%s, '%s', %s, '%s', %s, %s);";

    private static final String selectAllDeveloper = "select * from developers";

    private static final String deleteDeveloperFromDB = "delete from developers where id_developer = %s";

    private static final String updateDeveloper = "update developers\n" +
            "set name = '%s', age = %s, sex = '%s', it_company_id = %s, salary = %s\n" +
            "where id_developer = %s";

    public List<Developer> findAllDevelopersByProgramingLanguage(ProgramingLanguage programingLanguage) {
        List<Developer> developers = new ArrayList<Developer>();
        try {
            connection = ConnectionFactory.createConnection(MainDataForDataBaseConnection.URL, MainDataForDataBaseConnection.USERNAME, MainDataForDataBaseConnection.PASSWORD);
            ResultSet resultSet = connection.getStatement().executeQuery(String.format(selectAllDevelopersByLanguageSkills, programingLanguage.getName()));
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_developer");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                Long itCompanyId = resultSet.getLong("it_company_id");
                BigDecimal salary = resultSet.getBigDecimal("salary");
                developers.add(new Developer(id, name, age, sex, itCompanyId, salary));
            }
            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error with find all developers by programing language!");
        }
        return developers;
    }

    public List<Developer> findAllDevelopersBySkillsLevel(SkillsLevel skillsLevel) {
        List<Developer> developers = new ArrayList<Developer>();
        try {
            connection = ConnectionFactory.createConnection(MainDataForDataBaseConnection.URL, MainDataForDataBaseConnection.USERNAME, MainDataForDataBaseConnection.PASSWORD);
            ResultSet resultSet = connection.getStatement().executeQuery(String.format(selectAllDevelopersByLevelSkills, skillsLevel.name()));
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_developer");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                Long itCompanyId = resultSet.getLong("it_company_id");
                BigDecimal salary = resultSet.getBigDecimal("salary");
                developers.add(new Developer(id, name, age, sex, itCompanyId, salary));
            }
            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error with find developers by skills level");
        }
        return developers;
    }

    public List<Developer> findAllDevelopersByProjectId(long projectId) {
        List<Developer> developers = new ArrayList<Developer>();
        try {
            connection = ConnectionFactory.createConnection(MainDataForDataBaseConnection.URL, MainDataForDataBaseConnection.USERNAME, MainDataForDataBaseConnection.PASSWORD);
            ResultSet resultSet = connection.getStatement().executeQuery(String.format(selectDevelopersByProject, projectId));
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_developer");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                Long itCompanyId = resultSet.getLong("it_company_id");
                BigDecimal salary = resultSet.getBigDecimal("salary");
                developers.add(new Developer(id, name, age, sex, itCompanyId, salary));
            }
            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error with select developers by project ID");
        }
        return developers;
    }

    public Integer findDevelopersSalaryByIdProject(long projectId){
        Integer salarySum = null;
        try {
            connection = ConnectionFactory.createConnection(MainDataForDataBaseConnection.URL, MainDataForDataBaseConnection.USERNAME, MainDataForDataBaseConnection.PASSWORD);
            ResultSet resultSet = connection.getStatement().executeQuery(String.format(selectDevelopersSalaryByIdProject, projectId));
            while (resultSet.next()) {
                salarySum = resultSet.getInt("cost");
            }
            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error with find salary of developers by project ID");
        }
        return salarySum;
    }

    public void addDeveloper(Developer developer){
        try {
            connection = ConnectionFactory.createConnection(MainDataForDataBaseConnection.URL, MainDataForDataBaseConnection.USERNAME, MainDataForDataBaseConnection.PASSWORD);
            connection.getStatement().execute(String.format(insertIntoDeveloper, developer.getId(),
                    developer.getName(), developer.getAge(), developer.getSex(), developer.getSalary(), developer.getItCompanyId()));
            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error with add developer");
        }
    }

    public List<Developer> findAllDevelopers(){
        List<Developer> developers = new ArrayList<Developer>();
        try {
            connection = ConnectionFactory.createConnection(MainDataForDataBaseConnection.URL, MainDataForDataBaseConnection.USERNAME, MainDataForDataBaseConnection.PASSWORD);
            ResultSet resultSet = connection.getStatement().executeQuery(selectAllDeveloper);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_developer");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                Long itCompanyId = resultSet.getLong("it_company_id");
                BigDecimal salary = resultSet.getBigDecimal("salary");
                developers.add(new Developer(id, name, age, sex, itCompanyId, salary));
            }
            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error with select all developers");
        }
        return developers;
    }

    public void deleteDeveloper(Long developerId){
        try {
            connection = ConnectionFactory.createConnection(MainDataForDataBaseConnection.URL, MainDataForDataBaseConnection.USERNAME, MainDataForDataBaseConnection.PASSWORD);
             connection.getStatement().execute(String.format(deleteDeveloperFromDB, developerId));
            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error with delete developer!");
        }
    }

    public void updateDeveloper(Developer developer){
        try {
            connection = ConnectionFactory.createConnection(MainDataForDataBaseConnection.URL, MainDataForDataBaseConnection.USERNAME, MainDataForDataBaseConnection.PASSWORD);
            connection.getStatement().execute(String.format(updateDeveloper, developer.getName(), developer.getAge(), developer.getSex(),
                    developer.getItCompanyId(), developer.getSalary(), developer.getId()));
            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error with update developer!");
        }
    }
}
