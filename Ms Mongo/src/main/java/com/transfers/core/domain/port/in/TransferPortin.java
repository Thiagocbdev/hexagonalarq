package com.transfers.core.domain.port.in;


import com.transfers.kafka.contracts.DriverDto;

public interface TransferPortin {

    void publishDriver(DriverDto message);

}
