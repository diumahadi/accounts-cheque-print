package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import suvastu.entity.ChequeReprint;
import suvastu.exception.UncaughtError;
import suvastu.util.DbUtil;

public class ChequeReprintDao {
    
    private final Connection connection;

    public ChequeReprintDao() {
        this.connection = DbUtil.getConnection();
    }
    
    public void save(ChequeReprint data,Connection conn){
        
        try {
            String sql = "INSERT INTO cheque_reprint (cheque_html,check_date,print_date,supplier,amount_word,amount,bank,account_pay,cheque_no,bill_tp,cheque_no_id,note) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, data.getChequeHtml());
            ps.setDate(2, new java.sql.Date(data.getChequeDate().getTime()));
            ps.setDate(3, new java.sql.Date(data.getPrintDate().getTime()));
            ps.setString(4, data.getPersonName());
            ps.setString(5, data.getChequeAmountInWord());
            ps.setDouble(6, data.getChequeAmount());
            ps.setString(7, data.getBankShortCode());
            ps.setString(8, data.getIsAccountPay());
            ps.setString(9, data.getChequeNo());
            ps.setString(10, data.getBillTp());
            ps.setLong(11, data.getChequeNoId());
            ps.setString(12, data.getNote());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error...:ChequeReprintDao...:insertm..." + e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error...:ChequeReprintDao...:insertm...rollback" + e1);
            }

        }
        
    }
    
    public boolean delete(Long chequeId,Connection conn) {
        try {
            String sql = "DELETE FROM cheque_reprint WHERE cheque_no_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, chequeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new UncaughtError("Error...:ChequeReprintDao...:delete..." + e);
        }
        return true;
    }
    
    
    public ChequeReprint getCheque(String chequeNo){
        ChequeReprint data = new ChequeReprint();
        try {
            String sql = "SELECT * FROM cheque_reprint WHERE cheque_no = ? LIMIT 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, chequeNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                
                data.setBankShortCode(rs.getString("bank"));
                data.setBillTp(rs.getString("bill_tp"));
                data.setChequeAmount(rs.getDouble("amount"));
                data.setChequeAmountInWord(rs.getString("amount_word"));
                data.setChequeDate(rs.getDate("check_date"));
                data.setChequeHtml(rs.getString("cheque_html"));
                data.setChequeNo(rs.getString("cheque_no"));
                data.setChequeNoId(rs.getLong("cheque_no_id"));
                data.setIsAccountPay(rs.getString("account_pay"));
                data.setNote(rs.getString("note"));
                data.setPersonName(rs.getString("supplier"));
                data.setPrintDate(rs.getDate("print_date"));
                
            }
        } catch (SQLException e) {
            System.out.println("EmployeeDao:getEmployee:SQL Error........." + e);
        }
        return data;
    }
}
