/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import dtos.EntityDto;

import java.io.Serializable;

/**
 * @author ronna
 */
public interface DatabaseEntity extends Serializable {

	EntityDto toDto();

	void update(EntityDto dto);

}
