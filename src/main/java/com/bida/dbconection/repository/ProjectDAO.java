package com.bida.dbconection.repository;

import com.bida.dbconection.domain.Project;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    private ConnectionFactory connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Nazar1997";

    private static final String selectAllProjectStartDateNameAmountDevelopers = "select start_date, name , count(developer_id) as developers_amount from projects p\n" +
            "join developers_projects dp on dp.project_id = p.id_project\n" +
            "group by name, start_date";

    public List<Project> findAllProjects(){
        List<Project> projects = new ArrayList<Project>();

        try {
            connection = ConnectionFactory.createConnection(URL, USERNAME, PASSWORD);
            ResultSet resultSet = connection.getStatement().executeQuery(selectAllProjectStartDateNameAmountDevelopers);
            while (resultSet.next()) {
                String startDate = resultSet.getString("start_date");
                String name = resultSet.getString("name");
                Integer developersAmount = resultSet.getInt("developers_amount");
                projects.add(new Project(startDate, name, developersAmount));
            }
            connection.closeConnection();
        } catch (Exception e) {}
        return projects;
    }
}
