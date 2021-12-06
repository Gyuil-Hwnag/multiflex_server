package com.example.demo.src.seat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Seat {
    private int movietimeIdx;
    private int seatIdx;
    private int status;
}
