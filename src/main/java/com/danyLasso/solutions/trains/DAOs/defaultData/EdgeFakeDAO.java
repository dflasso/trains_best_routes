/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danyLasso.solutions.trains.DAOs.defaultData;

import com.danyLasso.solutions.trains.DAOs.EdgeDAO;
import com.danyLasso.solutions.trains.DAOs.NodeDAO;
import com.danyLasso.solutions.trains.DTOs.EdgeDTO;
import com.danyLasso.solutions.trains.DTOs.NodeDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dany_lasso
 */
public class EdgeFakeDAO implements EdgeDAO{

    private NodeDAO nodeDAO;
    
    public EdgeFakeDAO() {
        this.nodeDAO = new NodeFakeDAO();
    }
    
    

    @Override
    public List<EdgeDTO> findAll() {
        List<EdgeDTO> edges = new ArrayList<>();
        
         List<NodeDTO> nodes = this.nodeDAO.findAll();
         
        NodeDTO nodeA = nodes.stream().filter(xNode -> xNode.getName().equals("A")).findFirst().get();
        NodeDTO nodeB = nodes.stream().filter(xNode -> xNode.getName().equals("B")).findFirst().get();
        NodeDTO nodeC = nodes.stream().filter(xNode -> xNode.getName().equals("C")).findFirst().get();
        NodeDTO nodeD =nodes.stream().filter(xNode -> xNode.getName().equals("D")).findFirst().get();
        NodeDTO nodeE =nodes.stream().filter(xNode -> xNode.getName().equals("E")).findFirst().get();
        
        EdgeDTO edge = EdgeDTO.builder().nodeOrigin(nodeA).nodeDestiny(nodeB).distance(5D).build();
        edges.add(edge);
        
        edge =EdgeDTO.builder().nodeOrigin(nodeB).nodeDestiny(nodeC).distance(4D).build();
        edges.add(edge);
        
        edge =EdgeDTO.builder().nodeOrigin(nodeC).nodeDestiny(nodeD).distance(8D).build();
        edges.add(edge);
        
        edge =EdgeDTO.builder().nodeOrigin(nodeD).nodeDestiny(nodeC).distance(8D).build();
        edges.add(edge);
        
        edge =EdgeDTO.builder().nodeOrigin(nodeD).nodeDestiny(nodeE).distance(6D).build();
        edges.add(edge);
        
        edge =EdgeDTO.builder().nodeOrigin(nodeA).nodeDestiny(nodeD).distance(5D).build();
        edges.add(edge);
        
        edge =EdgeDTO.builder().nodeOrigin(nodeC).nodeDestiny(nodeE).distance(2D).build();
        edges.add(edge);
        
        edge =EdgeDTO.builder().nodeOrigin(nodeE).nodeDestiny(nodeB).distance(3D).build();
        edges.add(edge);
        
        edge =EdgeDTO.builder().nodeOrigin(nodeA).nodeDestiny(nodeE).distance(7D).build();
        edges.add(edge);
        
        return edges;
    }
    
}
