package com.tw.oobc.parkinglot;

public class ParkingLot {
    private int capacity;
    private int available;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.available = capacity;
    }

    public int getAvailableSpots() {
        return available;
    }

    public void park() throws NoAvailableSpotsException {
        if (!hasAvailableSpaces()) throw new NoAvailableSpotsException();
        available--;
    }

    public void unPark() throws UnParkingAnEmptyParkingLotException {
        if(!hasParkedCars()) throw new UnParkingAnEmptyParkingLotException();
        available++;
    }

    private boolean hasParkedCars() {
        return capacity - available > 0;
    }

    public boolean hasAvailableSpaces() {
        return available > 0;
    }

    boolean hasMoreAvailableSpaces(ParkingLot parkingLotNext) {
        return available < parkingLotNext.available;
    }

    boolean hasHigherEmptyRate(ParkingLot parkingLotNext) {
        return available / (float) capacity < parkingLotNext.available / (float) parkingLotNext.capacity;
    }
}
