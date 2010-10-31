package com.tw.oobc.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingWaiter {
    private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public int getNumberOfParkingLots() {
        return parkingLots.size();
    }

    public void park() throws NoAvailableSpotsException {
        for (ParkingLot parkingLot : parkingLots) {
            if(parkingLot.getAvailableSpots() > 0) parkingLot.park();
        }
        
    }
}
