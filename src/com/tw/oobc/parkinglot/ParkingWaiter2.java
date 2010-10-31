package com.tw.oobc.parkinglot;

public class ParkingWaiter2 extends ParkingWaiter{
    public void park() throws NoAvailableSpotsException {
        int indexOfParkingTo = 0;
        for(int i = 0; i < parkingLots.size() - 1; i++){
            ParkingLot parkingLot_i = parkingLots.get(i);
            ParkingLot parkingLot_i_plus = parkingLots.get(i + 1);
            if (parkingLot_i.getAvailableSpots()/(float ) parkingLot_i.getCapacity()
                    < parkingLot_i_plus.getAvailableSpots() / (float) parkingLot_i_plus.getCapacity()){
                indexOfParkingTo = 1 + i;
            }
        }
        parkingLots.get(indexOfParkingTo).park();

    }

}
