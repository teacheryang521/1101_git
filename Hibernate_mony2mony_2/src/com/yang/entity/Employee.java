package com.yang.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee implements java.io.Serializable {

	// Fields

	private Integer empId;
	private String empName;
	private Set<Project> ps=new HashSet<Project>();

	// Constructors

	public Set<Project> getPs() {
		return ps;
	}

	public void setPs(Set<Project> ps) {
		this.ps = ps;
	}

	/** default constructor */
	public Employee() {
	}

	/** full constructor */
	public Employee(String empName) {
		this.empName = empName;
	}

	// Property accessors

	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

}