package suvastu.model;

import java.sql.Timestamp;
import java.util.Date;

public class AccountCheque {
    

    private Long chequeId;
    private Integer bankId;
    private String bankName;
    private String bankShortName;
    private Integer accountId;
    private String accountNumber;
    private String branch;
    private String bankCaCode;
    private String chequeNo;
    private Date chequeDate;
    private Date expenseDate;
    private Double chequeAmount;
    private String expenseMode;
    private Timestamp createdAt;

    public Long getChequeId() {
        return chequeId;
    }

    public void setChequeId(Long chequeId) {
        this.chequeId = chequeId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBankCaCode() {
        return bankCaCode;
    }

    public void setBankCaCode(String bankCaCode) {
        this.bankCaCode = bankCaCode;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public Date getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(Date chequeDate) {
        this.chequeDate = chequeDate;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Double getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(Double chequeAmount) {
        this.chequeAmount = chequeAmount;
    }

    public String getExpenseMode() {
        return expenseMode;
    }

    public void setExpenseMode(String expenseMode) {
        this.expenseMode = expenseMode;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return bankShortName+"-"+chequeNo;
        //return "AccountCheque{" + "chequeId=" + chequeId + ", bankId=" + bankId + ", bankName=" + bankName + ", bankShortName=" + bankShortName + ", accountId=" + accountId + ", accountNumber=" + accountNumber + ", branch=" + branch + ", bankCaCode=" + bankCaCode + ", chequeNo=" + chequeNo + ", chequeDate=" + chequeDate + ", expenseDate=" + expenseDate + ", chequeAmount=" + chequeAmount + ", expenseMode=" + expenseMode + ", createdAt=" + createdAt + '}';
    }

    
}
