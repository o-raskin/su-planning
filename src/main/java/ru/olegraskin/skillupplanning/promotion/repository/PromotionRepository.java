package ru.olegraskin.skillupplanning.promotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olegraskin.skillupplanning.promotion.domain.Promotion;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Set<Promotion> findAllByUserId(long userId);

    Optional<Promotion> findByUserIdAndStatus(long userId, int statusOrdinal);

    Set<Promotion> findAllByMembersIs(long userId);
}
