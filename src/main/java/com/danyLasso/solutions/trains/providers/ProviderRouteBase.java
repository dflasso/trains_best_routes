/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danyLasso.solutions.trains.providers;

import com.danyLasso.solutions.trains.DTOs.EdgeDTO;
import com.danyLasso.solutions.trains.DTOs.NodeDTO;
import com.danyLasso.solutions.trains.DTOs.RouteDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Implements cammon methods to other providers
 * @author dany_lasso
 */
public abstract class ProviderRouteBase {

    /**
     * find New Routes By Near by Nodes and List edges
     * @param parentRoute
     * @param edges
     * @return 
     */
    protected List<RouteDTO> findNewRoutesByNearbyNodes(RouteDTO parentRoute, List<EdgeDTO> edges) {
        List<RouteDTO> tmpRoutes = new ArrayList<>();

        NodeDTO origin = parentRoute.getNodes().get(parentRoute.getNodes().size() - 1);

        List<EdgeDTO> edgesByOrigin = this.findEdgeByOrigin(origin, edges);

        edgesByOrigin.stream().forEach(xEdge -> {
            RouteDTO newTmpRoute = new RouteDTO();

            List<NodeDTO> nodes = new ArrayList<>();
            nodes.addAll(parentRoute.getNodes());
            nodes.add(xEdge.getNodeDestiny());
            newTmpRoute.setNodes(nodes);

            newTmpRoute.setNumStops(nodes.size() - 1);
            newTmpRoute.setDistanceAccumulatedTemp(parentRoute.getDistanceAccumulatedTemp() + xEdge.getDistance());
            newTmpRoute.setDistanceAccumulatedFinal(-1D);

            tmpRoutes.add(newTmpRoute);
        });

        parentRoute.setDistanceAccumulatedFinal(parentRoute.getDistanceAccumulatedTemp());

        return tmpRoutes;
    }

    /**
     * find Edge By Origin Node and List edges
     * @param origin
     * @param edges
     * @return 
     */
    protected List<EdgeDTO> findEdgeByOrigin(NodeDTO origin, List<EdgeDTO> edges) {
        return edges.stream()
                .filter(xEdge -> xEdge.getNodeOrigin().getName().equals(origin.getName()))
                .collect(Collectors.toList());
    }
    
    /**
     * add Routes With Destiny but check restriction
     * @param destiny
     * @param routesFound
     * @param newRoutes
     * @param restriction 
     */
    protected void addRoutesWithDestiny(NodeDTO destiny, List<RouteDTO> routesFound, List<RouteDTO> newRoutes, Predicate<RouteDTO> restriction) {

        NodeDTO lastNode;
        for (RouteDTO nRoute : newRoutes) {
            lastNode = nRoute.getNodes().get(nRoute.getNodes().size() - 1);

            if (lastNode.getName().equals(destiny.getName()) && restriction.test(nRoute)) {
                Double distanceAccumulatedFinal = nRoute.getDistanceAccumulatedTemp();
                nRoute.setDistanceAccumulatedFinal(distanceAccumulatedFinal);
                routesFound.add(nRoute);
            }
        }
    }
    
    /**
     * add Routes With Destiny
     * @param destiny
     * @param routesFound
     * @param newRoutes
     */
    protected void addRoutesWithDestiny(NodeDTO destiny, List<RouteDTO> routesFound, List<RouteDTO> newRoutes) {

        NodeDTO lastNode;
        for (RouteDTO nRoute : newRoutes) {
            lastNode = nRoute.getNodes().get(nRoute.getNodes().size() - 1);

            if (lastNode.getName().equals(destiny.getName())) {
                Double distanceAccumulatedFinal = nRoute.getDistanceAccumulatedTemp();
                nRoute.setDistanceAccumulatedFinal(distanceAccumulatedFinal);
                routesFound.add(nRoute);
            }
        }
    }

}
