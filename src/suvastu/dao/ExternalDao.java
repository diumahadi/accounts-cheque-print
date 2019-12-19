package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import suvastu.model.External;
import suvastu.model.ItemCombo;
import suvastu.util.DbUtil;

public class ExternalDao {
    
    private final Connection connection;

    public ExternalDao() {
        this.connection = DbUtil.getConnection();
    }
    
    
    public List<External> getMatch(String name) {
        
        name = name.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");

        List<External> list = new ArrayList<>();
        try {
            String sql = "SELECT external_name FROM inv_other_bill_paid WHERE external_name IS NOT NULL AND external_name LIKE ? ESCAPE '!' GROUP BY external_name ORDER BY external_name";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                External data = new External();
                data.setExternalName(rs.getString("external_name"));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("ExternalDao:getAllExternalName:SQL Error........." + e);
        }
        return list;
    }
    
    public List<External> getAllExternalName() {
        List<External> list = new ArrayList<>();
        try {
            String sql = "SELECT bp.external_name FROM inv_other_bill_paid bp WHERE bp.external_name IS NOT NULL GROUP BY bp.external_name ORDER BY bp.external_name";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                External data = new External();
                data.setExternalName(rs.getString("external_name").trim());
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("ExternalDao:getAllExternalName:SQL Error........." + e);
        }
        return list;
    }
    
    
    public List<ItemCombo> getPaymentType(){
        
        List<ItemCombo> pmList = new ArrayList<>();        
        pmList.add(new ItemCombo("MP", "Material Purchase"));
        pmList.add(new ItemCombo("PP", "Project Petty Cash"));
        pmList.add(new ItemCombo("SA", "Salary Advance"));        
        return pmList;
    }
}
