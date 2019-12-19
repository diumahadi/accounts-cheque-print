package suvastu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import suvastu.entity.AccAitPay;
import suvastu.entity.Cheque;
import suvastu.entity.ChequeReprint;
import suvastu.entity.InvBillPaid;
import suvastu.entity.InvOtherBillPaid;
import suvastu.entity.VoucherDetails;
import suvastu.entity.VoucherMaster;
import suvastu.exception.UncaughtError;
import suvastu.util.DbUtil;

public class BillPaymentDao {

    private final Connection connection;

    private ChequeDao chequeDao;
    private ChequeReprintDao chequeReprintDao;

    private AccAitPayDao accAitPayDao;
    private VoucherMasterDao voucherMasterDao;
    private VoucherDetailsDao voucherDetailsDao;
    private InvBillPaidDao invBillPaidDao;
    private InvSubContractorBillPaidDao subContractorBillPaidDao;
    private InvOtherBillPaidDao otherBillPaidDao;

    public BillPaymentDao() {
        this.connection = DbUtil.getConnection();
    }

    public boolean supplierBillPayment(List<InvBillPaid> billPaidProjectList, Cheque cheque, ChequeReprint chequeReprint) {

        try {
            connection.setAutoCommit(false);

            for (InvBillPaid billPaid : billPaidProjectList) {
                invBillPaidDao = new InvBillPaidDao();
                invBillPaidDao.save(billPaid, connection);
            }

            chequeDao = new ChequeDao();
            chequeDao.update(cheque, connection);

            chequeReprintDao = new ChequeReprintDao();
            chequeReprintDao.save(chequeReprint, connection);

            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new UncaughtError("Error...:BillPaymentDao...:insert..Rollback" + e);
            }

            throw new UncaughtError("Error...:BillPaymentDao...:insert..." + e);
        }

        return true;
    }

    public boolean subContractorBillPayment(List<InvBillPaid> billPaidProjectList, Cheque cheque, ChequeReprint chequeReprint) {

        try {
            connection.setAutoCommit(false);

            for (InvBillPaid billPaid : billPaidProjectList) {
                subContractorBillPaidDao = new InvSubContractorBillPaidDao();
                subContractorBillPaidDao.save(billPaid, connection);
            }

            chequeDao = new ChequeDao();
            chequeDao.update(cheque, connection);

            chequeReprintDao = new ChequeReprintDao();
            chequeReprintDao.save(chequeReprint, connection);
            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new UncaughtError("Error...:BillPaymentDao...:subContractorBillPayment..Rollback" + e);
            }

            throw new UncaughtError("Error...:BillPaymentDao...:subContractorBillPayment..." + e);
        }

        return true;
    }

    public boolean externalsBillPayment(List<InvOtherBillPaid> billPaidProjectList, Cheque cheque, ChequeReprint chequeReprint) {

        try {
            connection.setAutoCommit(false);

            for (InvOtherBillPaid billPaid : billPaidProjectList) {
                otherBillPaidDao = new InvOtherBillPaidDao();
                otherBillPaidDao.save(billPaid, connection);
            }
            chequeDao = new ChequeDao();
            chequeDao.update(cheque, connection);

            chequeReprintDao = new ChequeReprintDao();
            chequeReprintDao.save(chequeReprint, connection);

            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new UncaughtError("Error...:BillPaymentDao...:externalsBillPayment..Rollback" + e);
            }

            throw new UncaughtError("Error...:BillPaymentDao...:externalsBillPayment..." + e);
        }

        return true;
    }

    public void resetCheque(ChequeReprint chequeReprint) {
        try {
            connection.setAutoCommit(false);

            Cheque cheque = new Cheque();
            cheque.setChequeId(chequeReprint.getChequeNoId());
            cheque.setChequeAmount(0.0);
            cheque.setChequeDate(null);
            cheque.setChequeNo(chequeReprint.getChequeNo());
            cheque.setExpenseDate(null);
            cheque.setExpenseMode("A");

            if (chequeReprint.getBillTp().equals("SUP")) {
                invBillPaidDao = new InvBillPaidDao();
                invBillPaidDao.delete(chequeReprint.getChequeNoId(), connection);

                chequeReprintDao = new ChequeReprintDao();
                chequeReprintDao.delete(chequeReprint.getChequeNoId(), connection);

                chequeDao = new ChequeDao();
                chequeDao.update(cheque, connection);
            } else if (chequeReprint.getBillTp().equals("SUB")) {
                subContractorBillPaidDao = new InvSubContractorBillPaidDao();
                subContractorBillPaidDao.delete(chequeReprint.getChequeNoId(), connection);

                chequeReprintDao = new ChequeReprintDao();
                chequeReprintDao.delete(chequeReprint.getChequeNoId(), connection);

                chequeDao = new ChequeDao();
                chequeDao.update(cheque, connection);
            } else if (chequeReprint.getBillTp().equals("EMP")) {
                otherBillPaidDao = new InvOtherBillPaidDao();
                otherBillPaidDao.delete(chequeReprint.getChequeNoId(), connection);

                chequeReprintDao = new ChequeReprintDao();
                chequeReprintDao.delete(chequeReprint.getChequeNoId(), connection);

                chequeDao = new ChequeDao();
                chequeDao.update(cheque, connection);
            } else if (chequeReprint.getBillTp().equals("EXT")) {
                otherBillPaidDao = new InvOtherBillPaidDao();
                otherBillPaidDao.delete(chequeReprint.getChequeNoId(), connection);

                chequeReprintDao = new ChequeReprintDao();
                chequeReprintDao.delete(chequeReprint.getChequeNoId(), connection);

                chequeDao = new ChequeDao();
                chequeDao.update(cheque, connection);
            }

            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new UncaughtError("Error...:BillPaymentDao...:resetCheque..Rollback" + e);
            }

            throw new UncaughtError("Error...:BillPaymentDao...:resetCheque..." + e);
        }

    }

    public void supplierAitPayment(List<AccAitPay> paymentList, List<InvOtherBillPaid> aitProjectList, VoucherMaster voucherMaster, List<VoucherDetails> voucherDetailsList, Cheque cheque, ChequeReprint chequeReprint) {

        try {
            connection.setAutoCommit(false);

            voucherMasterDao = new VoucherMasterDao();
            voucherMasterDao.save(voucherMaster, connection);

            for (VoucherDetails details : voucherDetailsList) {
                voucherDetailsDao = new VoucherDetailsDao();
                voucherDetailsDao.save(details, connection);
            }

            for (AccAitPay ac : paymentList) {
                accAitPayDao = new AccAitPayDao();
                accAitPayDao.save(ac, connection);
            }

            for (InvOtherBillPaid billPaid : aitProjectList) {
                otherBillPaidDao = new InvOtherBillPaidDao();
                otherBillPaidDao.save(billPaid, connection);
            }

            chequeDao = new ChequeDao();
            chequeDao.update(cheque, connection);

            chequeReprintDao = new ChequeReprintDao();
            chequeReprintDao.save(chequeReprint, connection);

            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new UncaughtError("Error...:BillPaymentDao...:supplierAitPayment..Rollback" + e);
            }

            throw new UncaughtError("Error...:BillPaymentDao...:supplierAitPayment..." + e);
        }
    }

}
