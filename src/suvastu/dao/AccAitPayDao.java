package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import suvastu.entity.AccAitPay;
import suvastu.util.DbUtil;


public class AccAitPayDao {
    private final Connection connection;

    public AccAitPayDao() {
        this.connection = DbUtil.getConnection();
    }
    
    public void save(AccAitPay data, Connection conn) {
        try {
            String sql = "INSERT INTO acc_ait_pay (paid_id,voucher_no,cheque_id) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setLong(1, data.getPaidId());           
            if(data.getVoucherNo()!=null){
                ps.setString(2, data.getVoucherNo());
            }else {
                ps.setNull(2, java.sql.Types.VARCHAR);
            }
            ps.setLong(3, data.getChequeId());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error...:AccAitPayDao...:save..." + e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Error...:AccAitPayDao...:save...rollback" + e1);
            }

        }

    }
}
