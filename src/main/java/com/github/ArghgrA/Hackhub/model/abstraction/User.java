package com.github.ArghgrA.Hackhub.model.abstraction;

public interface User<I extends Comparable<I>> {
    I getId();
    String getName();
    String getUsername();
    String getEmail();
    String getPassword();
}
