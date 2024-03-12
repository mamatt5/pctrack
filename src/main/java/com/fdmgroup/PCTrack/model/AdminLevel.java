package com.fdmgroup.PCTrack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * This object converts the permission level of a user into a numerical value to use for frontend
 * permission checking
 */

@Entity
public class AdminLevel {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;

    // 1: highest, 2: 2nd highest... etc 
    @Column(name = "PRECEDENCE")
    private int precedence; 
    
    public AdminLevel() {
		super(); 
	
	}
    
	public int getPrecedence() {
		return precedence;
	}

	public void setPrecedence(int precedence) {
		this.precedence = precedence;
	}

	public AdminLevel(String name, int precedence) {
		super();
		this.name = name;
		this.precedence= precedence; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 

    
    
}