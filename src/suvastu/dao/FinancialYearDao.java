package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import suvastu.model.AppUser;
import suvastu.model.FinancialYear;
import suvastu.util.DbUtil;

public class FinancialYearDao {

    private final Connection connection;

    public FinancialYearDao() {
        this.connection = DbUtil.getConnection();
    }
    
    public List<FinancialYear> getAllFinancialYear() {
        List<FinancialYear> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM view_acc_fin_year";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                FinancialYear data = new FinancialYear();
                data.setCompanyId(rs.getInt("fy_comid"));
                data.setCompanyName(rs.getString("company_name"));
                data.setEndDate(rs.getDate("endDate"));
                data.setFinId(rs.getInt("fy_id"));
                data.setFinNo(rs.getInt("fy_no"));
                data.setStartDate(rs.getDate("startDate"));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("FinancialYearDao:getAllFinancialYear:SQL Error........." + e);
        }
        return list;
    }
    
    public  FinancialYear getFinYearByVoucherDate(Date voucherDate,Integer comId) {
        FinancialYear data = new FinancialYear();
        try {
            String sql = "SELECT * FROM view_acc_fin_year WHERE ? BETWEEN startDate AND endDate AND fy_comid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(voucherDate.getTime()));
            ps.setInt(2, comId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                
                data.setCompanyId(rs.getInt("fy_comid"));
                data.setCompanyName(rs.getString("company_name"));
                data.setEndDate(rs.getDate("endDate"));
                data.setFinId(rs.getInt("fy_id"));
                data.setFinNo(rs.getInt("fy_no"));
                data.setStartDate(rs.getDate("startDate"));
            }
        } catch (SQLException e) {
            System.out.println("FinancialYearDao:getFinYearByVoucherDate:SQL Error........." + e);
        }
        return data;
    }
}
