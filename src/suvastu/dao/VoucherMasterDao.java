package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import suvastu.entity.VoucherMaster;
import suvastu.exception.UncaughtError;

public class VoucherMasterDao {


    public void save(VoucherMaster data, Connection conn) {
        try {
            String sql = "INSERT INTO acc_voucher_master (vm_vno,vm_vdate,vm_fin_year,vm_narration,company_id,vm_created_by) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, data.getVoucherNo());
            ps.setDate(2, new java.sql.Date(data.getVoucherDate().getTime()));
            ps.setInt(3, data.getFinYear());
            ps.setString(4, data.getNarration());
            ps.setInt(5, data.getCompanyId());
            ps.setString(6, data.getAppUser().getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new UncaughtError("Error...:VoucherMasterDao...:save..." + e);
        }

    }
    
    public VoucherMaster getMasterInfo(String voucherNo, Connection conn) {        
        VoucherMaster data = new VoucherMaster();
        try {
            String sql = "SELECT * FROM acc_voucher_master WHERE vm_vno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, voucherNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AppUserDao appUserDao=new AppUserDao();
                data.setAppUser(appUserDao.getUser(rs.getString("vm_created_by")));
                data.setCompanyId(rs.getInt("company_id"));
                data.setFinYear(rs.getInt("vm_fin_year"));
                data.setNarration(rs.getString("vm_narration")); 
                data.setVoucherDate(rs.getDate("vm_vdate"));
                data.setVoucherNo(rs.getString("vm_vno"));
            }
        } catch (SQLException e) {
            System.out.println("Error:VoucherMasterDao:Find:SQL Error........." + e);
        }
        return data;
        
        
        

    }
}
