package com.tw.oobc.parkinglot;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;

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
    public void should_decrease_available_number_after_parking() throws NoAvailableSpotsException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park();
        assertThat(parkingLot.getCapacity(), is(1));
        assertThat(parkingLot.getAvailableSpots(), is(0));
    }

    @Test
    public void should_throw_exception_when_parking_when_no_available_spots() {
        ParkingLot parkingLot = new ParkingLot(0);
        try {
            parkingLot.park();
            fail("Should throw an exception.");
        } catch (NoAvailableSpotsException e) {

        }
        assertThat(parkingLot.getAvailableSpots(), is(0));
    }

    @Test
    public void should_increase_available_number_after_unParking() throws NoAvailableSpotsException, UnParkingAnEmptyParkingLotException {
        ParkingLot parkingLot = given_a_parking_lot_capacity_of_1_is_full();
        parkingLot.unPark();
        assertThat(parkingLot.getAvailableSpots(), is(1));
    }

    private ParkingLot given_a_parking_lot_capacity_of_1_is_full() throws NoAvailableSpotsException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park();
        assertThat(parkingLot.getAvailableSpots(), is(0));
        return parkingLot;
    }

    @Test
    public void should_throw_exception_when_unParking_from_an_empty_parkinglot() {
        ParkingLot parkingLot = new ParkingLot(0);
        try {
            parkingLot.unPark();
            fail("Should throw exception");
        } catch (UnParkingAnEmptyParkingLotException e) {
        }
        assertThat(parkingLot.getAvailableSpots(), is(0));
    }

}
