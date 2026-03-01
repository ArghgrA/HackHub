package com.github.ArghgrA.Hackhub.model.abstractions;

import com.github.ArghgrA.Hackhub.model.users.TeamMember;

import java.util.List;

public interface Team<I extends Comparable<I>> {
    I getId();
    String getName();
    List<TeamMember> getMembers();
}
