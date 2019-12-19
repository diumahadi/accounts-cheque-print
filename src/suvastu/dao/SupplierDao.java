package suvastu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import suvastu.model.Supplier;
import suvastu.util.DbUtil;

public class SupplierDao {

    private final Connection connection;

    public SupplierDao() {
        this.connection = DbUtil.getConnection();
    }

    public Supplier getSupplier(Integer supplierId) {
        Supplier data = new Supplier();
        try {
            String sql = "SELECT supplier_id,supplier_name,supplier_bank_name,acc_code,supplierTp,supplier_type FROM view_supplier WHERE supplier_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, supplierId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data.setCaCode(rs.getString("acc_code").trim());
                data.setSupplierBank(rs.getString("supplier_bank_name").trim());
                data.setSupplierId(rs.getInt("supplier_id"));
                data.setSupplierName(rs.getString("supplier_name").trim());
                data.setSupplierType(rs.getInt("supplier_type"));
            }
        } catch (SQLException e) {
            System.out.println("SupplierDao:getSupplier:SQL Error........." + e);
        }
        return data;
    }
    
    public List<Supplier> getMatch(String name) {
        
        name = name.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");

        List<Supplier> list = new ArrayList<>();
        try {
            String sql = "SELECT supplier_id,supplier_name,supplier_bank_name,acc_code,supplierTp,supplier_type FROM view_supplier WHERE supplier_name LIKE ? ESCAPE '!' ORDER BY supplier_name";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                Supplier data = new Supplier();
                data.setCaCode(rs.getString("acc_code").trim());
                data.setSupplierBank(rs.getString("supplier_bank_name").trim());
                data.setSupplierId(rs.getInt("supplier_id"));
                data.setSupplierName(rs.getString("supplier_name").trim());
                data.setSupplierType(rs.getInt("supplier_type"));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("SupplierDao:getMatch:SQL Error........." + e);
        }
        return list;
    }
    
    public List<Supplier> getMatchSupplier(String name) {
        
        name = name.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");

        List<Supplier> list = new ArrayList<>();
        try {
            String sql = "SELECT supplier_id,supplier_name,supplier_bank_name,acc_code,supplierTp,supplier_type FROM view_supplier WHERE acc_code IS NOT NULL AND supplier_type!=3 AND supplier_name LIKE ? ESCAPE '!' ORDER BY supplier_name";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                Supplier data = new Supplier();
                data.setCaCode(rs.getString("acc_code").trim());
                data.setSupplierBank(rs.getString("supplier_bank_name").trim());
                data.setSupplierId(rs.getInt("supplier_id"));
                data.setSupplierName(rs.getString("supplier_name").trim());
                data.setSupplierType(rs.getInt("supplier_type"));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("SupplierDao:getMatch:SQL Error........." + e);
        }
        return list;
    }
    
    public List<Supplier> getMatchSubContractor(String name) {
        
        name = name.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");

        List<Supplier> list = new ArrayList<>();
        try {
            String sql = "SELECT supplier_id,supplier_name,supplier_bank_name,acc_code,supplierTp,supplier_type FROM view_supplier WHERE supplier_type=3 AND supplier_name LIKE ? ESCAPE '!' ORDER BY supplier_name";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                Supplier data = new Supplier();
                data.setCaCode(rs.getString("acc_code").trim());
                data.setSupplierBank(rs.getString("supplier_bank_name").trim());
                data.setSupplierId(rs.getInt("supplier_id"));
                data.setSupplierName(rs.getString("supplier_name").trim());
                data.setSupplierType(rs.getInt("supplier_type"));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("SupplierDao:getMatch:SQL Error........." + e);
        }
        return list;
    }

    public List<Supplier> getAllSupplier() {
        List<Supplier> list = new ArrayList<>();
        try {
            String sql = "SELECT supplier_id,supplier_name,supplier_bank_name,acc_code,supplierTp,supplier_type FROM view_supplier ORDER BY supplier_name";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Supplier data = new Supplier();
                data.setCaCode(rs.getString("acc_code").trim());
                data.setSupplierBank(rs.getString("supplier_bank_name").trim());
                data.setSupplierId(rs.getInt("supplier_id"));
                data.setSupplierName(rs.getString("supplier_name").trim());
                data.setSupplierType(rs.getInt("supplier_type"));
                list.add(data);
            }
        } catch (SQLException e) {
            System.out.println("SupplierDao:getAllSupplier:SQL Error........." + e);
        }
        return list;
    }
}
