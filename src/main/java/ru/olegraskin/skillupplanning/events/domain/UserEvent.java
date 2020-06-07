package ru.olegraskin.skillupplanning.events.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserEvent {

    private Long id;

    private Long userId;

    private LocalDateTime start;

    private LocalDateTime end;

    private Number color;

}
