package com.github.ArghgrA.Hackhub.model.other.message;

import com.github.ArghgrA.Hackhub.model.abstraction.Message;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor @Getter @Setter
@Entity @Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractMessage<T,S,R> implements Message<T,S,R, UUID> {
    @Id @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
