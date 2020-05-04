package com.bida.dbconection.repository;

import com.bida.dbconection.domain.Developer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDAO {

    private ConnectionFactory connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Nazar1997";

    private static final String selectJavaDevelopers = "" +
            "select distinct id_developer, name, age, sex, it_company_id, salary from developer\n" +
            "join developers_skills ds ON developer.id_developer = ds.developer_id\n" +
            "join skills s ON s.id_skills = ds.skill_id\n" +
            "where s.programing_language = 'Java'";

    private static final String selectMiddleDevelopers = "" +
            "select distinct id_developer, name, age, sex, it_company_id, salary from developer\n" +
            "join developers_skills ds ON developer.id_developer = ds.developer_id\n" +
            "join skills s ON s.id_skills = ds.skill_id\n" +
            "where s.level = 'Middle'";

    private static final String selectDevelopersByProject = "select distinct * from developer\n" +
            "join developers_projects dp on developer.id_developer = dp.developer_id\n" +
            "join projects p on dp.project_id = p.id_project\n" +
            "where p.id_project = %s";

    private static final String selectDevelopersSalaryByIdProject = "select * from\n" +
            "(select project_id, sum(salary) as cost from developers_projects\n" +
            "join developer on developer.id_developer = developers_projects.developer_id\n" +
            "group by project_id) as foo where project_id = %s";

    private static final String insertIntoDeveloper = "insert into developer\n" +
            "(id_developer, name, age, sex, salary, it_company_id)\n" +
            "values (%s, '%s', %s, '%s', %s, %s);";

    private static final String selectAllDeveloper = "select * from developer";

    private static final String deleteDeveloperFromDB = "delete from developer where id_developer = %s";

    private static final String updateDeveloper = "update developer\n" +
            "set name = '%s', age = %s, sex = '%s', it_company_id = %s, salary = %s\n" +
            "where id_developer = %s";

    public List<Developer> findAllJavaDevelopers() {
        List<Developer> developers = new ArrayList<Developer>();
        try {
            connection = ConnectionFactory.createConnection(URL, USERNAME, PASSWORD);
            ResultSet resultSet = connection.getStatement().executeQuery(selectJavaDevelopers);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_developer");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                Long itCompanyId = resultSet.getLong("it_company_id");
                Integer salary = resultSet.getInt("salary");
                developers.add(new Developer(id, name, age, sex, itCompanyId, salary));
            }
            connection.closeConnection();
        } catch (Exception e) {}
        return developers;
    }

    public List<Developer> findAllMiddleDevelopers() {
        List<Developer> developers = new ArrayList<Developer>();
        try {
            connection = ConnectionFactory.createConnection(URL, USERNAME, PASSWORD);
            ResultSet resultSet = connection.getStatement().executeQuery(selectMiddleDevelopers);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_developer");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                Long itCompanyId = resultSet.getLong("it_company_id");
                Integer salary = resultSet.getInt("salary");
                developers.add(new Developer(id, name, age, sex, itCompanyId, salary));
            }
            connection.closeConnection();
        } catch (Exception e) {}
        return developers;
    }

    public List<Developer> findAllDevelopersByProjectId(long projectId) {
        List<Developer> developers = new ArrayList<Developer>();
        try {
            connection = ConnectionFactory.createConnection(URL, USERNAME, PASSWORD);
            ResultSet resultSet = connection.getStatement().executeQuery(String.format(selectDevelopersByProject, projectId));
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_developer");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                Long itCompanyId = resultSet.getLong("it_company_id");
                Integer salary = resultSet.getInt("salary");
                developers.add(new Developer(id, name, age, sex, itCompanyId, salary));
            }
            connection.closeConnection();
        } catch (Exception e) {}
        return developers;
    }

    public Integer findDevelopersSalaryByIdProject(long projectId){
        Integer salarySum = null;
        try {
            connection = ConnectionFactory.createConnection(URL, USERNAME, PASSWORD);
            ResultSet resultSet = connection.getStatement().executeQuery(String.format(selectDevelopersSalaryByIdProject, projectId));
            while (resultSet.next()) {
                salarySum = resultSet.getInt("cost");
            }
            connection.closeConnection();
        } catch (Exception e) {}
        return salarySum;
    }

    public void addDeveloper(Developer developer){
        try {
            connection = ConnectionFactory.createConnection(URL, USERNAME, PASSWORD);
            connection.getStatement().execute(String.format(insertIntoDeveloper, developer.getId(),
                    developer.getName(), developer.getAge(), developer.getSex(), developer.getSalary(), developer.getItCompanyId()));
            connection.closeConnection();
        } catch (Exception e) {}
    }

    public List<Developer> selectAllDevelopers(){
        List<Developer> developers = new ArrayList<Developer>();
        try {
            connection = ConnectionFactory.createConnection(URL, USERNAME, PASSWORD);
            ResultSet resultSet = connection.getStatement().executeQuery(selectAllDeveloper);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_developer");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                Long itCompanyId = resultSet.getLong("it_company_id");
                Integer salary = resultSet.getInt("salary");
                developers.add(new Developer(id, name, age, sex, itCompanyId, salary));
            }
            connection.closeConnection();
        } catch (Exception e) {}
        return developers;
    }

    public void deleteDeveloper(Long developerId){
        try {
            connection = ConnectionFactory.createConnection(URL, USERNAME, PASSWORD);
             connection.getStatement().execute(String.format(deleteDeveloperFromDB, developerId));
            connection.closeConnection();
        } catch (Exception e) {}
    }

    public void updateDeveloper(Developer developer){
        try {
            connection = ConnectionFactory.createConnection(URL, USERNAME, PASSWORD);
            connection.getStatement().execute(String.format(updateDeveloper, developer.getName(), developer.getAge(), developer.getSex(),
                    developer.getItCompanyId(), developer.getSalary(), developer.getId()));
            connection.closeConnection();
        } catch (Exception e) {e.printStackTrace();}
    }
}
