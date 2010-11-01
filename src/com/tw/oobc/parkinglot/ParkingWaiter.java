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


    public String printReport(int indent) {
        StringBuilder report = new StringBuilder();
        report.append(name);
        report.append("\n");
        for (ParkingLot manager : parkingLots) {
            report.append(getSpaces(indent + 2));
            report.append(manager.printReport());
        }
        return report.toString();
    }

    public String getSpaces(int indent){
        StringBuilder spacesBuilder = new StringBuilder();
        for (int i = 0; i < indent; i++){
            spacesBuilder.append(" ");
        }
        return spacesBuilder.toString();
    }
}
