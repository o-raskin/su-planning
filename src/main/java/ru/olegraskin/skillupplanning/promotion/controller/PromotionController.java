package ru.olegraskin.skillupplanning.promotion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.olegraskin.skillupplanning.promotion.domain.Promotion;
import ru.olegraskin.skillupplanning.promotion.dto.PromotionDto;
import ru.olegraskin.skillupplanning.promotion.mapper.PromotionMapper;
import ru.olegraskin.skillupplanning.promotion.service.PromotionService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;
    private final PromotionMapper promotionMapper;

    @GetMapping
    public List<PromotionDto> getAllPromotionsByUserId(@RequestParam("userId") Long userId) {
//        if (status != null) {
//            Promotion promotion = this.promotionService.getByUserIdAndStatus(userId, status);
//            return Collections.singletonList(promotionMapper.entityToDto(promotion));
//        }

        return this.promotionService.getAllByUserId(userId).stream()
                .map(promotionMapper::entityToDto)
                .sorted(Comparator.comparing(PromotionDto::getStartDate))
                .collect(Collectors.toList());
    }

    @PostMapping
    public PromotionDto createPromotion(@RequestBody PromotionDto promotionDto) {
        Promotion requestPromotion = this.promotionMapper.dtoToEntity(promotionDto);
        Promotion storedPromotion = this.promotionService.create(requestPromotion);
        return this.promotionMapper.entityToDto(storedPromotion);
    }

    @PostMapping("/{id}")
    public PromotionDto setPromotionStatus(@PathVariable("id") Long promotionId,
                                           @RequestParam("status") Promotion.Status status) {
        Promotion storedPromotion = this.promotionService.getPromotionById(promotionId);
        storedPromotion.setStatus(status);
        Promotion updatedPromotion = this.promotionService.update(storedPromotion);
        return this.promotionMapper.entityToDto(updatedPromotion);
    }

    @PutMapping("/{id}")
    public PromotionDto updatePromotion(@PathVariable("id") Long promotionId,
                                        @RequestBody PromotionDto promotionDto) {
        promotionDto.setId(promotionId);
        Promotion requestPromotion = this.promotionMapper.dtoToEntity(promotionDto);
        Promotion storedPromotion = this.promotionService.update(requestPromotion);
        return this.promotionMapper.entityToDto(storedPromotion);
    }

    @DeleteMapping("{id}")
    public void deletePromotion(@PathVariable("id") Long promotionId) {
        this.promotionService.delete(promotionId);
    }
}
