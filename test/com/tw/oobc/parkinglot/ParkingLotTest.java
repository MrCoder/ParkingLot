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
}
