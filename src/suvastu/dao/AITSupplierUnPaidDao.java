package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import suvastu.entity.InvBillPaid;
import suvastu.model.AITSupplierUnPaid;
import suvastu.util.DbUtil;

public class AITSupplierUnPaidDao {
    private final Connection connection;

    public AITSupplierUnPaidDao() {
        this.connection = DbUtil.getConnection();
    }
    
    
    public List<AITSupplierUnPaid> getAllSupplierAIT() {
        List<AITSupplierUnPaid> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM view_ait_supplier_unpaid ORDER BY voucher,project_name";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                AITSupplierUnPaid data = new AITSupplierUnPaid();
                AccountChequeDao accountChequeDao=new AccountChequeDao();                
                data.setAccountCheque(accountChequeDao.getCheque(rs.getLong("cheque_id")));
                data.setAitAmount(rs.getDouble("ait_payable"));
                data.setCreatedAt(rs.getTimestamp("created_at"));
                data.setCreatedBy(rs.getString("paied_by"));
                data.setId(rs.getLong("bill_paid_id"));
                data.setProjectCode(rs.getString("payment_project"));
                data.setProjectName(rs.getString("project_name"));
                data.setSupplierId(rs.getInt("supplier"));
                data.setSupplierName(rs.getString("supplier_name"));
                data.setVoucherNo(rs.getString("voucher"));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("AITSupplierUnPaidDao:getAllUser:SQL Error........." + e);
        }
        return list;
    }
    
    public List<AITSupplierUnPaid> getPaymentsUndearSupplier(Integer supplierId){     

        List<AITSupplierUnPaid> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM view_ait_supplier_unpaid WHERE supplier=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,supplierId);
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                AITSupplierUnPaid data = new AITSupplierUnPaid();
                AccountChequeDao accountChequeDao=new AccountChequeDao();                
                data.setAccountCheque(accountChequeDao.getCheque(rs.getLong("cheque_id")));
                data.setAitAmount(rs.getDouble("ait_payable"));
                data.setCreatedAt(rs.getTimestamp("created_at"));
                data.setCreatedBy(rs.getString("paied_by"));
                data.setId(rs.getLong("bill_paid_id"));
                data.setProjectCode(rs.getString("payment_project"));
                data.setProjectName(rs.getString("project_name"));
                data.setSupplierId(rs.getInt("supplier"));
                data.setSupplierName(rs.getString("supplier_name"));
                data.setVoucherNo(rs.getString("voucher"));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("AITSupplierUnPaidDao:getPaymentsUndearCheque:SQL Error........." + e);
        }
        
        Collections.sort(list, new Comparator<AITSupplierUnPaid>() {
            @Override
            public int compare(AITSupplierUnPaid d1, AITSupplierUnPaid d2) {
                return d1.getProjectName().compareToIgnoreCase(d2.getProjectName());
            }
        });
        return list;
    }
    
    public List<AITSupplierUnPaid> getPaymentsUndearCheque(Long chequeId){     

        List<AITSupplierUnPaid> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM view_ait_supplier_unpaid WHERE cheque_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1,chequeId);
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                AITSupplierUnPaid data = new AITSupplierUnPaid();
                AccountChequeDao accountChequeDao=new AccountChequeDao();                
                data.setAccountCheque(accountChequeDao.getCheque(rs.getLong("cheque_id")));
                data.setAitAmount(rs.getDouble("ait_payable"));
                data.setCreatedAt(rs.getTimestamp("created_at"));
                data.setCreatedBy(rs.getString("paied_by"));
                data.setId(rs.getLong("bill_paid_id"));
                data.setProjectCode(rs.getString("payment_project"));
                data.setProjectName(rs.getString("project_name"));
                data.setSupplierId(rs.getInt("supplier"));
                data.setSupplierName(rs.getString("supplier_name"));
                data.setVoucherNo(rs.getString("voucher"));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("AITSupplierUnPaidDao:getPaymentsUndearCheque:SQL Error........." + e);
        }
        
        Collections.sort(list, new Comparator<AITSupplierUnPaid>() {
            @Override
            public int compare(AITSupplierUnPaid d1, AITSupplierUnPaid d2) {
                return d1.getProjectName().compareToIgnoreCase(d2.getProjectName());
            }
        });
        return list;
    }
}
