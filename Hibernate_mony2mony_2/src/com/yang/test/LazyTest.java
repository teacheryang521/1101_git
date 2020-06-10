package com.yang.test;

import java.util.List;
import java.util.Set;

import hh.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yang.entity.Employee;
import com.yang.entity.Project;

public class LazyTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//lazyClass();
		lazyList();
	}
	
	
	 /**
	  * 类级别的lazy
	  * 在类的.hbm.xml中 添加lazy属性通过主键查找对象
	  * */
	public static void lazyClass(){
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSession();
			tx=session.beginTransaction();
			
			//其次获取石油勘探项目
			Employee emp=(Employee) session.load(Employee.class, 11);
			
			/**
			 * 类级别的,是在clss里面 lazy=true 和默认是一样的,
			 * 都是支持延迟加载
			 * 如果延迟加载,返回对象是代理对象,没有发出select语句
			 * 调用里面属性的时候,才会发出select语句
			 */
			
			System.out.println(emp.getEmpName());
			
			
			
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		}finally{
			session.close();
		}
	}
	
	
	
	 /**
	  * 集合(一对多,多对多)级别的lazy
	  * 在类的.hbm.xml中 的<set>添加lazy属性
	  * */
	public static void lazyList(){
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSession();
			tx=session.beginTransaction();
			
			//其次获取石油勘探项目
			Employee emp=(Employee) session.load(Employee.class, 11);
			
			/**
			 *lazy=true:如果延迟加载,返回对象是代理对象,没有发出select语句
			 * 调用里面属性的时候,才会发出select语句
			 * 
			 * 
			 * lazy=extra  存在bug???????????????????????????
			 */
			emp.getPs().size();
		
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		}finally{
			session.close();
		}
	}
}
