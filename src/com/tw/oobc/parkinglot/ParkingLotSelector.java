package com.tw.oobc.parkinglot;

public interface ParkingLotSelector {
    boolean preferNext(ParkingLot parkingLot, ParkingLot parkingLotNext);
}
