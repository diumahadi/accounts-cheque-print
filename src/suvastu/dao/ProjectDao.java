package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import suvastu.model.Project;
import suvastu.util.DbUtil;

public class ProjectDao {

    private final Connection connection;

    public ProjectDao() {
        this.connection = DbUtil.getConnection();
    }

    public Project getProject(String projectCode) {
        Project data = new Project();
        try {
            String sql = "SELECT project_code,project_name FROM project_infos WHERE project_code = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, projectCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data.setProjectCode(rs.getString("project_code").trim());
                data.setProjectName(rs.getString("project_name").trim());
            }
        } catch (SQLException e) {
            System.out.println("ProjectDao:getProject:SQL Error........." + e);
        }
        return data;
    }

    public List<Project> getMatch(String name) {

        name = name.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");

        List<Project> list = new ArrayList<>();
        try {
            String sql = "SELECT project_code,project_name FROM project_infos WHERE project_name LIKE ? ESCAPE '!' ORDER BY project_name";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project data = new Project();
                data.setProjectCode(rs.getString("project_code").trim());
                data.setProjectName(rs.getString("project_name").trim());
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("ProjectDao:getMatch:SQL Error........." + e);
        }
        return list;
    }

    public List<Project> getAllProject() {
        List<Project> list = new ArrayList<>();
        try {
           String sql = "SELECT project_code,project_name FROM project_infos ORDER BY project_name";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Project data = new Project();
                data.setProjectCode(rs.getString("project_code").trim());
                data.setProjectName(rs.getString("project_name").trim());
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("ProjectDao:getAllSupplier:SQL Error........." + e);
        }
        return list;
    }
}
