package com.github.ArghgrA.Hackhub.model.abstractions;

public interface Staff<I extends Comparable<I>, H extends Hackathon<I>> extends User<I>{
    H getHackathon();
}
