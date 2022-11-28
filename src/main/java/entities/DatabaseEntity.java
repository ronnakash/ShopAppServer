/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import com.openuproject.shopappserver.dtos.EntityDto;
import java.io.Serializable;

/**
 *
 * @author ronna
 */
public interface DatabaseEntity extends Serializable {
    
    public EntityDto toDto();
    
    public void update(EntityDto dto);
    
}
