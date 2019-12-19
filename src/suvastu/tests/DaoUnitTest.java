package suvastu.tests;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import suvastu.dao.*;

public class DaoUnitTest {
    
    public static void main(String[] args) {
        
        String no = "020711JV0000005";
        
        VoucherDao voucherDao = new VoucherDao();
        System.out.println("" + voucherDao.getVoucher(no));
    }
}
