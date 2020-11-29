package nofalelias.babcock.VehicleHireBookingSystem.common;

public enum CustomerType {
    INDIVIUAL("Indiviusal"), COMPANY("Company");
    private String displayType;
    public String getDisplayType() {
        return this.displayType;
    }

    CustomerType(String displayType) {
        this.displayType = displayType;
    }
}
