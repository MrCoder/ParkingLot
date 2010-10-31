package com.tw.oobc.parkinglot;

public class ParkingLotSelectorPreferAvailable implements ParkingLotSelector{

    public boolean preferNext(ParkingLot parkingLot, ParkingLot parkingLotNext) {
        return parkingLot.hasMoreAvailableSpaces(parkingLotNext);
    }

}
