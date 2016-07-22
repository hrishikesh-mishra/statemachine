package com.hrishikesh.statemachine.entities;

/**
 * <p>
 *     Transition handler Callable
 * </p>
 *
 * Created by hrishikesh.mishra
 */
public interface TransitionCallable {

    <C extends Context>  void call(C context);
}
