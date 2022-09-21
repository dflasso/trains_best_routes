/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danyLasso.solutions.trains.providers.v1;

import com.danyLasso.solutions.trains.DTOs.EdgeDTO;
import com.danyLasso.solutions.trains.DTOs.NodeDTO;
import com.danyLasso.solutions.trains.DTOs.RouteDTO;
import com.danyLasso.solutions.trains.providers.ProviderRouteBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.danyLasso.solutions.trains.providers.ProviderDistanceBetweenNodes;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author dany_lasso
 */
public class ProviderDistanceBetweenNodesOptimalRoute extends ProviderRouteBase
        implements ProviderDistanceBetweenNodes<List<NodeDTO>, List<EdgeDTO>, Double> {

    /**
     * {@inheritDoc}
     *
     * @see dijkstra's algorithm
     * @param nodes
     * @param edges
     * @return
     */
    @Override
    public Double resolveDistance(List<NodeDTO> nodes, List<EdgeDTO> edges) {
        this.validateNodesAndEdgeInbound(nodes, edges);

        //Init values
        NodeDTO origin = nodes.get(0);
        NodeDTO destiny = nodes.get(1);

        //analyze routes
        Optional<RouteDTO> routeOpt = this.findOptimalRoute(origin, destiny, edges);

        return routeOpt.isPresent() ? routeOpt.get().getDistanceAccumulatedFinal() : 0D;
    }

    /**
     * search optimal route from init node
     *
     * @param routes
     * @param destiny
     * @return
     */
    private Optional<RouteDTO> findOptimalRoute(NodeDTO origin, NodeDTO destiny, List<EdgeDTO> edges) {
        Optional<RouteDTO> optimalRoute = Optional.empty();

        RouteDTO initRoute = this.buildInitRoute(origin);
        RouteDTO selectedRoute = initRoute;

        List<RouteDTO> routes = new ArrayList<>();
        routes.add(initRoute);

        List<RouteDTO> newRoutes = new ArrayList<>();

        List<RouteDTO> routesFound = new ArrayList<>();

        Integer numRoutesToAnalyze = routes.size();
        Double minDistance;

        while (numRoutesToAnalyze > 0) {
            //find new routes by  last selected Route to near nodes
            newRoutes.addAll(this.findNewRoutesByNearbyNodes(selectedRoute, edges));

            //get routes that arrived destiny
            this.addRoutesWithDestiny(destiny, routesFound, newRoutes);

            //saved new routes
            routes.addAll(newRoutes);
            newRoutes.clear();

            //Analyze wich route has less distance
            minDistance = Double.MAX_VALUE;
            for (RouteDTO routeToAnalyze : routes) {
                if (routeToAnalyze.getDistanceAccumulatedTemp() <= 0 || routeToAnalyze.getDistanceAccumulatedFinal() > 0) {
                    continue;
                }

                if (routeToAnalyze.getDistanceAccumulatedTemp() < minDistance) {
                    minDistance = routeToAnalyze.getDistanceAccumulatedTemp();
                    selectedRoute = routeToAnalyze;
                }
            }

            //check if others routes have not been verified 
            numRoutesToAnalyze = (int) routes.stream().filter(xRoute -> xRoute.getDistanceAccumulatedFinal() < 0).count();

            if (!routesFound.isEmpty()) {
                //find unique routes
                Set<String> routesAsString = routesFound.stream()
                        .map(RouteDTO::getNodes)
                        .map(ltsNodes
                                -> ltsNodes.stream()
                                .map(NodeDTO::getName)
                                .reduce("", (partialString, element) -> partialString + element + ",")
                        )
                        .collect(Collectors.toSet());

                //check if there are duplicates routes, so break loop
                Boolean uniqueRoutes = true;
                for (String routeAsString : routesAsString) {
                    uniqueRoutes = routesAsString.stream()
                            .filter(routeS -> routeS.contains(routeAsString.substring(2)) && !routeS.equals(routeAsString))
                            .findFirst()
                            .isEmpty();

                    //flag to determinate if some route is inside of other route
                    if (!uniqueRoutes) {
                        numRoutesToAnalyze = 0;
                        break;
                    }
                }
            }

        }

        minDistance = Double.MAX_VALUE;
        for (RouteDTO routeFound : routesFound) {
            if (routeFound.getDistanceAccumulatedFinal() < minDistance && routeFound.getDistanceAccumulatedFinal() > 0) {
                minDistance = routeFound.getDistanceAccumulatedFinal();
                optimalRoute = Optional.of(routeFound);
            }
        }

        return optimalRoute;
    }

    private RouteDTO buildInitRoute(NodeDTO origin) {
        return RouteDTO.builder()
                .distanceAccumulatedTemp(0D)
                .distanceAccumulatedFinal(0D)
                .nodes(Arrays.asList(origin))
                .numStops(0)
                .build();
    }

    /**
     * verify data inbound
     *
     * @param nodes
     * @param edges
     */
    private void validateNodesAndEdgeInbound(List<NodeDTO> nodes, List<EdgeDTO> edges) {
        if (nodes == null || nodes.size() != 2) {
            throw new IllegalArgumentException("nodes require just 2 items: origin and destiny");
        }

        if (edges == null || edges.isEmpty()) {
            throw new IllegalArgumentException("edges is empty");
        }
    }

}
