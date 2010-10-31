package com.tw.oobc.parkinglot;

public class ParkingWaiter2 extends ParkingWaiter{
    public void park() throws NoAvailableSpotsException {
        int indexOfParkingTo = 0;
        for(int i = 0; i < parkingLots.size() - 1; i++){
            ParkingLot parkingLot_i = parkingLots.get(i);
            ParkingLot parkingLot_i_next = parkingLots.get(i + 1);
            if (parkingLot_i.getEmptyRate()
                    < parkingLot_i_next.getEmptyRate()){
                indexOfParkingTo = 1 + i;
            }
        }
        parkingLots.get(indexOfParkingTo).park();

    }

}
