package com.zjq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zjq.dao.UserDao;
import com.zjq.model.User;
import com.zjq.tools.Encrypt;

public class UserInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");//��ȡ���õ���������ע�ᡢ��½�����
		if(action!=null){
			if(action.equals("checkName")){
				this.checkName(request, response);
			}else if(action.equals("register")){
				this.register(request, response);
			}else if(action.equals("checkLogin")){
				this.checkLogin(request,response);
			}else if(action.equals("login")){
				this.login(request, response);
			}else if(action.equals("logout")){
				this.logout(request, response);
			}else{
				response.sendRedirect("index.jsp");	
			}
		}else{
			response.sendRedirect("index.jsp");
		}
	}
	/**
	 * �ж��û����Ƿ����,�ж��û������Ƿ��зǷ��ַ����ж���֤���Ƿ�������ȷ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void checkName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/xml;charset=UTF-8" );//������Ӧ��ʽ
		//������Ӧͷ��Ϣ
		response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		response.addHeader( "Expires", "0" );
		response.addHeader( "Pragma", "no-cache" );
		PrintWriter out = response.getWriter();
		out.println( "<?xml version=\"1.0\" encoding=\"utf-8\"?>" );
		out.println( "<userName>" );
		String name = request.getParameter( "name" );//��ȡ�û���
		
		if(Encrypt.isValidInput(name)){//�ж��û����Ƿ�Ϸ�����SQLע�빥��
			String code= request.getParameter( "code" );//��ȡ��֤��
			String codeSession =(String) request.getSession().getAttribute("rand");
			if(codeSession!=null){
			if(codeSession.equals(code)){//�ж���֤���Ƿ���ȷ
				boolean result=UserDao.getInstance().getUserByName(name.trim());//�������ݿ��Ƿ���ڸ��û���
				if(result==false) 
					out.println("<checkName id=\"ok\"/>");
				else
					out.println("<iteranceUserName>"+name+"</iteranceUserName>");
			}else{
				
				out.println("<errorCode id=\"ok\"/>");
			}
			}else{
				out.println("<errorCode id=\"ok\"/>");
			}

		}else{
			
			out.println("<errorName id=\"ok\"/>");
		}
		
		out.println( "</userName>" );
	}
	/**
	 *  �����û�ע��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");	//�û���
		String userpwd = request.getParameter("userpwd");	//����
		String email = request.getParameter("email");		
		String question=request.getParameter("question");
		String answer=request.getParameter("answer");
		
		if(!Encrypt.isValidInput(username)){
			response.sendRedirect("Fail.jsp");
			return;//�����ֹ�ڿ��滻���ݣ���SQLע�룻
		}
		User user = new User();
		user.setUsername(username.trim());
		user.setPassword(Encrypt.encodeMD5(userpwd));				//ʹ��MD5���������
		user.setEmail(email);
		user.setQuestion(question);
		user.setAnswer(answer);
		
		boolean res=UserDao.getInstance().saveUser(user);//����ע����Ϣ
		if(res){
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", user);
			response.sendRedirect("index.jsp");
		}else{
			response.sendRedirect("Fail.jsp");
		}
	}
	/**
	 *  �����û���¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");	//�û���
		String userpwd = request.getParameter("userpwd");	//����
		
		if(!Encrypt.isValidInput(username)){
			response.sendRedirect("Fail.jsp");//��ֹ�ڿͷǷ�ע���ַ�
			return ;
		}
		User user = UserDao.getInstance().getUserInforByNameAndPassword(username, Encrypt.encodeMD5(userpwd));
	
		if(user!=null){
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", user);
			response.sendRedirect("index.jsp");
		}else{
			response.sendRedirect("Fail.jsp");
		}
	}
	/**
	 * �ж��û����Ƿ����,�ж��û������Ƿ��зǷ��ַ����ж���֤���Ƿ�������ȷ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/xml;charset=UTF-8" );//������Ӧ��ʽ
		//������Ӧͷ��Ϣ
		response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		response.addHeader( "Expires", "0" );
		response.addHeader( "Pragma", "no-cache" );
		PrintWriter out = response.getWriter();
		out.println( "<?xml version=\"1.0\" encoding=\"utf-8\"?>" );
		out.println( "<userName>" );
		String name = request.getParameter( "name" );//��ȡ�û���
		
		if(Encrypt.isValidInput(name)){//�ж��û����Ƿ�Ϸ�����SQLע�빥��
			String code= request.getParameter( "code" );//��ȡ��֤��
			String password=request.getParameter("userpwd");
			String codeSession =(String) request.getSession().getAttribute("rand");
			if(codeSession!=null){
			  if(codeSession.equals(code)){//�ж���֤���Ƿ���ȷ
				boolean result=UserDao.getInstance().getUserByNameAndPassword(name.trim(),Encrypt.encodeMD5(password));//�������ݿ��Ƿ���ڸ��û���
				if(result==false){ 
					out.println("<errorNameOrPassword id=\"ok\"/>");
				}
				else{
					out.println("<existUser>"+name+"</existUser>");
				
				}
			  }else{
				
				out.println("<errorCode id=\"ok\"/>");
			 }
			}else{
				out.println("<errorCode id=\"ok\"/>");
			}

		}else{
			
			out.println("<errorName id=\"ok\"/>");
		}
		
		out.println( "</userName>" );
	}
	/**
	 * �����û�ע������
	 * **/
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null!=request.getSession().getAttribute("loginUser")){
			System.out.println("�û�session��������");
			request.getSession().invalidate();//ʵ��session�Ĳ���
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}

