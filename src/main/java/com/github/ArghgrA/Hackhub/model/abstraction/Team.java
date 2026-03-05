package com.github.ArghgrA.Hackhub.model.abstraction;

import com.github.ArghgrA.Hackhub.model.user.TeamMember;

import java.util.List;

public interface Team<I extends Comparable<I>> {
    I getId();
    String getName();
    List<TeamMember> getMembers();
}
