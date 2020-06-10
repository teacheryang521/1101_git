package com.yang.test;

import hh.HibernateUtil;

import org.hibernate.*;

import com.sun.org.apache.bcel.internal.generic.EmptyVisitor;
import com.yang.entity.Employee;
import com.yang.entity.Project;

/**
 * 双向多对多关联
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
	 * 新增医疗项目和石油勘探项目
	 * 新招的 汪汪，阳阳参与
	 * 汪汪   -------- 医疗项目
	 * 阳阳   -------- 医疗项目
	 * 阳阳   -------- 石油勘探项目
	 */
	public static void insertTest(){
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSession();
			tx=session.beginTransaction();
			
			//实例化项目对象
			Project yiLiao=new Project();
			yiLiao.setPname("医疗项目");
			
			Project shiYou=new Project();
			shiYou.setPname("石油勘探项目");
			
			
			//员工对象
			Employee yang=new Employee();
			yang.setEmpName("阳阳 ");
			
			Employee wang=new Employee();
			wang.setEmpName("汪汪 ");
			
			//建立双向的关联关系(把员工关联到项目)
			yiLiao.getEmps().add(yang);
			yiLiao.getEmps().add(wang);
			shiYou.getEmps().add(yang);
			
			//把项目关联到员工
			yang.getPs().add(yiLiao);
			yang.getPs().add(shiYou);
			wang.getPs().add(yiLiao);
			
			//持久化操作
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
	 *把汪汪调到石油勘探项目组
	 */
	public static void editTest(){
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSession();
			tx=session.beginTransaction();
			
			//首先获取汪汪
			Employee emp=(Employee) session.get(Employee.class, 10);
			//其次获取石油勘探项目
			Project pro=(Project) session.get(Project.class, 9);
			//建立关系
			emp.getPs().add(pro);
			pro.getEmps().add(emp);
			
			//最后持久化操作
			session.update(pro);
			
			
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		}finally{
			session.close();
		}
	}
	

	/**
	 * 医疗项目招标失败
	 * 
	 */
	public static void delTest(){
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSession();
			tx=session.beginTransaction();
			
			//其次获取石油勘探项目
			Project pro=(Project) session.get(Project.class, 8);
			
			//直接删除项目
			session.delete(pro);
			
			
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		}finally{
			session.close();
		}
	}
}
