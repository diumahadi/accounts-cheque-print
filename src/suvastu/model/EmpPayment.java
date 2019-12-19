package suvastu.model;

public class EmpPayment {
    private Integer paymentId;
    private String empId;
    private String empName;
    private String designationTitle;
    private String deptName;
    private String accCode;
    private String codeTp;

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDesignationTitle() {
        return designationTitle;
    }

    public void setDesignationTitle(String designationTitle) {
        this.designationTitle = designationTitle;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public String getCodeTp() {
        return codeTp;
    }

    public void setCodeTp(String codeTp) {
        this.codeTp = codeTp;
    }

    @Override
    public String toString() {
        return "EmpPayment{" + "paymentId=" + paymentId + ", empId=" + empId + ", empName=" + empName + ", designationTitle=" + designationTitle + ", deptName=" + deptName + ", accCode=" + accCode + ", codeTp=" + codeTp + '}';
    }
    
    
}
