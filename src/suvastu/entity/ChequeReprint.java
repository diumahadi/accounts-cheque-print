package suvastu.entity;

import java.util.Date;

public class ChequeReprint {
    
    private String chequeHtml;
    private Date chequeDate;
    private Date printDate;
    private String personName;
    private String chequeAmountInWord;
    private Double chequeAmount;
    private String bankShortCode;
    private String isAccountPay;
    private String chequeNo;
    private String billTp;
    private Long chequeNoId;
    private String note;

    public String getChequeHtml() {
        return chequeHtml;
    }

    public void setChequeHtml(String chequeHtml) {
        this.chequeHtml = chequeHtml;
    }

    public Date getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(Date chequeDate) {
        this.chequeDate = chequeDate;
    }

    public Date getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getChequeAmountInWord() {
        return chequeAmountInWord;
    }

    public void setChequeAmountInWord(String chequeAmountInWord) {
        this.chequeAmountInWord = chequeAmountInWord;
    }

    public Double getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(Double chequeAmount) {
        this.chequeAmount = chequeAmount;
    }

    public String getBankShortCode() {
        return bankShortCode;
    }

    public void setBankShortCode(String bankShortCode) {
        this.bankShortCode = bankShortCode;
    }

    public String getIsAccountPay() {
        return isAccountPay;
    }

    public void setIsAccountPay(String isAccountPay) {
        this.isAccountPay = isAccountPay;
    }

    

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getBillTp() {
        return billTp;
    }

    public void setBillTp(String billTp) {
        this.billTp = billTp;
    }

    public Long getChequeNoId() {
        return chequeNoId;
    }

    public void setChequeNoId(Long chequeNoId) {
        this.chequeNoId = chequeNoId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ChequeReprint{" + "chequeHtml=" + chequeHtml + ", chequeDate=" + chequeDate + ", printDate=" + printDate + ", personName=" + personName + ", chequeAmountInWord=" + chequeAmountInWord + ", chequeAmount=" + chequeAmount + ", bankShortCode=" + bankShortCode + ", isAccountPay=" + isAccountPay + ", chequeNo=" + chequeNo + ", billTp=" + billTp + ", chequeNoId=" + chequeNoId + ", note=" + note + '}';
    }
    
    
    
}
