package com.tw.oobc.parkinglot;

import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class ParkingWaiterTest {
    @Test
    public void can_assign_parkinglots_to_the_waiter(){
        ParkingWaiter parkingWaiter = new ParkingWaiter(new ParkingLotSelectorPreferAvailable());
        parkingWaiter.addParkingLot(new ParkingLot(1));
        assertThat(parkingWaiter.getNumberOfParkingLots(), is(1));
        parkingWaiter.addParkingLot(new ParkingLot(2));
        assertThat(parkingWaiter.getNumberOfParkingLots(), is(2));
    }

    @Test
    public void test_waiter_can_park_a_car_for_you() throws NoAvailableSpotsException {
        ParkingWaiter parkingWaiter = new ParkingWaiter(new ParkingLotSelectorPreferAvailable());
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingWaiter.addParkingLot(parkingLot1);
        parkingWaiter.park();
        assertThat(parkingLot1.getAvailableSpots(), is(0));
    }

    @Test
    public void should_throw_exception_when_all_parking_lots_are_full(){
        ParkingWaiter parkingWaiter = new ParkingWaiter(new ParkingLotSelectorPreferAvailable());
        ParkingLot parkingLot1 = new ParkingLot(0);
        parkingWaiter.addParkingLot(parkingLot1);
        try {
            parkingWaiter.park();
            fail("Should throw exception");
        } catch (NoAvailableSpotsException e) {
        }

        assertThat(parkingLot1.getAvailableSpots(), is(0));
    }

    @Test
    public void should_only_park_into_most_empty_lot() throws NoAvailableSpotsException {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        ParkingWaiter parkingWaiter = new ParkingWaiter(new ParkingLotSelectorPreferAvailable());
        parkingWaiter.addParkingLot(parkingLot1);
        parkingWaiter.addParkingLot(parkingLot2);
        parkingWaiter.park();
        assertThat(parkingLot1.getAvailableSpots(), is(1));
        assertThat(parkingLot2.getAvailableSpots(), is(1));
    }


}
