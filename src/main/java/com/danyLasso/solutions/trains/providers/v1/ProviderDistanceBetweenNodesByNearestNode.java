/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danyLasso.solutions.trains.providers.v1;

import com.danyLasso.solutions.trains.DTOs.EdgeDTO;
import com.danyLasso.solutions.trains.DTOs.NodeDTO;
import com.danyLasso.solutions.trains.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import com.danyLasso.solutions.trains.providers.ProviderDistanceBetweenNodes;

/**
 * {@inheritDoc}
 * @author dany_lasso
 */
public class ProviderDistanceBetweenNodesByNearestNode implements ProviderDistanceBetweenNodes<List<NodeDTO>, List<EdgeDTO>, Double> {

    /**
     * {@inheritDoc}
     * @param nodes
     * @param edges
     * @return 
     */
    @Override
    public Double resolveDistance(List<NodeDTO> nodes, List<EdgeDTO> edges) {
        //check content of nodes, min 2 nodes
        if (nodes == null || nodes.size() < 2) {
            throw new IllegalArgumentException("At least two nodes are required.");
        }

        //check edges
        if (edges == null || edges.isEmpty()) {
            throw new IllegalArgumentException("Edges are empty");
        }

        Double totalDistance = 0D;
        
        for (int i = 0; i < nodes.size(); i++) {
            if(i + 1 == nodes.size()){
                continue;
            }
            
            //instance origin and destiny node of this iteration
            NodeDTO origin = nodes.get(i);
            NodeDTO destiny = nodes.get(i + 1);
            
            //find edge with destiny and origin match
            Optional<EdgeDTO> edgeOpt = edges.stream()
                    .filter(xEdge
                            -> xEdge.getNodeOrigin().getName().equals(origin.getName())
                               && xEdge.getNodeDestiny().getName().equals(destiny.getName())
                    )
                    .findFirst();
            
            if(edgeOpt.isEmpty()){
                throw new NotFoundException("NO SUCH ROUTE");
            }
            
            
            totalDistance += edgeOpt.get().getDistance();
        }

        return totalDistance;
    }

}
