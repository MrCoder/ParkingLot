package com.tw.oobc.parkinglot;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class ParkingLotTest {
    private ParkingLotListener parkingLotListener = mock(ParkingLotListener.class);
    private final String PARKING_LOT_NAME = "TW Parking Lot";

    @Test
    public void should_be_able_to_park_a_car() throws NoAvailableSpotsException {
        ParkingLot parkingLot = prepare_a_parking_lot_and_its_listener(1);

        parkingLot.park();

        verify(parkingLotListener, times(1)).parkingIn(PARKING_LOT_NAME);
    }

    @Test
    public void should_not_be_able_to_park_second_car() throws NoAvailableSpotsException {
        ParkingLot parkingLot = prepare_a_parking_lot_and_its_listener(1);

        parkingLot.park();
        verify(parkingLotListener, times(1)).parkingIn(PARKING_LOT_NAME);
        try {
            parkingLot.park();
            fail("Should throw an exception.");
        } catch (NoAvailableSpotsException e) {

        }
        verify(parkingLotListener, times(1)).parkingIn(PARKING_LOT_NAME);
    }

    @Test
    public void should_throw_exception_when_parking_when_no_available_spots() {
        ParkingLot parkingLot = prepare_a_parking_lot_and_its_listener(0);
        try {
            parkingLot.park();
            fail("Should throw an exception.");
        } catch (NoAvailableSpotsException e) {

        }
        verify(parkingLotListener, times(0)).parkingIn(PARKING_LOT_NAME);
    }

    private ParkingLot prepare_a_parking_lot_and_its_listener(int capacity) {
        ParkingLot parkingLot = new ParkingLot(PARKING_LOT_NAME, capacity);
        parkingLot.setListener(parkingLotListener);
        return parkingLot;
    }

    @Test
    public void should_increase_available_number_after_unParking() throws NoAvailableSpotsException, UnParkingAnEmptyParkingLotException {
        ParkingLot parkingLot = prepare_a_parking_lot_and_its_listener(1);
        parkingLot.park();
        parkingLot.unPark();
        verify(parkingLotListener, times(1)).parkingIn(PARKING_LOT_NAME);
        verify(parkingLotListener, times(1)).unParkingFrom(PARKING_LOT_NAME);
    }

    @Test
    public void should_throw_exception_when_unParking_from_an_empty_parkinglot() {
        ParkingLot parkingLot = prepare_a_parking_lot_and_its_listener(0);
        try {
            parkingLot.unPark();
            fail("Should throw exception");
        } catch (UnParkingAnEmptyParkingLotException e) {
        }
        verify(parkingLotListener, times(0)).unParkingFrom(anyString());
    }

}
