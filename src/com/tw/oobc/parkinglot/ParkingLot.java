package com.tw.oobc.parkinglot;

public class ParkingLot {
    private String name;
    private int capacity;
    private int available;
    private ParkingLotListener listener;

    public ParkingLot(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.available = capacity;
    }

    public void setListener(ParkingLotListener listener) {
        this.listener = listener;
    }

    public void park() throws NoAvailableSpotsException {
        if (!hasAvailableSpaces()) throw new NoAvailableSpotsException();
        available--;
        if (hasListener())
            listener.parkingIn(name);
    }

    public void unPark() throws UnParkingAnEmptyParkingLotException {
        if (!hasParkedCars()) throw new UnParkingAnEmptyParkingLotException();
        available++;
        if (hasListener()) listener.unParkingFrom(name);
    }

    public boolean hasMoreAvailableSpaces(ParkingLot parkingLotNext) {
        return available < parkingLotNext.available;
    }

    public boolean hasHigherEmptyRate(ParkingLot parkingLotNext) {
        return available / (float) capacity < parkingLotNext.available / (float) parkingLotNext.capacity;
    }

    private boolean hasListener() {
        return listener != null;
    }

    private boolean hasParkedCars() {
        return capacity - available > 0;
    }

    private boolean hasAvailableSpaces() {
        return available > 0;
    }

    public String printReport(Reporter reporter){
        return reporter.printParkingLot(name, available, capacity);
    }

}
