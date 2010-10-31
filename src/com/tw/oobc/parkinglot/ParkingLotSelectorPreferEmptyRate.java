package com.tw.oobc.parkinglot;

public class ParkingLotSelectorPreferEmptyRate implements ParkingLotSelector{
    public boolean preferNext(ParkingLot parkingLot, ParkingLot parkingLotNext) {
        return parkingLot.getEmptyRate() < parkingLotNext.getEmptyRate();
    }
}
