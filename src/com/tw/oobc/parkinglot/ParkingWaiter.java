package com.tw.oobc.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingWaiter{
    protected List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
    protected ParkingLotSelector parkingLotSelector;

    public ParkingWaiter(ParkingLotSelector parkingLotSelector){

        this.parkingLotSelector = parkingLotSelector;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public void park() throws NoAvailableSpotsException {
        ParkingLot parkingTo = getParkingTo();
        parkingTo.park();
    }

    protected ParkingLot getParkingTo() {
        int indexOfParkingTo = 0;
        for(int i = 0; i < parkingLots.size() - 1; i++){
            if (parkingLotSelector.preferNext(parkingLots.get(i), parkingLots.get(i + 1))){
                indexOfParkingTo = 1 + i;
            }
        }

        return parkingLots.get(indexOfParkingTo);
    }

}
