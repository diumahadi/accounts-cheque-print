package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import suvastu.entity.VoucherDetails;
import suvastu.exception.UncaughtError;

public class VoucherDetailsDao {

    public void save(VoucherDetails data, Connection conn) {
        try {
            String sql = "INSERT INTO acc_voucher_detail (vd_sl_no,vd_vno,vd_ca_id,vd_project_code,vd_debit,vd_credit,vd_chq_no) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, data.getSlNo());
            ps.setString(2, data.getVoucherNo());
            ps.setString(3, data.getCaCode());
            ps.setString(4, data.getProjectCode());
            ps.setDouble(5, data.getDebitAmount());
            ps.setDouble(6, data.getCreditAmount());
            ps.setString(7, data.getChequeNo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new UncaughtError("Error...:VoucherDetailsDao...:save..." + e);
        }

    }
    
    
    public List<VoucherDetails> getVoucherDetails(String voucherNo, Connection conn) {

        List<VoucherDetails> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM acc_voucher_detail WHERE vd_vno=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,voucherNo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VoucherDetails data = new VoucherDetails();
                data.setCaCode(rs.getString("vd_ca_id"));
                data.setChequeNo(rs.getString("vd_chq_no"));
                data.setCreditAmount(rs.getDouble("vd_credit"));
                data.setDebitAmount(rs.getDouble("vd_debit"));
                data.setProjectCode(rs.getString("vd_project_code"));
                data.setSlNo(rs.getInt("vd_sl_no"));
                data.setVoucherNo(rs.getString("vd_vno"));
                

               
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("InvOtherBillPaidDao:getMatch:SQL Error........." + e);
        }     

        return list;
    }
    
}
