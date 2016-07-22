package com.hrishikesh.statemachine.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *     Transition handler type
 * </p>
 * Created by hrishikesh.mishra
 */
@AllArgsConstructor
@Setter @Getter
public class TransitionHandlerType {

    private State from;
    private State to;
    private Event event;
    private EventType eventType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransitionHandlerType)) return false;

        TransitionHandlerType that = (TransitionHandlerType) o;

        if (event != null? !event.equals(that.event) : that.event != null) return false;
        if (eventType != null? !eventType.equals(that.eventType) : that.eventType != null) return false;
        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (to != null ? !to.equals(that.to): that.to != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = event.hashCode();
        result = 31 * result + eventType.hashCode();
        result = 31 * result + (from != null? from.hashCode() : 0);
        result = 31 * result + (to != null? to.hashCode() : 0);
        return result;
    }
}
