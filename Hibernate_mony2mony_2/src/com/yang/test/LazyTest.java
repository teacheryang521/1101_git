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
	  * �༶���lazy
	  * �����.hbm.xml�� ���lazy����ͨ���������Ҷ���
	  * */
	public static void lazyClass(){
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSession();
			tx=session.beginTransaction();
			
			//��λ�ȡʯ�Ϳ�̽��Ŀ
			Employee emp=(Employee) session.load(Employee.class, 11);
			
			/**
			 * �༶���,����clss���� lazy=true ��Ĭ����һ����,
			 * ����֧���ӳټ���
			 * ����ӳټ���,���ض����Ǵ������,û�з���select���
			 * �����������Ե�ʱ��,�Żᷢ��select���
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
	  * ����(һ�Զ�,��Զ�)�����lazy
	  * �����.hbm.xml�� ��<set>���lazy����
	  * */
	public static void lazyList(){
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSession();
			tx=session.beginTransaction();
			
			//��λ�ȡʯ�Ϳ�̽��Ŀ
			Employee emp=(Employee) session.load(Employee.class, 11);
			
			/**
			 *lazy=true:����ӳټ���,���ض����Ǵ������,û�з���select���
			 * �����������Ե�ʱ��,�Żᷢ��select���
			 * 
			 * 
			 * lazy=extra  ����bug???????????????????????????
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
