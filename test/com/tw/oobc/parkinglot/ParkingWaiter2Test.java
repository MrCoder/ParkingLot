package com.tw.oobc.parkinglot;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ParkingWaiter2Test {
    @Test
    public void should_park_to_the_parking_lot_with_highest_empty_rate() throws NoAvailableSpotsException {
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLot2.park();
        ParkingLot parkingLot5 = new ParkingLot(5);
        parkingLot5.park();
        parkingLot5.park();
        parkingLot5.park();
        ParkingWaiter2 parkingWaiter2 = new ParkingWaiter2();
        parkingWaiter2.addParkingLot(parkingLot2);
        parkingWaiter2.addParkingLot(parkingLot5);
        parkingWaiter2.park();
        assertThat(parkingLot2.getAvailableSpots(), is(0));
        assertThat(parkingLot5.getAvailableSpots(), is(2));

    }
}
