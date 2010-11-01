package com.tw.oobc.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingWaiter implements Manager{

    protected List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    protected ParkingLotSelector parkingLotSelector;
    private String name;

    public ParkingWaiter(ParkingLotSelector parkingLotSelector){

        this.parkingLotSelector = parkingLotSelector;
    }

    public ParkingWaiter() {
        parkingLotSelector = new ParkingLotSelectorPreferAvailable();
    }

    public ParkingWaiter(String name) {

        this.name = name;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public void park() throws NoAvailableSpotsException {
        ParkingLot parkingTo = getParkingTo();
        parkingTo.park();
    }

    public ParkingLot getParkingTo() {
        int indexOfParkingTo = 0;
        for(int i = 0; i < parkingLots.size() - 1; i++){
            if (parkingLotSelector.preferNext(parkingLots.get(i), parkingLots.get(i + 1))){
                indexOfParkingTo = 1 + i;
            }
        }

        return parkingLots.get(indexOfParkingTo);
    }


    public String printReport(Reporter reporter){
        return reporter.printParkingWaiter(name, parkingLots);
    }
}
