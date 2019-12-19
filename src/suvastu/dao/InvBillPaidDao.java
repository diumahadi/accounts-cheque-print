package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import suvastu.entity.InvBillPaid;
import suvastu.exception.UncaughtError;
import suvastu.util.DbUtil;

public class InvBillPaidDao {

    private final Connection connection;

    public InvBillPaidDao() {
        this.connection = DbUtil.getConnection();
    }

    public void save(InvBillPaid data, Connection conn) {
        try {
            String sql = "INSERT INTO inv_bill_paid (supplier,payment_project,pay_mode,cheque_no,cheque_date,amount,ait_payable,pay_date,paied_by) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, data.getSupplier().getSupplierId());
            ps.setString(2, data.getProject().getProjectCode());
            ps.setString(3, data.getPaymentMode());
            ps.setString(4, String.valueOf(data.getCheque().getChequeId()));
            ps.setDate(5, new java.sql.Date(data.getChequeDate().getTime()));
            ps.setDouble(6, data.getPaidAmount());
            ps.setDouble(7, data.getAitAmount());
            ps.setDate(8, new java.sql.Date(data.getPayDate().getTime()));
            ps.setString(9, data.getAppUser().getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error...:InvBillPaidDao...:insertm..." + e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error...:InvBillPaidDao...:insertm...rollback" + e1);
            }

        }

    }
    
    public boolean delete(Long chequeId,Connection conn) {
        try {
            String sql = "DELETE FROM inv_bill_paid WHERE cheque_no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(chequeId));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new UncaughtError("Error...:InvBillPaidDao...:delete..." + e);
        }
        return true;
    }

    public InvBillPaid find(String id) {
        InvBillPaid data = new InvBillPaid();
        try {
            String sql = "SELECT * FROM inv_bill_paid WHERE bill_paid_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data.setAitAmount(rs.getDouble("ait_payable"));
                AppUserDao userDao = new AppUserDao();
                data.setAppUser(userDao.getUser(String.valueOf(rs.getInt("paied_by"))));
                AccountChequeDao accountChequeDao = new AccountChequeDao();
                data.setCheque(accountChequeDao.getCheque(Long.parseLong(rs.getString("cheque_no"))));
                data.setChequeDate(rs.getDate("cheque_date"));
                data.setPaidAmount(rs.getDouble("amount"));
                data.setPayDate(rs.getDate("pay_date"));
                data.setPaymentMode(rs.getString("pay_mode").trim());
                ProjectDao projectDao = new ProjectDao();
                data.setProject(projectDao.getProject(rs.getString("payment_project")));
                SupplierDao supplierDao = new SupplierDao();
                data.setSupplier(supplierDao.getSupplier(rs.getInt("supplier")));
            }
        } catch (SQLException e) {
            System.out.println("InvBillPaidDao:Find:SQL Error........." + e);
        }
        return data;
    }
    
    
    public List<InvBillPaid> getPaymentsUndearCheque(Long chequeId){     

        List<InvBillPaid> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM inv_bill_paid WHERE cheque_no=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,String.valueOf(chequeId));
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                InvBillPaid data = new InvBillPaid();
                data.setAitAmount(rs.getDouble("ait_payable"));
                AppUserDao userDao = new AppUserDao();
                data.setAppUser(userDao.getUser(String.valueOf(rs.getInt("paied_by"))));
                AccountChequeDao accountChequeDao = new AccountChequeDao();
                data.setCheque(accountChequeDao.getCheque(Long.parseLong(rs.getString("cheque_no"))));
                data.setChequeDate(rs.getDate("cheque_date"));
                data.setPaidAmount(rs.getDouble("amount"));
                data.setPayDate(rs.getDate("pay_date"));
                data.setPaymentMode(rs.getString("pay_mode").trim());
                ProjectDao projectDao = new ProjectDao();
                data.setProject(projectDao.getProject(rs.getString("payment_project")));
                SupplierDao supplierDao = new SupplierDao();
                data.setSupplier(supplierDao.getSupplier(rs.getInt("supplier")));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("InvBillPaidDao:getMatch:SQL Error........." + e);
        }
        
        Collections.sort(list, new Comparator<InvBillPaid>() {
            @Override
            public int compare(InvBillPaid d1, InvBillPaid d2) {
                return d1.getProject().getProjectName().compareToIgnoreCase(d2.getProject().getProjectName());
            }
        });
        return list;
    }

}
