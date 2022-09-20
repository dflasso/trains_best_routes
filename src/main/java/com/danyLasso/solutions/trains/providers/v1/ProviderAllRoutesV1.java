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
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.danyLasso.solutions.trains.providers.ProviderRoutes;

/**
 * {@inheritDoc }
 *
 * @author dany_lasso
 * @version 1.0.0
 */
public class ProviderAllRoutesV1 extends ProviderRouteBase
        implements ProviderRoutes<NodeDTO, List<EdgeDTO>, List<RouteDTO>, RouteDTO> {

    /**
     * {@inheritDoc}
     *
     * @param origin
     * @param destiny
     * @param edges
     * @param restriction
     * @return
     */
    @Override
    public List<RouteDTO> findRoutes(NodeDTO origin, NodeDTO destiny, List<EdgeDTO> edges, Predicate<RouteDTO> restriction) {
        List<RouteDTO> allRoutes = this.initAllRoutes(origin);

        List<RouteDTO> routesFound = new ArrayList<>();

        List<RouteDTO> newRoutes = new ArrayList<>();

        Integer numRoutesToAnalyze;
        do {
            //find new routes from last node to near nodes
            allRoutes.stream().forEach(routeToAnalyze
                    -> newRoutes.addAll(this.findNewRoutesByNearbyNodes(routeToAnalyze, edges)));

            //search in each route if it arrived to destiny
            this.addRoutesWithDestiny(destiny, routesFound, newRoutes, restriction);

            //save and clean new routes to next iteration
            allRoutes.clear();
            allRoutes.addAll(newRoutes.stream()
                    .filter(xNewRoutes -> xNewRoutes.getDistanceAccumulatedFinal() == -1D)
                    .collect(Collectors.toList()));
            newRoutes.clear();

            //check if there are other routes that can reach the destination
            numRoutesToAnalyze = allRoutes.size();
            if (!routesFound.isEmpty()) {
                numRoutesToAnalyze = (int) allRoutes.stream().filter(xNextRoute -> restriction.test(xNextRoute)).count();
            }

        } while (numRoutesToAnalyze > 0);

        return routesFound;
    }

    private List<RouteDTO> initAllRoutes(NodeDTO origin) {
        List<RouteDTO> allRoutes = new ArrayList<>();
        RouteDTO firstRoute = new RouteDTO();
        firstRoute.setDistanceAccumulatedFinal(0D);
        firstRoute.setDistanceAccumulatedTemp(0D);
        firstRoute.setNodes(Arrays.asList(origin));
        firstRoute.setNumStops(0);
        allRoutes.add(firstRoute);

        return allRoutes;
    }

    
}
