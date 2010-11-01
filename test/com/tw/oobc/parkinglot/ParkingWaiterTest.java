package com.tw.oobc.parkinglot;

import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.mockito.Mockito.*;

public class ParkingWaiterTest {
    private ParkingLotListener parkingLotListener = mock(ParkingLotListener.class);
    private final String TW_PARKING_LOT = "TW Parking Lot";


    private ParkingLot preparing_parking_lot(String name, int capacity) {
        ParkingLot parkingLot1 = new ParkingLot(name, capacity);
        parkingLot1.setListener(parkingLotListener);
        return parkingLot1;
    }

    @Test
    public void test_waiter_can_park_a_car_for_you() throws NoAvailableSpotsException {
        ParkingWaiter parkingWaiter = new ParkingWaiter(new ParkingLotSelectorPreferAvailable());
        ParkingLot parkingLot1 = preparing_parking_lot(TW_PARKING_LOT, 1);
        parkingWaiter.addParkingLot(parkingLot1);
        parkingWaiter.park();
        verify(parkingLotListener, times(1)).parkingIn(TW_PARKING_LOT);
    }

    @Test
    public void should_throw_exception_when_all_parking_lots_are_full() {
        ParkingWaiter parkingWaiter = new ParkingWaiter(new ParkingLotSelectorPreferAvailable());
        ParkingLot parkingLot1 = preparing_parking_lot("TW Parking Lot", 0);
        parkingWaiter.addParkingLot(parkingLot1);
        try {
            parkingWaiter.park();
            fail("Should throw exception");
        } catch (NoAvailableSpotsException e) {
        }
        verify(parkingLotListener, times(0)).parkingIn(anyString());
    }

    @Test
    public void should_only_park_into_most_empty_lot() throws NoAvailableSpotsException {
        ParkingLot parkingLot1 = preparing_parking_lot("TW Parking Lot1", 1);
        ParkingLot parkingLot2 = preparing_parking_lot("TW Parking Lot2", 2);
        ParkingWaiter parkingWaiter = new ParkingWaiter(new ParkingLotSelectorPreferAvailable());
        parkingWaiter.addParkingLot(parkingLot1);
        parkingWaiter.addParkingLot(parkingLot2);
        parkingWaiter.park();
        verify(parkingLotListener, times(1)).parkingIn("TW Parking Lot2");
        parkingWaiter.park();
        verify(parkingLotListener, times(1)).parkingIn("TW Parking Lot1");
        parkingWaiter.park();
        verify(parkingLotListener, times(2)).parkingIn("TW Parking Lot2");
    }


    @Test
    public void should_park_to_the_parking_lot_with_highest_empty_rate() throws NoAvailableSpotsException {
        ParkingLot parkingLot2 = preparing_parking_lot("TW Parking Lot2", 2);
        ParkingLot parkingLot5 = preparing_parking_lot("TW Parking Lot5", 5);
        ParkingWaiter parkingWaiter2 = new ParkingWaiter(new ParkingLotSelectorPreferEmptyRate());
        parkingWaiter2.addParkingLot(parkingLot2);
        parkingWaiter2.addParkingLot(parkingLot5);

        parkingWaiter2.park(); //into 2
        verify(parkingLotListener, times(1)).parkingIn("TW Parking Lot2");
        parkingWaiter2.park(); //into 5
        verify(parkingLotListener, times(1)).parkingIn("TW Parking Lot5");
        parkingWaiter2.park(); //into 5
        verify(parkingLotListener, times(2)).parkingIn("TW Parking Lot5");
        parkingWaiter2.park(); //into 5
        verify(parkingLotListener, times(3)).parkingIn("TW Parking Lot5");
        parkingWaiter2.park(); //into 2
        verify(parkingLotListener, times(2)).parkingIn("TW Parking Lot2");
        parkingWaiter2.park(); //into 5
        verify(parkingLotListener, times(4)).parkingIn("TW Parking Lot5");
        parkingWaiter2.park(); //into 5
        verify(parkingLotListener, times(5)).parkingIn("TW Parking Lot5");
    }
}
