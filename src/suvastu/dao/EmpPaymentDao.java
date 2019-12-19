package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import suvastu.model.EmpPayment;
import suvastu.util.DbUtil;

public class EmpPaymentDao {
    
    private final Connection connection;

    public EmpPaymentDao() {
        this.connection = DbUtil.getConnection();
    }
    
    public List<EmpPayment> getMatch(String pmTp,String name) {

        name = name.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");

        List<EmpPayment> list = new ArrayList<>();
        try {
            
            String sql="SELECT acc.id,acc.emp_id,emp.first_name,emp.designation_title,emp.department_name,acc.acc_code,acc.code_type\n" +
            "FROM acc_emp_acc_code_map acc\n" +
            "JOIN view_emp_report emp ON emp.emp_id=acc.emp_id\n" +
            "WHERE acc.code_type=? AND CONCAT(acc.emp_id,emp.first_name) LIKE ? ESCAPE '!'\n" +
            "ORDER BY emp.first_name";
            
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pmTp);
            ps.setString(2, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EmpPayment data = new EmpPayment();
                data.setAccCode(rs.getString("acc_code").trim());
                data.setCodeTp(rs.getString("code_type").trim());
                data.setDeptName(rs.getString("department_name").trim());
                data.setDesignationTitle(rs.getString("designation_title").trim());
                data.setEmpId(rs.getString("emp_id"));
                data.setEmpName(rs.getString("first_name"));
                data.setPaymentId(rs.getInt("id"));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("EmpPaymentDao:getMatch:SQL Error........." + e);
        }
        return list;
    }
}
