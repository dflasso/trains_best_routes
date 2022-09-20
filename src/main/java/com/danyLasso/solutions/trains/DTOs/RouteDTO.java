/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danyLasso.solutions.trains.DTOs;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author dany_lasso
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteDTO {
        
    private List<NodeDTO> nodes;
    
    private Double distanceAccumulatedFinal;
    
    private Double distanceAccumulatedTemp;
    
    private Integer numStops;
}
