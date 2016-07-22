package com.hrishikesh.statemachine;

import com.hrishikesh.statemachine.entities.Context;
import com.hrishikesh.statemachine.entities.Event;
import com.hrishikesh.statemachine.entities.State;
import com.hrishikesh.statemachine.entities.TransitionCallable;
import com.hrishikesh.statemachine.exceptions.DuplicateTransitionException;
import com.hrishikesh.statemachine.exceptions.NoSuchTransitionException;
import com.hrishikesh.statemachine.services.api.TransitionHandlerService;
import com.hrishikesh.statemachine.services.api.TransitionService;
import com.hrishikesh.statemachine.services.impl.TransitionHandlerServiceImpl;
import com.hrishikesh.statemachine.services.impl.TransitionServiceImpl;

/**
 * <p>
 *     Finite state machine
 *     inspired from @koushikr easy-fsm
 * </p>
 *
 * Created by hrishikesh.mishra
 */

public class StateMachine {

    private final TransitionHandlerService transitionHandlerService;
    private final TransitionService transitionService;

    /**
     * <p>
     *     Only allow to build through builder
     * </p>
     * @param transitionHandlerService
     * @param transitionService
     */
    private StateMachine(TransitionHandlerService transitionHandlerService, TransitionService transitionService) {
        this.transitionHandlerService = transitionHandlerService;
        this.transitionService = transitionService;
    }

    public void addTransition(State from, State to, Event event) throws DuplicateTransitionException {
        if(transitionService.isTransitionExist(from, to, event))
            throw new DuplicateTransitionException("Duplicate transition, from: " +from  + ", to: " + to + ", event: " + event);

        transitionService.addTransition(from, to, event);
    }

    public void addTransitionHandlerBeforeLandingOnToState(State from, State to, Event event, TransitionCallable transitionCallable) throws  NoSuchTransitionException {
        if(!transitionService.isTransitionExist(from, to, event))
            throw new NoSuchTransitionException("No such transition, from: " +from  + ", to: " + to + ", event: " + event);

        transitionHandlerService.addTransitionBeforeLandingOnToState(from, to, event, transitionCallable);
    }

    public void addTransitionHandlerAfterTakeOffOnFromState(State from, State to, Event event, TransitionCallable transitionCallable) throws  NoSuchTransitionException {
        if(!transitionService.isTransitionExist(from, to, event))
            throw new NoSuchTransitionException("No such transition, from: " +from  + ", to: " + to + ", event: " + event);

        transitionHandlerService.addTransitionAfterTakeOffOnFromState(from, to, event, transitionCallable);
    }

    public void addTransitionHandlerInTransitionBetweenFromAndToState(State from, State to, Event event, TransitionCallable transitionCallable) throws  NoSuchTransitionException {
        if(!transitionService.isTransitionExist(from, to, event))
            throw new NoSuchTransitionException("No such transition, from: " +from  + ", to: " + to + ", event: " + event);

        transitionHandlerService.addTransitionInTransitionBetweenFromAndToState(from, to, event, transitionCallable);
    }

    public void addTransitionHandlerBeforeAnyTransition(Event event, TransitionCallable transitionCallable) throws  NoSuchTransitionException {
        /** @todo add validation for duplicate checking **/
        transitionHandlerService.addTransitionBeforeAnyTransition(event, transitionCallable);
    }

    public void addTransitionHandlerAfterAnyTransition(Event event, TransitionCallable transitionCallable) throws  NoSuchTransitionException {
        /** @todo add validation for duplicate checking **/
        transitionHandlerService.addTransitionAfterAnyTransition(event, transitionCallable);
    }

    public void addTransitionAnyOnTransition(Event event, TransitionCallable transitionCallable) throws  NoSuchTransitionException {
        /** @todo add validation for duplicate checking **/
        transitionHandlerService.addTransitionAnyOnTransition(event, transitionCallable);
    }

    private void handleTakeOffTransition(State from, State to, Event event, Context context) throws  NoSuchTransitionException {
        if(!transitionService.isTransitionExist(from, to, event))
            throw new NoSuchTransitionException("No such transition, from: " +from  + ", to: " + to + ", event: " + event);

        transitionHandlerService.handleTakeOffTransition(from, to, event, context);
    }

    private void handleTransition(State from, State to, Event event, Context context) throws  NoSuchTransitionException {
        if(!transitionService.isTransitionExist(from, to, event))
            throw new NoSuchTransitionException("No such transition, from: " +from  + ", to: " + to + ", event: " + event);

        transitionHandlerService.handleTransition(from, to, event, context);
    }

    private void handleLandingTransition(State from, State to, Event event, Context context) throws  NoSuchTransitionException {
        if(!transitionService.isTransitionExist(from, to, event))
            throw new NoSuchTransitionException("No such transition, from: " +from  + ", to: " + to + ", event: " + event);

        transitionHandlerService.handleLandingTransition(from, to, event, context);
    }

    public void fire(State from, State to, Event event, Context context) throws NoSuchTransitionException {

        if(!transitionService.isTransitionExist(from, to, event))
            throw new NoSuchTransitionException("No such transition, from: " +from  + ", to: " + to + ", event: " + event);

        handleTakeOffTransition(from, to, event, context);
        handleTransition(from, to, event, context);
        handleLandingTransition(from, to, event, context);
    }

    /**
     * <p>
     *     Sate Machine Builder
     * </p>
     */
    public static class Builder {

        private StateMachine stateMachine;

        public Builder() {
            stateMachine = new StateMachine(new TransitionHandlerServiceImpl(), new TransitionServiceImpl());
        }

        public Builder addTransition(State from, State to, Event event) throws DuplicateTransitionException {
            stateMachine.addTransition(from, to, event);
            return this;
        }


        public Builder addTransitionHandlerBeforeLandingOnToState(State from, State to, Event event, TransitionCallable transitionCallable) throws  NoSuchTransitionException {
            stateMachine.addTransitionHandlerBeforeLandingOnToState(from, to, event, transitionCallable);
            return this;
        }

        public Builder addTransitionHandlerAfterTakeOffOnFromState(State from, State to, Event event, TransitionCallable transitionCallable) throws  NoSuchTransitionException {
            stateMachine.addTransitionHandlerAfterTakeOffOnFromState(from, to, event, transitionCallable);
            return this;
        }

        public Builder addTransitionHandlerInTransitionBetweenFromAndToState(State from, State to, Event event, TransitionCallable transitionCallable) throws  NoSuchTransitionException {
            stateMachine.addTransitionHandlerInTransitionBetweenFromAndToState(from, to, event, transitionCallable);
            return this;
        }

        public Builder addTransitionHandlerBeforeAnyTransition(Event event, TransitionCallable transitionCallable) throws  NoSuchTransitionException {
            stateMachine.addTransitionHandlerBeforeAnyTransition(event, transitionCallable);
            return this;
        }

        public Builder addTransitionHandlerAfterAnyTransition(Event event, TransitionCallable transitionCallable) throws  NoSuchTransitionException {
            stateMachine.addTransitionHandlerAfterAnyTransition(event, transitionCallable);
            return this;
        }

        public Builder addTransitionAnyOnTransition(Event event, TransitionCallable transitionCallable) throws  NoSuchTransitionException {
            stateMachine.addTransitionAnyOnTransition(event, transitionCallable);
            return this;
        }

        public StateMachine build(){
            return stateMachine;
        }
    }
}
