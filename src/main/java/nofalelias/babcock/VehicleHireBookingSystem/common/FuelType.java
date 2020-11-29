package nofalelias.babcock.VehicleHireBookingSystem.common;

public enum FuelType {
    PETROL("Petrol"), DIESEL("Diesel");
    private String displayType;
    public String getDisplayType() {
        return this.displayType;
    }

    FuelType(String displayType) {
        this.displayType = displayType;
    }
}
