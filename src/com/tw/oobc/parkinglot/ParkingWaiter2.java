package com.tw.oobc.parkinglot;

public class ParkingWaiter2 extends ParkingWaiter{

    private ParkingLotSelector parkingLotSelector = new ParkingLotSelectorPreferEmptyRate();
    public boolean preferNext(ParkingLot parkingLot_i, ParkingLot parkingLot_i_next) {
        return parkingLotSelector.preferNext(parkingLot_i, parkingLot_i_next);
    }

}
