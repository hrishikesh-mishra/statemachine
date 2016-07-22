package com.hrishikesh.statemachine.services.impl;

import com.google.common.collect.Maps;
import com.hrishikesh.statemachine.entities.*;
import com.hrishikesh.statemachine.services.api.TransitionHandlerService;

import java.util.Map;
import java.util.Objects;

import static com.hrishikesh.statemachine.entities.EventType.*;
/**
* Created by hrishikesh.mishra
*/
public class TransitionHandlerServiceImpl implements TransitionHandlerService {

    private final Map<TransitionHandlerType, TransitionCallable> callables;

    public TransitionHandlerServiceImpl() {
        callables = Maps.newHashMap();
    }

    public void addTransitionBeforeLandingOnToState(State from, State to, Event event, TransitionCallable transitionCallable){
        callables.put(new TransitionHandlerType(from, to, event, BEFORE_STATE_TRANSITION), transitionCallable);
    }

    public void addTransitionAfterTakeOffOnFromState(State from, State to, Event event, TransitionCallable transitionCallable){
        callables.put(new TransitionHandlerType(from, to, event, AFTER_STATE_TRANSITION), transitionCallable);
    }

    public void addTransitionInTransitionBetweenFromAndToState(State from, State to, Event event, TransitionCallable transitionCallable){
        callables.put(new TransitionHandlerType(from, to, event, IN_ARY_TRANSITION), transitionCallable);
    }

    public void addTransitionBeforeAnyTransition(Event event, TransitionCallable transitionCallable){
        callables.put(new TransitionHandlerType(null, null, event, BEFORE_ANY_TRANSITION), transitionCallable);
    }

    public void addTransitionAfterAnyTransition(Event event, TransitionCallable transitionCallable){
        callables.put(new TransitionHandlerType(null, null, event, AFTER_ANY_TRANSITION), transitionCallable);
    }

    public void addTransitionAnyOnTransition(Event event, TransitionCallable transitionCallable){
        callables.put(new TransitionHandlerType(null, null, event, IN_ARY_TRANSITION), transitionCallable);
    }


    public <C extends Context> void handleTakeOffTransition(State from, State to, Event event, C context){
        TransitionCallable callable = callables.get(new TransitionHandlerType(null, null, event, BEFORE_ANY_TRANSITION));

        if(!Objects.isNull(callable)) callable.call(context);

        /** @todo validate from and to state scenarios **/
        callable = callables.get(new TransitionHandlerType(from, to, event, BEFORE_STATE_TRANSITION));
        if(!Objects.isNull(callable)) callable.call(context);
    }

    public <C extends Context> void handleTransition(State from, State to, Event event, C context){
        TransitionCallable callable = callables.get(new TransitionHandlerType(null, null, event, IN_ARY_TRANSITION));

        if(!Objects.isNull(callable)) callable.call(context);

        /** @todo validate from and to state scenarios **/
        callable = callables.get(new TransitionHandlerType(from, to, event, IN_STATE_TRANSITION));
        if(!Objects.isNull(callable)) callable.call(context);
    }

    public <C extends Context> void handleLandingTransition(State from, State to, Event event, C context){
        TransitionCallable callable = callables.get(new TransitionHandlerType(null, null, event, AFTER_ANY_TRANSITION));

        if(!Objects.isNull(callable)) callable.call(context);

        /** @todo validate from and to state scenarios **/
        callable = callables.get(new TransitionHandlerType(from, to, event, AFTER_STATE_TRANSITION));
        if(!Objects.isNull(callable)) callable.call(context);
    }

}
