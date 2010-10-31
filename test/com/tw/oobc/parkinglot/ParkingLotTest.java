package com.tw.oobc.parkinglot;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;

public class ParkingLotTest {

    @Test
    public void parking_lot_with_capacity_0_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);
        assertThat(parkingLot.hasAvailableSpaces(), is(false));

        ParkingLot parkingLot1 = new ParkingLot(1);
        assertThat(parkingLot1.hasAvailableSpaces(), is(true));
    }

    @Test
    public void should_be_full_after_parking() throws NoAvailableSpotsException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park();
        assertThat(parkingLot.hasAvailableSpaces(), is(false));
    }

    @Test
    public void should_throw_exception_when_parking_when_no_available_spots() {
        ParkingLot parkingLot = new ParkingLot(0);
        try {
            parkingLot.park();
            fail("Should throw an exception.");
        } catch (NoAvailableSpotsException e) {

        }
        assertThat(parkingLot.hasAvailableSpaces(), is(false));
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
