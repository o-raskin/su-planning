package ru.olegraskin.skillupplanning.events.service;

import ru.olegraskin.skillupplanning.events.domain.UserEvent;

import java.util.List;
import java.util.Set;

public interface UserEventService {

    Set<UserEvent> getAllUserEvents(long id);

}
