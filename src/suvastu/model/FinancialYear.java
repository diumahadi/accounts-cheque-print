package suvastu.model;

import java.util.Date;

public class FinancialYear {
    
    private Integer finId;
    private Integer finNo;
    private Integer companyId;
    private String companyName;
    private Date startDate;
    private Date endDate;

    public Integer getFinId() {
        return finId;
    }

    public void setFinId(Integer finId) {
        this.finId = finId;
    }

    public Integer getFinNo() {
        return finNo;
    }

    public void setFinNo(Integer finNo) {
        this.finNo = finNo;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "FinancialYear{" + "finId=" + finId + ", finNo=" + finNo + ", companyId=" + companyId + ", companyName=" + companyName + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
    
    
}
