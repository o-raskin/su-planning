package ru.olegraskin.skillupplanning.events.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.olegraskin.skillupplanning.events.domain.UserEvent;
import ru.olegraskin.skillupplanning.events.service.UserEventService;
import ru.olegraskin.skillupplanning.promotion.domain.Promotion;
import ru.olegraskin.skillupplanning.promotion.service.PromotionService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserEventsServiceImpl implements UserEventService {

    private static final String PROMOTION_STRING_KEY = "promotion";

    private final PromotionService promotionService;

    @Override
    public Set<UserEvent> getAllUserEvents(long userId) {
        Set<Promotion> promotions = promotionService.getAllPromotionsWithParticipation(userId);

        return promotions.stream()
                .filter(p -> p.getStatus().equals(Promotion.Status.CREATED))
                .map(p -> {
                    UserEvent event = new UserEvent();
                    event.setUserId(p.getUserId());
                    event.setColor(0);
                    event.setId(p.getId());
                    event.setStart(p.getStartDate());
                    event.setEnd(p.getEndDate());
                    return event;
                })
                .collect(Collectors.toSet());
    }
}
