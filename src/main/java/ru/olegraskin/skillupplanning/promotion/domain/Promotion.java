package ru.olegraskin.skillupplanning.promotion.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promotion")
@Data
@NoArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    private Long userId;

    private Long originGradeId;

    private Long nextGradeId;

    /**
     * Set of users ids, whose marked as members of promotion.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "promotion_member", joinColumns = @JoinColumn(name = "promotion_id"))
    @Column(name = "member_id")
    private Set<Long> members = new HashSet<>();

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Promotion.Status status;

    public enum Status {
        CREATED,
        FAILED,
        SUCCESSFULLY
    }

}
