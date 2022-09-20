/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danyLasso.solutions.trains.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a point can be a desteny or origin
 * @author dany_lasso
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NodeDTO {
    private String id;
    
    private String name;
}
