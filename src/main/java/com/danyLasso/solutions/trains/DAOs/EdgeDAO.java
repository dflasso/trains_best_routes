/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.danyLasso.solutions.trains.DAOs;

import com.danyLasso.solutions.trains.DTOs.EdgeDTO;
import java.util.List;

/**
 *
 * @author dany_lasso
 */
public interface EdgeDAO {
    /**
     * 
     * @return 
     */
    public List<EdgeDTO> findAll();
}
