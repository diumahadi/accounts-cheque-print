package suvastu.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import suvastu.model.AppUser;
import suvastu.util.DbUtil;

public class AppUserDao {

    private final Connection connection;

    public AppUserDao() {
        this.connection = DbUtil.getConnection();
    }
    
    public AppUser getUser(String user_id) {
        AppUser data = new AppUser();
        try {
            String sql = "SELECT * FROM view_appuser WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                
                data.setIsActive(rs.getInt("is_active"));
                data.setUserId(rs.getString("user_id").trim());
                data.setUserLevel(rs.getString("user_level").trim());
                data.setUserName(rs.getString("first_name").trim()); 
                data.setUserPassword(rs.getString("user_password"));
            }
        } catch (SQLException e) {
            System.out.println("AppUserDao:Find:SQL Error........." + e);
        }
        return data;
    }
    
    public List<AppUser> getAllUser() {
        List<AppUser> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM view_appuser";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                AppUser data = new AppUser();
                data.setIsActive(rs.getInt("is_active"));
                data.setUserId(rs.getString("user_id").trim());
                data.setUserLevel(rs.getString("user_level").trim());
                data.setUserName(rs.getString("first_name").trim());
                data.setUserPassword(rs.getString("user_password"));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("AppUserDao:getAllUser:SQL Error........." + e);
        }
        return list;
    }
    
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
