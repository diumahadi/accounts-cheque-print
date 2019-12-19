package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import suvastu.model.AccountCheque;
import suvastu.util.DbUtil;

public class AccountChequeDao {

    private final Connection connection;

    public AccountChequeDao() {
        this.connection = DbUtil.getConnection();
    }

    public AccountCheque getCheque(Long chequeId) {
        AccountCheque data = new AccountCheque();
        try {
            String sql = "SELECT * FROM view_acc_bank_cheque WHERE cheque_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, chequeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data.setAccountId(rs.getInt("account_id"));
                data.setAccountNumber(rs.getString("account_number").trim());
                data.setBankCaCode(rs.getString("bank_acc_code").trim());
                data.setBankId(rs.getInt("bi_id"));
                data.setBankName(rs.getString("bi_name").trim());
                data.setBankShortName(rs.getString("bi_short_name").trim());
                data.setBranch(rs.getString("branch").trim());
                data.setChequeAmount(rs.getDouble("cheque_amount"));
                data.setChequeDate(rs.getDate("cheque_date"));
                data.setChequeId(rs.getLong("cheque_id"));
                data.setChequeNo(rs.getString("cheque_no").trim());
                data.setCreatedAt(rs.getTimestamp("created_at"));
                data.setExpenseDate(rs.getDate("expence_date"));
                data.setExpenseMode(rs.getString("expence_mode").trim());
            }
        } catch (SQLException e) {
            System.out.println("AccountChequeDao:getCheque:SQL Error........." + e);
        }
        return data;
    }

    public List<AccountCheque> getMatch(String name) {

        name = name.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");

        List<AccountCheque> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM view_acc_bank_cheque WHERE concat(bi_short_name,cheque_no) LIKE ? ESCAPE '!' AND expence_mode='A' ORDER BY bi_short_name,cheque_no";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountCheque data = new AccountCheque();
                data.setAccountId(rs.getInt("account_id"));
                data.setAccountNumber(rs.getString("account_number").trim());
                data.setBankCaCode(rs.getString("bank_acc_code").trim());
                data.setBankId(rs.getInt("bi_id"));
                data.setBankName(rs.getString("bi_name").trim());
                data.setBankShortName(rs.getString("bi_short_name").trim());
                data.setBranch(rs.getString("branch").trim());
                data.setChequeAmount(rs.getDouble("cheque_amount"));
                data.setChequeDate(rs.getDate("cheque_date"));
                data.setChequeId(rs.getLong("cheque_id"));
                data.setChequeNo(rs.getString("cheque_no").trim());
                data.setCreatedAt(rs.getTimestamp("created_at"));
                data.setExpenseDate(rs.getDate("expence_date"));
                data.setExpenseMode(rs.getString("expence_mode").trim());
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("AccountChequeDao:getMatch:SQL Error........." + e);
        }
        return list;
    }

    public List<AccountCheque> getAllCheque() {
        List<AccountCheque> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM view_acc_bank_cheque";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                AccountCheque data = new AccountCheque();
                data.setAccountId(rs.getInt("account_id"));
                data.setAccountNumber(rs.getString("account_number").trim());
                data.setBankCaCode(rs.getString("bank_acc_code").trim());
                data.setBankId(rs.getInt("bi_id"));
                data.setBankName(rs.getString("bi_name").trim());
                data.setBankShortName(rs.getString("bi_short_name").trim());
                data.setBranch(rs.getString("branch").trim());
                data.setChequeAmount(rs.getDouble("cheque_amount"));
                data.setChequeDate(rs.getDate("cheque_date"));
                data.setChequeId(rs.getLong("cheque_id"));
                data.setChequeNo(rs.getString("cheque_no").trim());
                data.setCreatedAt(rs.getTimestamp("created_at"));
                data.setExpenseDate(rs.getDate("expence_date"));
                data.setExpenseMode(rs.getString("expence_mode").trim());
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("AccountChequeDao:getAllCheque:SQL Error........." + e);
        }
        return list;
    }

    public List<AccountCheque> getAllHonouredCheque() {
        List<AccountCheque> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM view_acc_bank_cheque WHERE expence_mode='HO'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                AccountCheque data = new AccountCheque();
                data.setAccountId(rs.getInt("account_id"));
                data.setAccountNumber(rs.getString("account_number").trim());
                data.setBankCaCode(rs.getString("bank_acc_code").trim());
                data.setBankId(rs.getInt("bi_id"));
                data.setBankName(rs.getString("bi_name").trim());
                data.setBankShortName(rs.getString("bi_short_name").trim());
                data.setBranch(rs.getString("branch").trim());
                data.setChequeAmount(rs.getDouble("cheque_amount"));
                data.setChequeDate(rs.getDate("cheque_date"));
                data.setChequeId(rs.getLong("cheque_id"));
                data.setChequeNo(rs.getString("cheque_no").trim());
                data.setCreatedAt(rs.getTimestamp("created_at"));
                data.setExpenseDate(rs.getDate("expence_date"));
                data.setExpenseMode(rs.getString("expence_mode").trim());
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("AccountChequeDao:getAllHonouredCheque:SQL Error........." + e);
        }
        return list;
    }

    public List<AccountCheque> getAllAvailableCheque() {
        List<AccountCheque> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM view_acc_bank_cheque WHERE expence_mode='A'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                AccountCheque data = new AccountCheque();
                data.setAccountId(rs.getInt("account_id"));
                data.setAccountNumber(rs.getString("account_number").trim());
                data.setBankCaCode(rs.getString("bank_acc_code").trim());
                data.setBankId(rs.getInt("bi_id"));
                data.setBankName(rs.getString("bi_name").trim());
                data.setBankShortName(rs.getString("bi_short_name").trim());
                data.setBranch(rs.getString("branch").trim());
                data.setChequeAmount(rs.getDouble("cheque_amount"));
                data.setChequeDate(rs.getDate("cheque_date"));
                data.setChequeId(rs.getLong("cheque_id"));
                data.setChequeNo(rs.getString("cheque_no").trim());
                data.setCreatedAt(rs.getTimestamp("created_at"));
                data.setExpenseDate(rs.getDate("expence_date"));
                data.setExpenseMode(rs.getString("expence_mode").trim());
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("AccountChequeDao:getAllAvailableCheque:SQL Error........." + e);
        }
        return list;
    }

    public List<AccountCheque> getAllDamageCheque() {
        List<AccountCheque> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM view_acc_bank_cheque WHERE expence_mode NOT IN('A','HO')";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                AccountCheque data = new AccountCheque();
                data.setAccountId(rs.getInt("account_id"));
                data.setAccountNumber(rs.getString("account_number").trim());
                data.setBankCaCode(rs.getString("bank_acc_code").trim());
                data.setBankId(rs.getInt("bi_id"));
                data.setBankName(rs.getString("bi_name").trim());
                data.setBankShortName(rs.getString("bi_short_name").trim());
                data.setBranch(rs.getString("branch").trim());
                data.setChequeAmount(rs.getDouble("cheque_amount"));
                data.setChequeDate(rs.getDate("cheque_date"));
                data.setChequeId(rs.getLong("cheque_id"));
                data.setChequeNo(rs.getString("cheque_no").trim());
                data.setCreatedAt(rs.getTimestamp("created_at"));
                data.setExpenseDate(rs.getDate("expence_date"));
                data.setExpenseMode(rs.getString("expence_mode").trim());
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("AccountChequeDao:getAllAvailableCheque:SQL Error........." + e);
        }
        return list;
    }
}
