package com.tw.oobc.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager implements Manager {

    private List<Manager> managers = new ArrayList<Manager>();
    private String name;

    public ParkingManager(String name) {

        this.name = name;
    }

    public void add(Manager manager) {
        managers.add(manager);
    }

    public void park() throws NoAvailableSpotsException {
        for (Manager manager : managers) {
            try {
                manager.park();
                return;
            } catch (NoAvailableSpotsException ignored) {
            }
        }
        throw new NoAvailableSpotsException();
    }

    public String printReport(Reporter reporter){
        return reporter.printParkingManger(name, managers);
    }


}
