package com.bida.dbconection.repository;

import com.bida.dbconection.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    private ConnectionFactory connection;

    private static final String selectAllProjectStartDateNameAmountDevelopers = "select start_date, name , count(developer_id) as developers_amount from projects p\n" +
            "join developers_projects dp on dp.project_id = p.id_project\n" +
            "group by name, start_date";

    public List<Project> findAllProjects(){
        List<Project> projects = new ArrayList<Project>();
        try {
            connection = ConnectionFactory.createConnection(MainDataForDataBaseConnection.URL, MainDataForDataBaseConnection.USERNAME, MainDataForDataBaseConnection.PASSWORD);
            ResultSet resultSet = connection.getStatement().executeQuery(selectAllProjectStartDateNameAmountDevelopers);
            while (resultSet.next()) {
                String startDate = resultSet.getString("start_date");
                String name = resultSet.getString("name");
                Integer developersAmount = resultSet.getInt("developers_amount");
                projects.add(new Project(startDate, name, developersAmount));
            }
            connection.closeConnection();
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(Project.class);
            logger.error("ERROR with findAllProjects");
        }
        return projects;
    }
}
