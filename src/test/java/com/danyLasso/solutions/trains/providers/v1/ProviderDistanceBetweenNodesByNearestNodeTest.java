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
import com.danyLasso.solutions.trains.exceptions.NotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author dany_lasso
 */
public class ProviderDistanceBetweenNodesByNearestNodeTest {
    
    private EdgeDAO edgeDAO;
    
    private NodeDAO nodeDAO;
    
    private  List<NodeDTO> nodesFake;
    
    private List<EdgeDTO> edgesFake;
    
    @BeforeEach
    void initServicesBeforeTest(){
        this.edgeDAO = new EdgeFakeDAO();
        this.nodeDAO = new NodeFakeDAO();
        this.nodesFake = this.nodeDAO.findAll();
        this.edgesFake = this.edgeDAO.findAll();
    }
    

    /**
     * Test of testResolveDistanceWithNodes_A_B_C method, of class ProvaiderDistanceBetweenNodesByNearestNode.
     * Case -> Resolve Distance with mock data: he distance of the route A-B-C
     */
    @Test
    @DisplayName("test Resolve Distance with mock data: he distance of the route A-B-C")
    public void testResolveDistanceWithNodes_A_B_C() {
        List<NodeDTO> nodes = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("A") || 
                                 xNode.getName().equals("B") ||
                                 xNode.getName().equals("C"))
                .collect(Collectors.toList());
        
        ProviderDistanceBetweenNodesByNearestNode providerDistance = new ProviderDistanceBetweenNodesByNearestNode();
        
        Double distance = providerDistance.resolveDistance(nodes, edgesFake);
        
        assertEquals(9D,distance,  "Value expected 9 but was " + distance.toString());
        
    }
    
    /**
     * Test of testResolveDistanceWithNodes_A_D method, of class ProvaiderDistanceBetweenNodesByNearestNode.
     * Case -> Resolve Distance with mock data: he distance of the route A-D
     */
    @Test
    @DisplayName("test Resolve Distance with mock data: he distance of the route A-D")
    public void testResolveDistanceWithNodes_A_D() {
        List<NodeDTO> nodes = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("A") || 
                                 xNode.getName().equals("D"))
                .collect(Collectors.toList());
        
        ProviderDistanceBetweenNodesByNearestNode providerDistance = new ProviderDistanceBetweenNodesByNearestNode();
        
        Double distance = providerDistance.resolveDistance(nodes, edgesFake);
        
        assertEquals(5D,distance,  "Value expected 5 but was " + distance.toString());
        
    }
    
    /**
     * Test of testResolveDistanceWithNodes_A_D_C method, of class ProvaiderDistanceBetweenNodesByNearestNode.
     * Case -> Resolve Distance with mock data: he distance of the route A-D-C
     */
    @Test
    @DisplayName("test Resolve Distance with mock data: he distance of the route A-D-C")
    public void testResolveDistanceWithNodes_A_D_C() {
        NodeDTO nodeA = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("A"))
                .findFirst().get();
        
        NodeDTO nodeC = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("C"))
                .findFirst().get();
        
        NodeDTO nodeD = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("D"))
                .findFirst().get();
        
         List<NodeDTO> nodes = Arrays.asList(nodeA, nodeD, nodeC);
        
        ProviderDistanceBetweenNodesByNearestNode providerDistance = new ProviderDistanceBetweenNodesByNearestNode();
        
        Double distance = providerDistance.resolveDistance(nodes, edgesFake);
        
        assertEquals(13D,distance,  "Value expected 13 but was " + distance.toString());
    }
    
     /**
     * Test of testResolveDistanceWithNodes_A_E_B_C_D method, of class ProvaiderDistanceBetweenNodesByNearestNode.
     * Case -> Resolve Distance with mock data: he distance of the route A-E-B-C-D
     */
    @Test
    @DisplayName("test Resolve Distance with mock data: he distance of the route A-E-B-C-D")
    public void testResolveDistanceWithNodes_A_E_B_C_D() {
        NodeDTO nodeA = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("A"))
                .findFirst().get();
        
        NodeDTO nodeE = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("E"))
                .findFirst().get();
        
        NodeDTO nodeB = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("B"))
                .findFirst().get();
        
        NodeDTO nodeC = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("C"))
                .findFirst().get();
        
        NodeDTO nodeD = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("D"))
                .findFirst().get();
        
         List<NodeDTO> nodes = Arrays.asList(nodeA,nodeE, nodeB, nodeC, nodeD);
        
        ProviderDistanceBetweenNodesByNearestNode providerDistance = new ProviderDistanceBetweenNodesByNearestNode();
        
        Double distance = providerDistance.resolveDistance(nodes, edgesFake);
        
        assertEquals(22D,distance,  "Value expected 22 but was " + distance.toString());
    }
    
    
     /**
     * Test of testResolveDistanceWithNodes_A_E_D method, of class ProvaiderDistanceBetweenNodesByNearestNode.
     * Case -> Resolve Distance with mock data: he distance of the route A-E-D
     */
    @Test
    @DisplayName("test Resolve Distance with mock data: he distance of the route A-E-D")
    public void testResolveDistanceWithNodes_A_E_D_error_NO_SUCH_ROUTE() {
        NodeDTO nodeA = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("A"))
                .findFirst().get();
        
        NodeDTO nodeE = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("E"))
                .findFirst().get();
        
        NodeDTO nodeD = this.nodesFake.stream()
                .filter(xNode -> xNode.getName().equals("D"))
                .findFirst().get();
        
         List<NodeDTO> nodes = Arrays.asList(nodeA,nodeE, nodeD);
        
        ProviderDistanceBetweenNodesByNearestNode providerDistance = new ProviderDistanceBetweenNodesByNearestNode();
        
        //check if exception was launched
        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                                            providerDistance.resolveDistance(nodes, edgesFake));
        
        //check if message was: NO SUCH ROUTE
        assertEquals("NO SUCH ROUTE", exception.getMessage());
    }
    
}
