package com.github.ArghgrA.Hackhub.model;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.builder.DefaultHackathonBuilder;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.other.message.call.DefaultCall;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.user.AbstractUser;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;
import com.github.ArghgrA.Hackhub.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AddCallTest {

    @Autowired
    private UserRepository<AbstractUser> usrRepository;

    @Autowired
    private UserRepository<TeamMember> teamMemberRepository; // Repository per TeamMember

    @Autowired
    private HackathonRepository<AbstractHackathon> hRepository;

    @Autowired
    private TeamRepository<AbstractTeam> teamRepository; // Repository per Team

    @Autowired
    private CallRepository<DefaultCall> callRepository; // Repository per Call (da creare se non esiste)


    @Test
    @Commit
    public void testPersistence() {
        // ---------- 1. Creazione e salvataggio di un utente base ----------
        var usr = new DefaultUser();
        usr.setName("foo");
        usr.setUsername("foobar");
        usr.setEmail("foo@bar");
        usr.setPassword("pwd");
        usrRepository.save(usr);

        var usr_copy = usrRepository.findById(usr.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        assertNotNull(usr_copy);
        // Confronta per ID invece che per equals (evita il problema di equals non implementato)
        assertEquals(usr.getId(), usr_copy.getId());
        assertEquals(usr.getName(), usr_copy.getName());
        assertEquals(usr.getUsername(), usr_copy.getUsername());
        // ... altri campi se necessario

        // ---------- 2. Creazione dello staff ----------
        var o = new Organizer();
        o.setName("foo");
        o.setUsername("foobar");
        o.setEmail("foo@bar");
        o.setPassword("pwd");

        var j = new Judge();
        j.setUsername("foobar");
        j.setEmail("foo@bar");
        j.setPassword("pwd");

        var m = new Mentor();
        m.setUsername("foobar");
        m.setEmail("foo@bar");
        m.setPassword("pwd");

        var m2 = new Mentor();
        m2.setUsername("foobar");
        m2.setEmail("foo@bar");
        m2.setPassword("pwd");

        // ---------- 3. Creazione e salvataggio dell'hackathon ----------
        var h_build = new DefaultHackathonBuilder();
        var h = h_build
                .setName("hackathon")
                .setRule("rules")
                .setMaxTeamMembers(10)
                .setInterval(new Interval(
                        LocalDateTime.parse("2027-06-01T10:00:00"),
                        LocalDateTime.parse("2027-06-01T12:00:00"),
                        LocalDateTime.parse("2027-06-01T13:00:00"),
                        LocalDateTime.parse("2027-06-01T15:00:00")
                ))
                .setOrganizer(o)
                .setJudge(j)
                .setMentors(List.of(m, m2))
                .getResult();

        hRepository.save(h);

        var h_copy = hRepository.findById(h.getId())
                .orElseThrow(() -> new RuntimeException("Hackathon not found"));
        assertNotNull(h_copy);
        // Confronta per ID
        assertEquals(h.getId(), h_copy.getId());
        assertEquals(h.getName(), h_copy.getName());

        // ---------- 4. Creazione di un team e di un membro del team ----------
        var team = new DefaultTeam();
        team.setName("Team Test");

        // Trasforma l'utente base in TeamMember
        TeamMember teamMember = usr.transform(TeamMember.class);
        team.addMember(teamMember);

        // Salva il team e il membro (se non gestito in cascata)
        teamRepository.save(team);
        // Salva esplicitamente il membro se necessario (dipende dalla configurazione JPA)
        teamMemberRepository.save(teamMember);

        // Verifica che il team sia stato salvato
        var team_copy = teamRepository.findById(team.getId())
                .orElseThrow(() -> new RuntimeException("Team not found"));
        assertNotNull(team_copy);
        assertEquals(team.getId(), team_copy.getId());
        assertEquals(team.getName(), team_copy.getName());

        // ---------- 5. Creazione e salvataggio di una DefaultCall ----------
        var call = new DefaultCall();
        call.setSender(m);               // Il mentore che propone la call
        call.setReceiver(team);          // Il team destinatario
        call.setMessage(LocalDateTime.now().plusDays(2)); // Data proposta per la call
        call.setTicket(null);             // Nessun ticket associato

        callRepository.save(call);

        // Verifica che la call sia stata salvata correttamente
        var call_copy = callRepository.findById(call.getId())
                .orElseThrow(() -> new RuntimeException("Call not found"));
        assertNotNull(call_copy);
        assertEquals(call.getId(), call_copy.getId());
        assertEquals(call.getSender().getId(), call_copy.getSender().getId());
        assertEquals(call.getReceiver().getId(), call_copy.getReceiver().getId());
        assertEquals(call.getMessage(), call_copy.getMessage());
        assertNull(call_copy.getCalendarEventId()); // Non ancora accettata
    }
}
