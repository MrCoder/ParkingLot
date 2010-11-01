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

    public String printReport(int indent) {
        StringBuilder report = new StringBuilder();
        report.append(name);
        report.append("\n");
        for (Manager manager : managers) {
            report.append(getSpaces(indent + 2));
            report.append(manager.printReport(indent + 2));
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
