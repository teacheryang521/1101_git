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
 * 创建的是过滤器
 * @author Administrator
 *
 */
public class OpenSessinInView implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 打开session,开启事务和提交事务
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chin) throws IOException, ServletException {
		
		Session session=null;
		Transaction tx=null;
		try {
			//打开一个session
			session=HibernateUtil.getSession();
			//开启一个事务
			tx=session.beginTransaction();
			
			//过滤的内容
			chin.doFilter(request, response);
			
			//提交事务
			tx.commit();
			
			
		} catch (Exception e) {
			tx.rollback();
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
