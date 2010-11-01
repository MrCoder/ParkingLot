package com.tw.oobc.parkinglot;

public interface Manager {
    void park() throws NoAvailableSpotsException;

    String printReport(int indent);
}
