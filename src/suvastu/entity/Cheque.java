package suvastu.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import suvastu.model.AppUser;


public class Cheque {
    
    private Long chequeId;
    private String chequeNo;
    private Date chequeDate;
    private Date expenseDate;
    private String expenseMode;
    private Double chequeAmount;
    private AppUser appUser;
    private Timestamp createdAt;

    public Long getChequeId() {
        return chequeId;
    }

    public void setChequeId(Long chequeId) {
        this.chequeId = chequeId;
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

    public String getExpenseMode() {
        return expenseMode;
    }

    public void setExpenseMode(String expenseMode) {
        this.expenseMode = expenseMode;
    }

    public Double getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(Double chequeAmount) {
        this.chequeAmount = chequeAmount;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.chequeId);
        hash = 29 * hash + Objects.hashCode(this.chequeNo);
        hash = 29 * hash + Objects.hashCode(this.expenseMode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cheque other = (Cheque) obj;
        if (!Objects.equals(this.chequeId, other.chequeId)) {
            return false;
        }
        if (!Objects.equals(this.chequeNo, other.chequeNo)) {
            return false;
        }
        if (!Objects.equals(this.expenseMode, other.expenseMode)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Cheque{" + "chequeId=" + chequeId + ", chequeNo=" + chequeNo + ", chequeDate=" + chequeDate + ", expenseDate=" + expenseDate + ", expenseMode=" + expenseMode + ", chequeAmount=" + chequeAmount + ", appUser=" + appUser + ", createdAt=" + createdAt + '}';
    }

    
}
