package com.github.ArghgrA.Hackhub.model.abstractions;

public interface Invite<I extends Comparable<I>> {
    I getId();
    Team<I> getTeam();
    User<I> getUser();
    String getMessage();
}
