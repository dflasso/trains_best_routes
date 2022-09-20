/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.danyLasso.solutions.trains.providers;

/**
 * Provaider distance Between Nodes
 * @author dany_lasso
 * @param <N> Object or objects with node information for calculate distance
 * @param <R> Object or objects with edges information
 * @param <D> Object with the result of calculation
 */
public interface ProviderDistanceBetweenNodes<N, R, D> {
    
    /**
     * 
     * @param nodes
     * @param routes
     * @return <D> Object with the distance information
     */
    public D resolveDistance(N nodes, R edges);
}
