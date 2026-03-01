package com.github.ArghgrA.Hackhub.model.hackathon.builder;

import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;

public class DefaultHackathonBuilder implements HackathonBuilder {
    private DefaultHackathon hackathon;

    @Override
    public void setName() {}

    @Override
    public void setRule() {}

    @Override
    public void setInterval() {}

    @Override
    public void setMaxTeamMembers() {}

    @Override
    public void setJudge() {}

    @Override
    public void setMentors() {}

    public DefaultHackathon getResult() {
        DefaultHackathon tmp = hackathon;
        hackathon = new DefaultHackathon();
        return tmp;
    }
}
