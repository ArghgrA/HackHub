package com.github.ArghgrA.Hackhub.model.abstraction;

public interface Message<T,S,R,I extends Comparable<I>> {
    I getId();
    S getSender();
    R getReceiver();
    T getMessage();
}
