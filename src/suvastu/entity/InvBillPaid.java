package suvastu.entity;

import java.util.Date;
import suvastu.model.AccountCheque;
import suvastu.model.AppUser;
import suvastu.model.Project;
import suvastu.model.Supplier;

public class InvBillPaid {
    
    private Supplier supplier;
    private Project project;
    private String  paymentMode;
    private AccountCheque cheque;
    private Date chequeDate;
    private Double paidAmount;
    private Double aitAmount;
    private Date payDate;
    private AppUser appUser;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public AccountCheque getCheque() {
        return cheque;
    }

    public void setCheque(AccountCheque cheque) {
        this.cheque = cheque;
    }

    public Date getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(Date chequeDate) {
        this.chequeDate = chequeDate;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getAitAmount() {
        return aitAmount;
    }

    public void setAitAmount(Double aitAmount) {
        this.aitAmount = aitAmount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "InvBillPaid{" + "supplier=" + supplier + ", project=" + project + ", paymentMode=" + paymentMode + ", cheque=" + cheque + ", chequeDate=" + chequeDate + ", paidAmount=" + paidAmount + ", aitAmount=" + aitAmount + ", payDate=" + payDate + ", appUser=" + appUser + '}';
    }

    
    
    
}
