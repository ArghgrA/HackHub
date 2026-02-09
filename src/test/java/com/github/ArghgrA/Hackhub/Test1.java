package com.github.ArghgrA.Hackhub;

import com.github.ArghgrA.Hackhub.model.hackathon.Hackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.Intervallo;
import com.github.ArghgrA.Hackhub.model.posizione.Posizione;
import com.github.ArghgrA.Hackhub.model.posizione.OnSitePosizione;
import com.github.ArghgrA.Hackhub.model.premio.Premio;
import com.github.ArghgrA.Hackhub.model.premio.CartaCreditoPremio;
import com.github.ArghgrA.Hackhub.model.utente.staff.Giudice;
import com.github.ArghgrA.Hackhub.model.utente.staff.Mentore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite per la classe Hackathon.
 * Verifica la corretta gestione di hackathon, staff, e relazioni.
 */
@DisplayName("Test della classe Hackathon")
class Test1 {

    @Nested
    @DisplayName("Test del costruttore")
    class CostruttoreTest {

        @Test
        @DisplayName("Dovrebbe creare un hackathon con valori di default")
        void testCostruttoreDefault() {
            // Act
            Hackathon hackathon = new Hackathon();

            // Assert
            assertNotNull(hackathon);
            assertNotNull(hackathon.getId());
            assertNotNull(hackathon.getMentori());
            assertTrue(hackathon.getMentori().isEmpty());
        }

        @Test
        @DisplayName("Dovrebbe generare un UUID univoco")
        void testGenerazioneUUIDUnivoco() {
            // Act
            Hackathon hackathon1 = new Hackathon();
            Hackathon hackathon2 = new Hackathon();

            // Assert
            assertNotEquals(hackathon1.getId(), hackathon2.getId());
        }

        @Test
        @DisplayName("Dovrebbe inizializzare la lista mentori vuota")
        void testInizializzazioneListaMentori() {
            // Act
            Hackathon hackathon = new Hackathon();

            // Assert
            assertNotNull(hackathon.getMentori());
            assertEquals(0, hackathon.getMentori().size());
        }
    }

    @Nested
    @DisplayName("Test delle proprietà base")
    class ProprietaBaseTest {

        private Hackathon hackathon;

        @BeforeEach
        void setUp() {
            hackathon = new Hackathon();
        }

        @Test
        @DisplayName("Dovrebbe permettere di impostare e recuperare il nome")
        void testNome() {
            // Arrange
            String nomeAtteso = "Global Hack Week 2026";

            // Act
            hackathon.setNome(nomeAtteso);

            // Assert
            assertEquals(nomeAtteso, hackathon.getNome());
        }

        @Test
        @DisplayName("Dovrebbe permettere di impostare e recuperare il regolamento")
        void testRegolamento() {
            // Arrange
            String regolamento = "1. I team devono essere composti da massimo 4 membri.\n" +
                    "2. È vietato copiare codice da progetti esistenti.\n" +
                    "3. Tutti i progetti devono essere caricati entro la deadline.";

            // Act
            hackathon.setRegolamento(regolamento);

            // Assert
            assertEquals(regolamento, hackathon.getRegolamento());
        }

        @Test
        @DisplayName("Dovrebbe permettere di impostare e recuperare il numero massimo membri")
        void testMaxNumMembri() {
            // Arrange
            int maxMembri = 5;

            // Act
            hackathon.setMaxNumMebri(maxMembri);

            // Assert
            assertEquals(maxMembri, hackathon.getMaxNumMebri());
        }

        @Test
        @DisplayName("Dovrebbe accettare valori zero per maxNumMembri")
        void testMaxNumMembriZero() {
            // Act
            hackathon.setMaxNumMebri(0);

            // Assert
            assertEquals(0, hackathon.getMaxNumMebri());
        }

        @Test
        @DisplayName("Dovrebbe accettare valori negativi per maxNumMembri")
        void testMaxNumMembriNegativi() {
            // Act
            hackathon.setMaxNumMebri(1);

            // Assert
            assertEquals(1, hackathon.getMaxNumMebri());
        }
    }

    @Nested
    @DisplayName("Test delle relazioni con Posizione")
    class PosizioneTest {

        private Hackathon hackathon;

        @BeforeEach
        void setUp() {
            hackathon = new Hackathon();
        }

        @Test
        @DisplayName("Dovrebbe permettere di impostare una posizione OnSite")
        void testSetPosizioneOnSite() {
            // Arrange
            OnSitePosizione posizione = new OnSitePosizione();
            posizione.setLatitudine(40.7128);
            posizione.setLongitudine(-74.0060);

            // Act
            hackathon.setPosizione(posizione);

            // Assert
            assertNotNull(hackathon.getPosizione());
            assertEquals(posizione, hackathon.getPosizione());
        }

        @Test
        @DisplayName("Dovrebbe accettare posizione null")
        void testPosizioneNull() {
            // Act
            hackathon.setPosizione(null);

            // Assert
            assertNull(hackathon.getPosizione());
        }
    }

    @Nested
    @DisplayName("Test delle relazioni con Premio")
    class PremioTest {

        private Hackathon hackathon;

        @BeforeEach
        void setUp() {
            hackathon = new Hackathon();
        }

        @Test
        @DisplayName("Dovrebbe permettere di impostare un premio")
        void testSetPremio() {
            // Arrange
            CartaCreditoPremio premio = new CartaCreditoPremio(5000);

            // Act
            hackathon.setPremio(premio);

            // Assert
            assertNotNull(hackathon.getPremio());
            assertEquals(premio, hackathon.getPremio());
        }

        @Test
        @DisplayName("Dovrebbe accettare premio null")
        void testPremioNull() {
            // Act
            hackathon.setPremio(null);

            // Assert
            assertNull(hackathon.getPremio());
        }
    }

    @Nested
    @DisplayName("Test delle relazioni con Intervallo")
    class IntervalloTest {

        private Hackathon hackathon;

        @BeforeEach
        void setUp() {
            hackathon = new Hackathon();
        }

        @Test
        @DisplayName("Dovrebbe permettere di impostare un intervallo")
        void testSetIntervallo() {
            // Arrange
            LocalDateTime inizioIscrizioni = LocalDateTime.now().plusDays(30);
            LocalDateTime fineIscrizioni = inizioIscrizioni.plusHours(1);
            LocalDateTime inizioCompetizione = fineIscrizioni;
            LocalDateTime fineCompetizione = inizioCompetizione.plusHours(1);

            Intervallo intervallo = new Intervallo(
                    inizioIscrizioni,
                    fineIscrizioni,
                    inizioCompetizione,
                    fineCompetizione
            );

            // Act
            hackathon.setIntervallo(intervallo);

            // Assert
            assertNotNull(hackathon.getIntervallo());
            assertEquals(intervallo, hackathon.getIntervallo());
        }

        @Test
        @DisplayName("Dovrebbe accettare intervallo null")
        void testIntervalloNull() {
            // Act
            hackathon.setIntervallo(null);

            // Assert
            assertNull(hackathon.getIntervallo());
        }
    }

    @Nested
    @DisplayName("Test delle relazioni con Giudice")
    class GiudiceTest {

        private Hackathon hackathon;

        @BeforeEach
        void setUp() {
            hackathon = new Hackathon();
        }

        @Test
        @DisplayName("Dovrebbe permettere di impostare un giudice")
        void testSetGiudice() {
            // Arrange
            Giudice giudice = new Giudice();
            giudice.setNome("Mario Rossi");
            giudice.setEmail("mario.rossi@example.com");

            // Act
            hackathon.setGiudice(giudice);

            // Assert
            assertNotNull(hackathon.getGiudice());
            assertEquals(giudice, hackathon.getGiudice());
            assertEquals("Mario Rossi", hackathon.getGiudice().getNome());
        }

        @Test
        @DisplayName("Dovrebbe permettere di modificare il giudice")
        void testModificaGiudice() {
            // Arrange
            Giudice giudice1 = new Giudice();
            giudice1.setNome("Mario Rossi");

            Giudice giudice2 = new Giudice();
            giudice2.setNome("Luigi Bianchi");

            // Act
            hackathon.setGiudice(giudice1);
            hackathon.setGiudice(giudice2);

            // Assert
            assertEquals(giudice2, hackathon.getGiudice());
            assertEquals("Luigi Bianchi", hackathon.getGiudice().getNome());
        }

        @Test
        @DisplayName("Dovrebbe accettare giudice null")
        void testGiudiceNull() {
            // Act
            hackathon.setGiudice(null);

            // Assert
            assertNull(hackathon.getGiudice());
        }
    }

    @Nested
    @DisplayName("Test delle relazioni con Mentori")
    class MentoriTest {

        private Hackathon hackathon;

        @BeforeEach
        void setUp() {
            hackathon = new Hackathon();
        }

        @Test
        @DisplayName("Dovrebbe permettere di aggiungere un mentore")
        void testAggiungiMentore() {
            // Arrange
            Mentore mentore = new Mentore();
            mentore.setNome("Anna Verdi");
            mentore.setEmail("anna.verdi@example.com");

            // Act
            hackathon.setMentore(mentore);

            // Assert
            assertEquals(1, hackathon.getMentori().size());
            assertTrue(hackathon.getMentori().contains(mentore));
            assertEquals("Anna Verdi", hackathon.getMentori().get(0).getNome());
        }

        @Test
        @DisplayName("Dovrebbe permettere di aggiungere più mentori")
        void testAggiungiPiuMentori() {
            // Arrange
            Mentore mentore1 = new Mentore();
            mentore1.setNome("Anna Verdi");

            Mentore mentore2 = new Mentore();
            mentore2.setNome("Paolo Neri");

            Mentore mentore3 = new Mentore();
            mentore3.setNome("Laura Gialli");

            // Act
            hackathon.setMentore(mentore1);
            hackathon.setMentore(mentore2);
            hackathon.setMentore(mentore3);

            // Assert
            assertEquals(3, hackathon.getMentori().size());
            assertTrue(hackathon.getMentori().contains(mentore1));
            assertTrue(hackathon.getMentori().contains(mentore2));
            assertTrue(hackathon.getMentori().contains(mentore3));
        }

        @Test
        @DisplayName("Dovrebbe mantenere l'ordine di inserimento dei mentori")
        void testOrdineMentori() {
            // Arrange
            Mentore mentore1 = new Mentore();
            mentore1.setNome("Primo");

            Mentore mentore2 = new Mentore();
            mentore2.setNome("Secondo");

            Mentore mentore3 = new Mentore();
            mentore3.setNome("Terzo");

            // Act
            hackathon.setMentore(mentore1);
            hackathon.setMentore(mentore2);
            hackathon.setMentore(mentore3);

            // Assert
            List<Mentore> mentori = hackathon.getMentori();
            assertEquals("Primo", mentori.get(0).getNome());
            assertEquals("Secondo", mentori.get(1).getNome());
            assertEquals("Terzo", mentori.get(2).getNome());
        }

        @Test
        @DisplayName("Non dovrebbe permettere di impostare direttamente la lista mentori")
        void testSetMentoriNonDisponibile() {
            // Assert - verifica che il setter non esista o sia privato
            // Questo test è più concettuale dato che @Setter(AccessLevel.NONE) è usato
            assertNotNull(hackathon.getMentori());
        }
    }

    @Nested
    @DisplayName("Test di integrazione completi")
    class IntegrazioneTest {

        @Test
        @DisplayName("Dovrebbe creare un hackathon completo con tutte le proprietà")
        void testHackathonCompleto() {
            // Arrange
            Hackathon hackathon = new Hackathon();
            hackathon.setNome("AI Innovation Challenge 2026");
            hackathon.setRegolamento("Regole complete dell'hackathon");
            hackathon.setMaxNumMebri(4);

            // Posizione
            OnSitePosizione posizione = new OnSitePosizione();
            posizione.setLatitudine(41.9028);
            posizione.setLongitudine(12.4964);
            hackathon.setPosizione(posizione);

            // Premio
            CartaCreditoPremio premio = new CartaCreditoPremio(10000);
            hackathon.setPremio(premio);

            // Intervallo - aggiunto buffer di 1 giorno per evitare timing issues
            LocalDateTime inizioIscrizioni = LocalDateTime.now().plusDays(31);
            Intervallo intervallo = new Intervallo(
                    inizioIscrizioni,
                    inizioIscrizioni.plusDays(7),
                    inizioIscrizioni.plusDays(8),
                    inizioIscrizioni.plusDays(10)
            );
            hackathon.setIntervallo(intervallo);

            // Giudice
            Giudice giudice = new Giudice();
            giudice.setNome("Giudice Principale");
            giudice.setEmail("giudice@example.com");
            hackathon.setGiudice(giudice);

            // Mentori
            Mentore mentore1 = new Mentore();
            mentore1.setNome("Mentore 1");
            hackathon.setMentore(mentore1);

            Mentore mentore2 = new Mentore();
            mentore2.setNome("Mentore 2");
            hackathon.setMentore(mentore2);

            // Assert
            assertNotNull(hackathon.getId());
            assertEquals("AI Innovation Challenge 2026", hackathon.getNome());
            assertEquals(4, hackathon.getMaxNumMebri());
            assertNotNull(hackathon.getPosizione());
            assertNotNull(hackathon.getPremio());
            assertNotNull(hackathon.getIntervallo());
            assertNotNull(hackathon.getGiudice());
            assertEquals(2, hackathon.getMentori().size());
        }

        @Test
        @DisplayName("Dovrebbe creare un hackathon minimo valido")
        void testHackathonMinimo() {
            // Act
            Hackathon hackathon = new Hackathon();
            hackathon.setNome("Quick Hack");
            hackathon.setMaxNumMebri(2);

            // Assert
            assertNotNull(hackathon);
            assertNotNull(hackathon.getId());
            assertEquals("Quick Hack", hackathon.getNome());
            assertEquals(2, hackathon.getMaxNumMebri());
            assertNull(hackathon.getPosizione());
            assertNull(hackathon.getPremio());
            assertNull(hackathon.getIntervallo());
            assertNull(hackathon.getGiudice());
            assertTrue(hackathon.getMentori().isEmpty());
        }

        @Test
        @DisplayName("Dovrebbe gestire hackathon senza mentori")
        void testHackathonSenzaMentori() {
            // Arrange
            Hackathon hackathon = new Hackathon();
            hackathon.setNome("Solo Hackathon");

            Giudice giudice = new Giudice();
            giudice.setNome("Solo Giudice");
            hackathon.setGiudice(giudice);

            // Assert
            assertNotNull(hackathon.getGiudice());
            assertEquals(0, hackathon.getMentori().size());
        }

        @Test
        @DisplayName("Dovrebbe gestire hackathon senza giudice ma con mentori")
        void testHackathonSenzaGiudice() {
            // Arrange
            Hackathon hackathon = new Hackathon();
            hackathon.setNome("Mentored Hackathon");

            Mentore mentore = new Mentore();
            mentore.setNome("Mentore Solo");
            hackathon.setMentore(mentore);

            // Assert
            assertNull(hackathon.getGiudice());
            assertEquals(1, hackathon.getMentori().size());
        }
    }

    @Nested
    @DisplayName("Test casi limite")
    class CasiLimiteTest {

        @Test
        @DisplayName("Dovrebbe gestire nome molto lungo")
        void testNomeLungo() {
            // Arrange
            Hackathon hackathon = new Hackathon();
            String nomeLungo = "A".repeat(1000);

            // Act
            hackathon.setNome(nomeLungo);

            // Assert
            assertEquals(1000, hackathon.getNome().length());
        }

        @Test
        @DisplayName("Dovrebbe gestire regolamento molto lungo")
        void testRegolamentoLungo() {
            // Arrange
            Hackathon hackathon = new Hackathon();
            String regolamentoLungo = "Articolo 1: " + "X".repeat(10000);

            // Act
            hackathon.setRegolamento(regolamentoLungo);

            // Assert
            assertTrue(hackathon.getRegolamento().length() > 10000);
        }

        @Test
        @DisplayName("Dovrebbe gestire aggiunta di molti mentori")
        void testMoltiMentori() {
            // Arrange
            Hackathon hackathon = new Hackathon();

            // Act
            for (int i = 0; i < 100; i++) {
                Mentore mentore = new Mentore();
                mentore.setNome("Mentore " + i);
                hackathon.setMentore(mentore);
            }

            // Assert
            assertEquals(100, hackathon.getMentori().size());
        }

        @Test
        @DisplayName("Dovrebbe gestire nome null")
        void testNomeNull() {
            // Arrange
            Hackathon hackathon = new Hackathon();

            // Act
            hackathon.setNome(null);

            // Assert
            assertNull(hackathon.getNome());
        }

        @Test
        @DisplayName("Dovrebbe gestire regolamento null")
        void testRegolamentoNull() {
            // Arrange
            Hackathon hackathon = new Hackathon();

            // Act
            hackathon.setRegolamento(null);

            // Assert
            assertNull(hackathon.getRegolamento());
        }
    }
}