package com.defers.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Track {
    private List<Transport> transports = new ArrayList<>();
    private final int length;

    public void addTransport(Transport transport) {
        if (transports.contains(transport)) {
            throw new RuntimeException("The transport is already on the track");
        }
        transports.add(transport);
    }
}
