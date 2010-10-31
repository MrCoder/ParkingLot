package com.tw.oobc.parkinglot;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ParkingLotTest {
    @Test
    public void can_create_a_parking_lot_with_its_capacity(){
        ParkingLot parkingLot = new ParkingLot(0);
        assertThat(parkingLot.getCapacity(), is(0));
    }

    @Test
    public void can_know_available_parking_spots(){
        ParkingLot parkingLot = new ParkingLot(0);
        assertThat(parkingLot.getAvailableSpots(), is(0));

        ParkingLot parkingLot1 = new ParkingLot(1);
        assertThat(parkingLot1.getAvailableSpots(), is(1));
    }

    @Test
    public void should_decrease_available_number_after_parking(){
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park();
        assertThat(parkingLot.getCapacity(), is(1));
        assertThat(parkingLot.getAvailableSpots(), is(0));
    }

}
