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
		String action = request.getParameter("action");//获取设置的请求动作：注册、登陆或更新
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
	 * 判断用户名是否存在,判断用户名中是否有非法字符，判断验证码是否输入正确
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void checkName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/xml;charset=UTF-8" );//设置响应格式
		//设置响应头信息
		response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		response.addHeader( "Expires", "0" );
		response.addHeader( "Pragma", "no-cache" );
		PrintWriter out = response.getWriter();
		out.println( "<?xml version=\"1.0\" encoding=\"utf-8\"?>" );
		out.println( "<userName>" );
		String name = request.getParameter( "name" );//获取用户名
		
		if(Encrypt.isValidInput(name)){//判断用户名是否合法，防SQL注入攻击
			String code= request.getParameter( "code" );//获取验证码
			String codeSession =(String) request.getSession().getAttribute("rand");
			if(codeSession!=null){
			if(codeSession.equals(code)){//判断验证码是否正确
				boolean result=UserDao.getInstance().getUserByName(name.trim());//查找数据库是否存在该用户名
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
	 *  处理用户注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");	//用户名
		String userpwd = request.getParameter("userpwd");	//密码
		String email = request.getParameter("email");		
		String question=request.getParameter("question");
		String answer=request.getParameter("answer");
		
		if(!Encrypt.isValidInput(username)){
			response.sendRedirect("Fail.jsp");
			return;//这里防止黑客替换数据，防SQL注入；
		}
		User user = new User();
		user.setUsername(username.trim());
		user.setPassword(Encrypt.encodeMD5(userpwd));				//使用MD5将密码加密
		user.setEmail(email);
		user.setQuestion(question);
		user.setAnswer(answer);
		
		boolean res=UserDao.getInstance().saveUser(user);//保存注册信息
		if(res){
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", user);
			response.sendRedirect("index.jsp");
		}else{
			response.sendRedirect("Fail.jsp");
		}
	}
	/**
	 *  处理用户登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");	//用户名
		String userpwd = request.getParameter("userpwd");	//密码
		
		if(!Encrypt.isValidInput(username)){
			response.sendRedirect("Fail.jsp");//防止黑客非法注入字符
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
	 * 判断用户名是否存在,判断用户名中是否有非法字符，判断验证码是否输入正确
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/xml;charset=UTF-8" );//设置响应格式
		//设置响应头信息
		response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		response.addHeader( "Expires", "0" );
		response.addHeader( "Pragma", "no-cache" );
		PrintWriter out = response.getWriter();
		out.println( "<?xml version=\"1.0\" encoding=\"utf-8\"?>" );
		out.println( "<userName>" );
		String name = request.getParameter( "name" );//获取用户名
		
		if(Encrypt.isValidInput(name)){//判断用户名是否合法，防SQL注入攻击
			String code= request.getParameter( "code" );//获取验证码
			String password=request.getParameter("userpwd");
			String codeSession =(String) request.getSession().getAttribute("rand");
			if(codeSession!=null){
			  if(codeSession.equals(code)){//判断验证码是否正确
				boolean result=UserDao.getInstance().getUserByNameAndPassword(name.trim(),Encrypt.encodeMD5(password));//查找数据库是否存在该用户名
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
	 * 处理用户注销操作
	 * **/
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null!=request.getSession().getAttribute("loginUser")){
			System.out.println("用户session进行销毁");
			request.getSession().invalidate();//实行session的操作
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}

