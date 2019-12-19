package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import suvastu.model.Employee;
import suvastu.util.DbUtil;

public class EmployeeDao {

    private final Connection connection;

    public EmployeeDao() {
        this.connection = DbUtil.getConnection();
    }

    public Employee getEmployee(String empId) {
        Employee data = new Employee();
        try {
            String sql = "SELECT * FROM view_emp_report WHERE emp_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                
                data.setBankAccountNo(rs.getString("bank_acc_no").trim());
                data.setDepartmentName(rs.getString("department_name").trim());
                data.setDesignationTitle(rs.getString("designation_title").trim());
                data.setEmpId(rs.getString("emp_id").trim());
                data.setEmpName(rs.getString("first_name").trim());
                data.setIsActive(rs.getInt("active"));  
            }
        } catch (SQLException e) {
            System.out.println("EmployeeDao:getEmployee:SQL Error........." + e);
        }
        return data;
    }
    
    public List<Employee> getMatch(String name) {
        
        name = name.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");

        List<Employee> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM view_emp_report WHERE first_name LIKE ? ESCAPE '!' ORDER BY first_name";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                Employee data = new Employee();
                data.setBankAccountNo(rs.getString("bank_acc_no").trim());
                data.setDepartmentName(rs.getString("department_name").trim());
                data.setDesignationTitle(rs.getString("designation_title").trim());
                data.setEmpId(rs.getString("emp_id").trim());
                data.setEmpName(rs.getString("first_name").trim());
                data.setIsActive(rs.getInt("active")); 
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("EmployeeDao:getMatch:SQL Error........." + e);
        }
        return list;
    }

    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM view_emp_report";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Employee data = new Employee();
                data.setBankAccountNo(rs.getString("bank_acc_no").trim());
                data.setDepartmentName(rs.getString("department_name").trim());
                data.setDesignationTitle(rs.getString("designation_title").trim());
                data.setEmpId(rs.getString("emp_id").trim());
                data.setEmpName(rs.getString("first_name").trim());
                data.setIsActive(rs.getInt("active"));                
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("EmployeeDao:getAllEmployee:SQL Error........." + e);
        }
        return list;
    }

}
