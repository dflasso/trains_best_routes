/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.danyLasso.solutions.trains.providers.v1;

import com.danyLasso.solutions.trains.DAOs.EdgeDAO;
import com.danyLasso.solutions.trains.DAOs.NodeDAO;
import com.danyLasso.solutions.trains.DAOs.defaultData.EdgeFakeDAO;
import com.danyLasso.solutions.trains.DAOs.defaultData.NodeFakeDAO;
import com.danyLasso.solutions.trains.DTOs.EdgeDTO;
import com.danyLasso.solutions.trains.DTOs.NodeDTO;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author dany_lasso
 */
public class ProviderDistanceBetweenNodesOptimalRouteTest {
    
    private List<EdgeDTO> edges;

    private List<NodeDTO> nodes;
    
    private ProviderDistanceBetweenNodesOptimalRoute providerOptimalRoute;

    @BeforeEach
    void initEachTest() {
        EdgeDAO edgeDAO = new EdgeFakeDAO();
        edges = edgeDAO.findAll();

        NodeDAO nodeDAO = new NodeFakeDAO();
        nodes = nodeDAO.findAll();
        
        providerOptimalRoute = new ProviderDistanceBetweenNodesOptimalRoute();
    }

    /**
     * Test of testResolveDistance_From_A_to_C method, of class ProviderDistanceBetweenNodesOptimalRoute.
     */
    @Test
    public void testResolveDistance_From_A_to_C() {
        
        NodeDTO nodeA = this.nodes.stream().filter(xNode -> xNode.getName().equals("A")).findFirst().get();
        NodeDTO nodeC = this.nodes.stream().filter(xNode -> xNode.getName().equals("C")).findFirst().get();
        
        Double distance = this.providerOptimalRoute.resolveDistance(Arrays.asList(nodeA,nodeC), edges);
        
        assertEquals(9D, distance);
    }
    
    
        /**
     * Test of testResolveDistance_From_B_to_B method, of class ProviderDistanceBetweenNodesOptimalRoute.
     */
    @Test
    public void testResolveDistance_From_B_to_B() {
        
        NodeDTO nodeB = this.nodes.stream().filter(xNode -> xNode.getName().equals("B")).findFirst().get();
        
        
        Double distance = this.providerOptimalRoute.resolveDistance(Arrays.asList(nodeB,nodeB), edges);
        
        assertEquals(9D, distance);
    }
    
}
