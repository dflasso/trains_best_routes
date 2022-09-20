/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.danyLasso.solutions.trains.DAOs;

import com.danyLasso.solutions.trains.DTOs.NodeDTO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dany_lasso
 */
public interface NodeDAO {
    
    /**
     * recovery all nodes saved
     * @return 
     */
    public List<NodeDTO> findAll();
    
    /**
     * recovery specific Node by name
     * @param name
     * @return 
     */
    public Optional<NodeDTO> findByName(String name);
}
