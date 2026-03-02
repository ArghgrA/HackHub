package com.github.ArghgrA.Hackhub.model.other;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor @Getter @Setter
@Entity @Table(name = "table_interval")
public class Interval {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
