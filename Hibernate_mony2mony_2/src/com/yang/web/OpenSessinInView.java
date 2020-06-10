package com.yang.web;


import hh.HibernateUtil;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * �������ǹ�����
 * @author Administrator
 *
 */
public class OpenSessinInView implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ��session,����������ύ����
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chin) throws IOException, ServletException {
		
		Session session=null;
		Transaction tx=null;
		try {
			//��һ��session
			session=HibernateUtil.getSession();
			//����һ������
			tx=session.beginTransaction();
			
			//���˵�����
			chin.doFilter(request, response);
			
			//�ύ����
			tx.commit();
			
			
		} catch (Exception e) {
			tx.rollback();
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
