/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danyLasso.solutions.trains.DAOs.defaultData;

import com.danyLasso.solutions.trains.DAOs.NodeDAO;
import com.danyLasso.solutions.trains.DTOs.NodeDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author dany_lasso
 */
public class NodeFakeDAO implements NodeDAO{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<NodeDTO> findAll() {
        List<NodeDTO> nodes = new ArrayList<>();
        
        NodeDTO node = NodeDTO.builder().id(UUID.randomUUID().toString()).name("A").build();
        nodes.add(node);
        
        node = NodeDTO.builder().id(UUID.randomUUID().toString()).name("B").build();
        nodes.add(node);
        
        node = NodeDTO.builder().id(UUID.randomUUID().toString()).name("C").build();
        nodes.add(node);
        
        node = NodeDTO.builder().id(UUID.randomUUID().toString()).name("D").build();
        nodes.add(node);
        
        node = NodeDTO.builder().id(UUID.randomUUID().toString()).name("E").build();
        nodes.add(node);
        
        
        return nodes;
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public Optional<NodeDTO> findByName(String name) {
        List<NodeDTO> nodes = this.findAll();
        return nodes.stream().filter(xNode -> xNode.getName().equals(name)).findFirst();
    }
    
}
