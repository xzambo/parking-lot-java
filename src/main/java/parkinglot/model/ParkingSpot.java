package parkinglot.model;

public class ParkingSpot {
    private final int id;
    private boolean isOccupied;
    private final Vehicle vehicleType;

    public ParkingSpot(int id, Vehicle vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
        isOccupied = false;
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicleType() {
        return vehicleType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void park() {
        isOccupied = true;
    }

    public void vacate() {
        isOccupied = false;
    }
}
