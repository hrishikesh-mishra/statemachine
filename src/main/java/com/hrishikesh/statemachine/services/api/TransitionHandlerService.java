package com.hrishikesh.statemachine.services.api;

import com.hrishikesh.statemachine.entities.Context;
import com.hrishikesh.statemachine.entities.Event;
import com.hrishikesh.statemachine.entities.State;
import com.hrishikesh.statemachine.entities.TransitionCallable;

/**
 * <p>
 *  Transition Handler Service
 * </p>
 * Created by hrishikesh.mishra
 */
public interface TransitionHandlerService {

    void addTransitionBeforeLandingOnToState(State from, State to, Event event, TransitionCallable transitionCallable);

    void addTransitionAfterTakeOffOnFromState(State from, State to, Event event, TransitionCallable transitionCallable);

    void addTransitionInTransitionBetweenFromAndToState(State from, State to, Event event, TransitionCallable transitionCallable);

    void addTransitionBeforeAnyTransition(Event event, TransitionCallable transitionCallable);

    void addTransitionAfterAnyTransition(Event event, TransitionCallable transitionCallable);

    void addTransitionAnyOnTransition(Event event, TransitionCallable transitionCallable);


    <C extends Context> void handleTakeOffTransition(State from, State to, Event event, C context);

    <C extends Context> void handleTransition(State from, State to, Event event, C context);

    <C extends Context> void handleLandingTransition(State from, State to, Event event, C context);

}
