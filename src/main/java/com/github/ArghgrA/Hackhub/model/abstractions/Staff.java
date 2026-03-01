package com.github.ArghgrA.Hackhub.model.abstractions;

public interface Staff<I extends Comparable<I>, N extends Number> extends User<I>{
    Hackathon<I,N> getHackathon();
}
