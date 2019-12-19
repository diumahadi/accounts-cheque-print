package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import suvastu.entity.InvBillPaid;
import suvastu.entity.InvOtherBillPaid;
import suvastu.exception.UncaughtError;
import suvastu.util.DbUtil;

public class InvOtherBillPaidDao {

    private final Connection connection;

    public InvOtherBillPaidDao() {
        this.connection = DbUtil.getConnection();
    }

    public void save(InvOtherBillPaid data, Connection conn) {
        try {
            String sql = "INSERT INTO inv_other_bill_paid (emp_sc_code,emp_id,external_name,cheque_date,pay_date,amount,cheque_no,mr_no,payment_project,note,paid_by) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            if (data.getAccCode() != null) {
                ps.setString(1, data.getAccCode());
            } else {
                ps.setNull(1, Types.VARCHAR);
            }

            if (data.getAccCode() != null) {
                ps.setString(2, data.getEmpId());
            } else {
                ps.setNull(2, Types.VARCHAR);
            }

            ps.setString(3, data.getExternalName());
            ps.setDate(4, new java.sql.Date(data.getChequeDate().getTime()));
            ps.setDate(5, new java.sql.Date(data.getPaidDate().getTime()));
            ps.setDouble(6, data.getPaidAmount());
            ps.setString(7, String.valueOf(data.getAccountCheque().getChequeId()));
            if (data.getMrNo() != null) {
                ps.setString(8, data.getMrNo());
            } else {
                ps.setNull(8, Types.VARCHAR);
            }
            ps.setString(9, data.getProject().getProjectCode());
            ps.setString(10, data.getNotes());
            ps.setString(11, data.getAppUser().getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error...:InvOtherBillPaidDao...:insertm..." + e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error...:InvOtherBillPaidDao...:insertm...rollback" + e1);
            }

        }

    }

    public boolean delete(Long chequeId, Connection conn) {
        try {
            String sql = "DELETE FROM inv_other_bill_paid WHERE cheque_no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(chequeId));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new UncaughtError("Error...:InvBillPaidDao...:delete..." + e);
        }
        return true;
    }

    public List<InvOtherBillPaid> getPaymentsUndearCheque(Long chequeId) {

        List<InvOtherBillPaid> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM inv_other_bill_paid WHERE cheque_no=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(chequeId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InvOtherBillPaid data = new InvOtherBillPaid();
                if (rs.getString("emp_sc_code") != null) {
                    data.setAccCode(rs.getString("emp_sc_code"));
                }

                AccountChequeDao accountChequeDao = new AccountChequeDao();
                data.setAccountCheque(accountChequeDao.getCheque(chequeId));
                AppUserDao appUserDao = new AppUserDao();
                data.setAppUser(appUserDao.getUser(rs.getString("paid_by")));
                data.setChequeDate(rs.getDate("cheque_date"));
                if (rs.getString("emp_id") != null) {
                    data.setEmpId(rs.getString("emp_id"));
                }
                data.setExternalName(rs.getString("external_name"));
                data.setMrNo(rs.getString("mr_no"));
                data.setNotes(rs.getString("note"));
                data.setPaidAmount(rs.getDouble("amount"));
                data.setPaidDate(rs.getDate("pay_date"));
                ProjectDao projectDao = new ProjectDao();
                data.setProject(projectDao.getProject(rs.getString("payment_project")));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("InvOtherBillPaidDao:getMatch:SQL Error........." + e);
        }

        Collections.sort(list, new Comparator<InvOtherBillPaid>() {
            @Override
            public int compare(InvOtherBillPaid d1, InvOtherBillPaid d2) {
                return d1.getProject().getProjectName().compareToIgnoreCase(d2.getProject().getProjectName());
            }
        });

        return list;
    }
}
