package suvastu.util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;

public class ReportUtil extends JInternalFrame {

    public ReportUtil(String fileName) {
        this(fileName, null);
    }

    public ReportUtil(String fileName, HashMap parameter) {

        super("Reports", true, true, true, true);
        try {
            Properties prop = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("config.properties");
            prop.load(stream);

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            JasperPrint print = JasperFillManager.fillReport(fileName, parameter, con);

            setLayout(new BorderLayout());
            JRViewer viewer = new JRViewer(print);
            add(viewer);
            setVisible(true);

        } catch (ClassNotFoundException | SQLException | JRException | IOException cnfe) {
            cnfe.printStackTrace();
            System.out.println("Cannot connect ireport: ");
        }
    }
}
