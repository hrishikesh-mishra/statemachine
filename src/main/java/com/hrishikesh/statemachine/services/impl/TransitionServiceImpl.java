package com.hrishikesh.statemachine.services.impl;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.hrishikesh.statemachine.entities.Event;
import com.hrishikesh.statemachine.entities.State;
import com.hrishikesh.statemachine.entities.Transition;
import com.hrishikesh.statemachine.services.api.TransitionService;

import java.util.Collection;

/**
 * Created by hrishikesh.mishra on 22/07/16.
 */
public class TransitionServiceImpl implements TransitionService {

    Multimap<State, Transition> transitionCollection;

    public TransitionServiceImpl() {
        transitionCollection = HashMultimap.create();
    }

    @Override
    public void addTransition(State fromState, State toState, Event event) {
        transitionCollection.put(fromState, new Transition(fromState, toState, event));
    }

    @Override
    public Collection<Transition> getTransitions(State fromState) {
        return transitionCollection.get(fromState);
    }

    @Override
    public boolean isTransitionExist(State fromState, State toState, Event event) {
        return  getTransitions(fromState).contains(new Transition(fromState, toState, event));
    }

}
