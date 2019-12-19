package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import suvastu.entity.VoucherDetails;
import suvastu.entity.VoucherMaster;
import suvastu.exception.UncaughtError;
import suvastu.util.DbUtil;

public class VoucherDao {
    
    private final Connection connection;
    
    private VoucherMaster voucherMaster;
    private VoucherDetails voucherDetail;
    
    private VoucherMasterDao voucherMasterDao;
    private VoucherDetailsDao voucherDetailDao;
    
    public VoucherDao() {
        this.connection = DbUtil.getConnection();
    }
    
    public VoucherMaster getVoucher(String voucherNo) {
        voucherMasterDao = new VoucherMasterDao();
        voucherMaster = voucherMasterDao.getMasterInfo(voucherNo, connection);
        
        voucherDetailDao = new VoucherDetailsDao();
        voucherMaster.setVoucherDetailList(voucherDetailDao.getVoucherDetails(voucherNo, connection));
        
        return voucherMaster;
    }
    
    public void save(VoucherMaster voucherMaster, List<VoucherDetails> voucherDetailsList) {
        try {
            connection.setAutoCommit(false);
            voucherMasterDao = new VoucherMasterDao();
            voucherMasterDao.save(voucherMaster, connection);
            connection.commit();
        } catch (SQLException e) {
            
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new UncaughtError("Error...:VoucherDao...:save..Rollback" + e);
            }
            
            throw new UncaughtError("Error...:VoucherDao...:save..." + e);
        }
        
    }
    
    public String getNewVoucherNumber(Integer comId, Integer finId, Integer finNo, String voucherTp, Integer monthNo) {
        
        String voucherId = "";
        try {
            
            String sql = "SELECT max(vm_vno) maxVno from acc_voucher_master where SUBSTRING(vm_vno,7,3) =? AND company_id=? AND vm_fin_year=? AND MONTH(vm_vdate)=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, voucherTp);
            ps.setInt(2, comId);
            ps.setInt(3, finId);
            ps.setInt(4, monthNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                voucherId = rs.getString("maxVno");
            }
            
            DecimalFormat format = new DecimalFormat("00");
            DecimalFormat formatId = new DecimalFormat("000000");
            
            if (voucherId != null) {
                voucherId = format.format(comId) + "" + format.format(finNo) + "" + format.format(monthNo) + "" + voucherTp + formatId.format(Integer.parseInt(voucherId.substring(9)) + 1);
            } else {
                voucherId = format.format(comId) + "" + format.format(finNo) + "" + format.format(monthNo) + "" + voucherTp + "000001";
            }
            
        } catch (SQLException e) {
            System.out.println("VoucherDao:getNewVoucherNumber:SQL Error........." + e);
        }
        return voucherId;
    }
}
