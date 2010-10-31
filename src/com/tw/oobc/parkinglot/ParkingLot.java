package com.tw.oobc.parkinglot;

public class ParkingLot {
    private int capacity;
    private int available;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.available = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSpots() {
        return available;
    }

    public void park() throws NoAvailableSpotsException {
        if (available <= 0) throw new NoAvailableSpotsException();
        available--;
    }

    public void unPark() throws UnParkingAnEmptyParkingLotException {
        if(isFull()) throw new UnParkingAnEmptyParkingLotException();
        available++;
    }

    private boolean isFull() {
        return available == capacity;
    }

    float getEmptyRate() {
        return getAvailableSpots()/(float ) getCapacity();
    }
}
