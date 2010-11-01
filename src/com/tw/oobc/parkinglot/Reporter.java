package com.tw.oobc.parkinglot;

import java.util.List;

public class Reporter {
    private int indent;

    public String printParkingManager(String name, List<Manager> managers) {
        StringBuilder report = appendNameAndReturn(name);
        for (Manager manager : managers) {
            indent += 2;
            report.append(manager.printReport(this));
            indent -= 2;
        }
        return report.toString();
    }

    public String printParkingWaiter(String name, List<ParkingLot> parkingLots) {
        StringBuilder report = appendNameAndReturn(name);
        for (ParkingLot parkingLot : parkingLots) {
            indent += 2;
            report.append(parkingLot.printReport(this));
            indent -= 2;
        }
        return report.toString();
    }

    private StringBuilder appendNameAndReturn(String name) {
        StringBuilder report = new StringBuilder();
        report.append(getSpaces(indent));
        report.append(name);
        report.append("\n");
        return report;
    }

    public String printParkingLot(String name, int available, int capacity) {
        return getSpaces(indent) + name+":"+available+"/"+capacity + "\n";
    }

    private String getSpaces(int indent) {
        StringBuilder spacesBuilder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            spacesBuilder.append(" ");
        }
        return spacesBuilder.toString();
    }
}
