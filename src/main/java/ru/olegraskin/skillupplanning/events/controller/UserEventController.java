package ru.olegraskin.skillupplanning.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.olegraskin.skillupplanning.events.domain.UserEvent;
import ru.olegraskin.skillupplanning.events.service.UserEventService;

import java.util.Set;

@RestController
@RequestMapping("/user-events")
@RequiredArgsConstructor
public class UserEventController {

    private final UserEventService userEventService;

    @GetMapping
    public Set<UserEvent> getEventsByUserId(@RequestParam("userId") Long userId) {
        return userEventService.getAllUserEvents(userId);
    }
}
