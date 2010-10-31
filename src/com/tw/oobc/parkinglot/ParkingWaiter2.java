package com.tw.oobc.parkinglot;

public class ParkingWaiter2 extends ParkingWaiter{

    protected boolean preferNext(ParkingLot parkingLot_i, ParkingLot parkingLot_i_next) {
        return parkingLot_i.getEmptyRate()
                < parkingLot_i_next.getEmptyRate();
    }

}
