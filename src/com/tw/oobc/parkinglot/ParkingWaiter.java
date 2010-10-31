package com.tw.oobc.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingWaiter {
    protected List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public int getNumberOfParkingLots() {
        return parkingLots.size();
    }

    public void park() throws NoAvailableSpotsException {
        int indexOfParkingTo = 0;
        for(int i = 0; i < parkingLots.size() - 1; i++){
            if (parkingLots.get(i).getAvailableSpots() < parkingLots.get(i + 1).getAvailableSpots()){
                indexOfParkingTo = 1 + i;
            }
        }

        parkingLots.get(indexOfParkingTo).park();
    }
}
