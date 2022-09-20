/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.danyLasso.solutions.trains.providers;

import java.util.function.Predicate;

/**
 * 
 * @author dany_lasso
 * @param <N> Class with node information
 * @param <E> Class with edge information
 * @param <R> Class with route information
 * @param <PV> Predicate Value type
 */
public interface ProviderRoutes<N, E, R, PV> {
    
    /**
     * Find route from origin to destiny by edges
     * @param origin
     * @param destiny
     * @param edges
     * @param restriction is a callback  to handle custom validation 
     * @return best route
     */
    public R findRoutes(N origin, N destiny, E edges, Predicate<PV> restriction);
}
