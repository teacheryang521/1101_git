package com.yang.test;

import hh.HibernateUtil;

import org.hibernate.*;

import com.sun.org.apache.bcel.internal.generic.EmptyVisitor;
import com.yang.entity.Employee;
import com.yang.entity.Project;

/**
 * ˫���Զ����
 * @author Administrator
 *
 */
public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		insertTest();
		//editTest();
		//delTest();
	}
	
	/**
	 * ����ҽ����Ŀ��ʯ�Ϳ�̽��Ŀ
	 * ���е� ��������������
	 * ����   -------- ҽ����Ŀ
	 * ����   -------- ҽ����Ŀ
	 * ����   -------- ʯ�Ϳ�̽��Ŀ
	 */
	public static void insertTest(){
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSession();
			tx=session.beginTransaction();
			
			//ʵ������Ŀ����
			Project yiLiao=new Project();
			yiLiao.setPname("ҽ����Ŀ");
			
			Project shiYou=new Project();
			shiYou.setPname("ʯ�Ϳ�̽��Ŀ");
			
			
			//Ա������
			Employee yang=new Employee();
			yang.setEmpName("���� ");
			
			Employee wang=new Employee();
			wang.setEmpName("���� ");
			
			//����˫��Ĺ�����ϵ(��Ա����������Ŀ)
			yiLiao.getEmps().add(yang);
			yiLiao.getEmps().add(wang);
			shiYou.getEmps().add(yang);
			
			//����Ŀ������Ա��
			yang.getPs().add(yiLiao);
			yang.getPs().add(shiYou);
			wang.getPs().add(yiLiao);
			
			//�־û�����
			session.save(yiLiao);
			session.save(shiYou);
			
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		}finally{
			session.close();
		}
	}

	/**
	 *����������ʯ�Ϳ�̽��Ŀ��
	 */
	public static void editTest(){
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSession();
			tx=session.beginTransaction();
			
			//���Ȼ�ȡ����
			Employee emp=(Employee) session.get(Employee.class, 10);
			//��λ�ȡʯ�Ϳ�̽��Ŀ
			Project pro=(Project) session.get(Project.class, 9);
			//������ϵ
			emp.getPs().add(pro);
			pro.getEmps().add(emp);
			
			//���־û�����
			session.update(pro);
			
			
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		}finally{
			session.close();
		}
	}
	

	/**
	 * ҽ����Ŀ�б�ʧ��
	 * 
	 */
	public static void delTest(){
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSession();
			tx=session.beginTransaction();
			
			//��λ�ȡʯ�Ϳ�̽��Ŀ
			Project pro=(Project) session.get(Project.class, 8);
			
			//ֱ��ɾ����Ŀ
			session.delete(pro);
			
			
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		}finally{
			session.close();
		}
	}
}
