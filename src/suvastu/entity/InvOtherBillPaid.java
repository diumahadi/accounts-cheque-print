package suvastu.entity;

import java.util.Date;
import suvastu.model.AccountCheque;
import suvastu.model.AppUser;
import suvastu.model.Project;


public class InvOtherBillPaid {
    
    private String accCode;
    private String empId;
    private String externalName;
    private Date chequeDate;
    private Date paidDate;
    private Double paidAmount;
    private AccountCheque accountCheque;
    private String mrNo;
    private Project project;
    private String notes;
    private AppUser appUser;
    
    //emp_sc_code,emp_id,external_name,cheque_date,pay_date,amount
    //,cheque_no,mr_no,payment_project,note,paid_by
    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
    }

    public Date getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(Date chequeDate) {
        this.chequeDate = chequeDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public AccountCheque getAccountCheque() {
        return accountCheque;
    }

    public void setAccountCheque(AccountCheque accountCheque) {
        this.accountCheque = accountCheque;
    }

    public String getMrNo() {
        return mrNo;
    }

    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "InvOtherBillPaid{" + "accCode=" + accCode + ", empId=" + empId + ", externalName=" + externalName + ", chequeDate=" + chequeDate + ", paidDate=" + paidDate + ", paidAmount=" + paidAmount + ", accountCheque=" + accountCheque + ", mrNo=" + mrNo + ", project=" + project + ", notes=" + notes + ", appUser=" + appUser + '}';
    }
    
    
}
