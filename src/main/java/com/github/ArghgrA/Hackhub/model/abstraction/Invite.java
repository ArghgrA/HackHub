package com.github.ArghgrA.Hackhub.model.abstraction;

public interface Invite<I extends Comparable<I>> {
    I getId();
    Team<I> getTeam();
    User<I> getUser();
    String getMessage();
}
