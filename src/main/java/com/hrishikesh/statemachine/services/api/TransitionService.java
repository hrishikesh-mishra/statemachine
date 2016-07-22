package com.hrishikesh.statemachine.services.api;

import com.hrishikesh.statemachine.entities.Event;
import com.hrishikesh.statemachine.entities.State;
import com.hrishikesh.statemachine.entities.Transition;

import java.util.Collection;

/**
 * <p>
 *     Transition Service
 * </p>
 * Created by hrishikesh.mishra
 */
public interface TransitionService {

    void addTransition(State fromState , State toState, Event event);

    Collection<Transition> getTransitions(State fromState);

    boolean isTransitionExist(State fromState, State toState, Event event);
}
