package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import suvastu.entity.Cheque;

public class ChequeDao {

    public Cheque update(Cheque data, Connection connection) {

        try {
            String sql = "UPDATE acc_bank_cheque SET cheque_date=?,expence_date=?,expence_mode=?,cheque_amount=? WHERE cheque_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            if (data.getChequeDate() != null) {
                ps.setDate(1, new java.sql.Date(data.getChequeDate().getTime()));
            } else {
                ps.setNull(1, java.sql.Types.DATE);
            }
            
            if (data.getChequeDate() != null) {
                ps.setDate(2, new java.sql.Date(data.getExpenseDate().getTime()));
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }
            
            ps.setString(3, data.getExpenseMode());
            ps.setDouble(4, data.getChequeAmount());
            ps.setLong(5, data.getChequeId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error...:ChequeDao...:Update..." + e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println("Error...:ChequeDao...:Update...rollback" + e1);
            }
        }
        return data;
    }
}
