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
import com.danyLasso.solutions.trains.DTOs.RouteDTO;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author dany_lasso
 */
public class ProviderAllRoutesV1Test {

    private List<EdgeDTO> edges;

    private List<NodeDTO> nodes;

    @BeforeEach
    void initEachTest() {
        EdgeDAO edgeDAO = new EdgeFakeDAO();
        edges = edgeDAO.findAll();

        NodeDAO nodeDAO = new NodeFakeDAO();
        nodes = nodeDAO.findAll();
    }

    /**
     * Test of testFindRoutes_From_C_to_C_restriction_num_stops method, of class ProviderAllRoutesV1.
     */
    @Test
    public void testFindRoutes_From_C_to_C_restriction_num_stops() {

        ProviderAllRoutesV1 provaiderRoutes = new ProviderAllRoutesV1();

        NodeDTO nodeC = this.nodes.stream().filter(xNode -> xNode.getName().equals("C")).findFirst().get();

        List<RouteDTO> routesFound = provaiderRoutes.findRoutes(nodeC, nodeC, edges, (route) -> route.getNumStops() <= 3);

        assertEquals(2, routesFound.size());

    }

    /**
     * Test of testFindRoutes_From_A_to_C_restriction_num_stops method, of class ProviderAllRoutesV1.
     */
    @Test
    public void testFindRoutes_From_A_to_C_restriction_num_stops() {

        ProviderAllRoutesV1 provaiderRoutes = new ProviderAllRoutesV1();

        NodeDTO nodeA = this.nodes.stream().filter(xNode -> xNode.getName().equals("A")).findFirst().get();
        NodeDTO nodeC = this.nodes.stream().filter(xNode -> xNode.getName().equals("C")).findFirst().get();

        List<RouteDTO> routesFound = provaiderRoutes.findRoutes(nodeA, nodeC, edges, (route) -> route.getNumStops() == 4);

        assertEquals(3, routesFound.size());
    }

    /**
     * Test of testFindRoutes_From_C_to_C_restriction_max_distance method, of class ProviderAllRoutesV1.
     */
    @Test
    public void testFindRoutes_From_C_to_C_restriction_max_distance() {

        ProviderAllRoutesV1 provaiderRoutes = new ProviderAllRoutesV1();

        NodeDTO nodeC = this.nodes.stream().filter(xNode -> xNode.getName().equals("C")).findFirst().get();

        List<RouteDTO> routesFound = provaiderRoutes.findRoutes(nodeC, nodeC, edges, (route) -> route.getDistanceAccumulatedTemp() < 30);

        assertEquals(3, routesFound.size());
    }

}
