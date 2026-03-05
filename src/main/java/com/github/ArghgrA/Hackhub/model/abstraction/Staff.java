package com.github.ArghgrA.Hackhub.model.abstraction;

public interface Staff<I extends Comparable<I>, H extends Hackathon<I>> extends User<I>{
    H getHackathon();
}
