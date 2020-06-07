package ru.olegraskin.skillupplanning.promotion.service;

import ru.olegraskin.skillupplanning.promotion.domain.Promotion;

import java.util.Set;

public interface PromotionService {

    Promotion create(Promotion promotion);

    Promotion update(Promotion promotion);

    Set<Promotion> getAllByUserId(Long userId);

    Promotion getByUserIdAndStatus(Long userId, Promotion.Status status);

    void delete(Long promotionId);

    Promotion getPromotionById(Long promotionId);

    Set<Promotion> getAllPromotionsWithParticipation(Long userId);
}
