package com.tw.oobc.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingWaiter{
    protected List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
    private ParkingLotSelectorPreferAvailable parkingLotSelectorPreferAvailable = new ParkingLotSelectorPreferAvailable();

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public int getNumberOfParkingLots() {
        return parkingLots.size();
    }

    public void park() throws NoAvailableSpotsException {
        ParkingLot parkingTo = getParkingTo();
        parkingTo.park();
    }

    protected ParkingLot getParkingTo() {
        int indexOfParkingTo = 0;
        for(int i = 0; i < parkingLots.size() - 1; i++){
            if (preferNext(parkingLots.get(i), parkingLots.get(i + 1))){
                indexOfParkingTo = 1 + i;
            }
        }

        return parkingLots.get(indexOfParkingTo);
    }

    public boolean preferNext(ParkingLot parkingLot, ParkingLot parkingLotNext) {
        return parkingLotSelectorPreferAvailable.preferNext(parkingLot, parkingLotNext);
    }
}
