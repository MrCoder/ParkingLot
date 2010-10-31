package com.tw.oobc.parkinglot;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class ParkingWaiterTest {
    @Test
    public void can_assign_parkinglots_to_the_waiter(){
        ParkingWaiter parkingWaiter = new ParkingWaiter();
        parkingWaiter.addParkingLot(new ParkingLot(1));
        assertThat(parkingWaiter.getNumberOfParkingLots(), is(1));
        parkingWaiter.addParkingLot(new ParkingLot(2));
        assertThat(parkingWaiter.getNumberOfParkingLots(), is(2));
    }

    @Test
    public void test_waiter_can_park_a_car_for_you() throws NoAvailableSpotsException {
        ParkingWaiter parkingWaiter = new ParkingWaiter();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingWaiter.addParkingLot(parkingLot1);
        parkingWaiter.park();
        assertThat(parkingLot1.getAvailableSpots(), is(0));
    }
}
