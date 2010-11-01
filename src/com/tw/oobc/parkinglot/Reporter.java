package com.tw.oobc.parkinglot;

import java.util.List;

public class Reporter {
    private int indent;

    public Reporter(int indent) {

        this.indent = indent;
    }

    public String printParkingManger(String name, List<Manager> managers) {

        StringBuilder report = new StringBuilder();
        report.append(name);
        report.append("\n");
        for (Manager manager : managers) {
            report.append(getSpaces(indent + 2));
            report.append(manager.printReport(new Reporter(indent + 2)));
        }
        return report.toString();
    }

    public String getSpaces(int indent) {
        StringBuilder spacesBuilder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            spacesBuilder.append(" ");
        }
        return spacesBuilder.toString();
    }

    public String printParkingWaiter(String name, List<ParkingLot> parkingLots) {

        StringBuilder report = new StringBuilder();
        report.append(name);
        report.append("\n");
        for (ParkingLot manager : parkingLots) {
            report.append(getSpaces(indent + 2));
            report.append(manager.printReport(new Reporter(0)));
        }
        return report.toString();
    }

    public String printParkingLot(String name, int available, int capacity) {
        return name+":"+available+"/"+capacity + "\n";
    }
}
