package ru.olegraskin.skillupplanning.promotion.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.olegraskin.skillupplanning.exception.ResourceNotFoundException;
import ru.olegraskin.skillupplanning.promotion.domain.Promotion;
import ru.olegraskin.skillupplanning.promotion.repository.PromotionRepository;
import ru.olegraskin.skillupplanning.promotion.service.PromotionService;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    @Override
    public Promotion create(@NonNull Promotion promotion) {

        // temp solution
        while (true) {
            try {
                return promotionRepository.save(promotion);
            } catch (Exception ignored) { }
        }
    }

    @Override
    public Promotion update(@NonNull Promotion promotion) {
        Optional<Promotion> optional = promotionRepository.findById(promotion.getId());
        if (!optional.isPresent()) {
            return this.create(promotion);
        }
        Promotion stored = optional.get();
        stored.setMembers(promotion.getMembers());
        stored.setNextGradeId(promotion.getNextGradeId());
        stored.setOriginGradeId(promotion.getOriginGradeId());
        stored.setStartDate(promotion.getStartDate());
        stored.setEndDate(promotion.getEndDate());
        stored.setStatus(promotion.getStatus());
        stored.setUserId(promotion.getUserId());
        return this.promotionRepository.save(stored);
    }

    @Override
    public Set<Promotion> getAllByUserId(@NonNull Long userId) {
        return promotionRepository.findAllByUserId(userId);
    }

    @Override
    public Promotion getByUserIdAndStatus(Long userId, Promotion.Status status) {
        Optional<Promotion> optionalPromotion = promotionRepository.findByUserIdAndStatus(userId, status.ordinal());
        return optionalPromotion
                .orElseThrow(() -> new ResourceNotFoundException("Promotion", "userId", userId + " and status: " + status));
    }

    @Override
    public void delete(@NonNull Long promotionId) {
        this.promotionRepository.deleteById(promotionId);
    }

    @Override
    public Promotion getPromotionById(Long promotionId) {
        return promotionRepository.findById(promotionId)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion", "id", promotionId));
    }

    @Override
    public Set<Promotion> getAllPromotionsWithParticipation(Long userId) {
        return promotionRepository.findAllByMembersIs(userId);
    }
}
