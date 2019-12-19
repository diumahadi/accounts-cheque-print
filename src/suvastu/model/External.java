package suvastu.model;

public class External {
    private String externalName;

    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
    }

    @Override
    public String toString() {
        return "External{" + "externalName=" + externalName + '}';
    }
    
    
}
