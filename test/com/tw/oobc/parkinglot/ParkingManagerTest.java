package com.tw.oobc.parkinglot;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ParkingManagerTest {
    private String PARKING_LOT_NAME = "Parking Lot 1";
    private ParkingLotListener parkingLotListener = mock(ParkingLotListener.class);

    @Test
    public void a_manager_can_add_manager() {
        ParkingManager parkingManager = new ParkingManager("Manager1");
        ParkingWaiter parkingManager2 = new ParkingWaiter();

        parkingManager.add(parkingManager2);
    }

    @Test
    public void a_manager_can_park_a_car() throws NoAvailableSpotsException {
        ParkingManager parkingManager = new ParkingManager("Manager1");
        ParkingWaiter parkingManager2 = new ParkingWaiter();

        parkingManager.add(parkingManager2);

        ParkingLot parkingLot = prepare_a_parking_lot_and_its_listener(1);
        parkingManager2.addParkingLot(parkingLot);
        parkingManager.park();

        verify(parkingLotListener, times(1)).parkingIn(PARKING_LOT_NAME);

    }

    @Test
    public void a_manager_can_park_a_car_to_second_lot_when_first_is_full() throws NoAvailableSpotsException {
        ParkingManager parkingManager = new ParkingManager("Manager1");
        ParkingWaiter parkingWaiter = new ParkingWaiter();
        ParkingWaiter parkingWaiter1 = new ParkingWaiter();

        parkingManager.add(parkingWaiter);
        parkingManager.add(parkingWaiter1);

        ParkingLot parkingLot = prepare_a_parking_lot_and_its_listener(1);
        ParkingLot parkingLot1 = prepare_a_parking_lot_and_its_listener(1);
        parkingWaiter.addParkingLot(parkingLot);
        parkingWaiter1.addParkingLot(parkingLot1);
        parkingManager.park();

        parkingManager.park();
        verify(parkingLotListener, times(2)).parkingIn(PARKING_LOT_NAME);

    }

    @Test
    public void should_print_report_for_one_manager() {
        ParkingManager parkingManager = new ParkingManager("Manager1");
        assertThat(parkingManager.printReport(new Reporter(0)), is("Manager1"));
    }

    @Test
    public void should_print_report_for_manager_and_waiter(){
        ParkingManager parkingManager = new ParkingManager("Manager1");
        ParkingWaiter waiter = new ParkingWaiter("waiter1");
        ParkingLot lot = new ParkingLot("lot1", 1);

        waiter.addParkingLot(lot);
        parkingManager.add(waiter);
        assertThat(parkingManager.printReport(new Reporter(0)), is("Manager1\n  waiter1\n    lot1:1/1"));
        System.out.println(parkingManager.printReport(new Reporter(0)));
    }

    @Test
    public void should_print_report_when_manager_has_manager(){
        ParkingManager roy = new ParkingManager("Roy");
        ParkingManager guoXiao = new ParkingManager("Guo Xiao");
        ParkingManager xuHao = new ParkingManager("Xu Hao");
        roy.add(guoXiao);
        roy.add(xuHao);
        ParkingWaiter peng = new ParkingWaiter("Xiao Peng");
        ParkingLot lot = new ParkingLot("lot1", 1);
        peng.addParkingLot(lot);
        guoXiao.add(peng);

        ParkingWaiter jian = new ParkingWaiter("Li Jian");
        ParkingLot lot2 = new ParkingLot("lot2", 1);
        jian.addParkingLot(lot2);
        xuHao.add(jian);
        System.out.println(roy.printReport(new Reporter(0)));
        assertThat(roy.printReport(new Reporter(0)), is(
                "Roy\n" +
                "  Guo Xiao\n" +
                "    Xiao Peng\n" +
                "      lot1:1/1\n" +
                "  Xu Hao\n" +
                "    Li Jian\n" +
                "      lot2:1/1\n"));
    }

    private ParkingLot prepare_a_parking_lot_and_its_listener(int capacity) {
        ParkingLot parkingLot = new ParkingLot(PARKING_LOT_NAME, capacity);
        parkingLot.setListener(parkingLotListener);
        return parkingLot;
    }
}
