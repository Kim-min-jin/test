package com.newlecture.web.controller.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;


@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			int id = Integer.parseInt(request.getParameter("id"));
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String sql = "SELECT * FROM NOTICE WHERE ID=?";

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection(url,"aa","1111");
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, id);
				ResultSet rs = st.executeQuery();	
				
				
				rs.next();
		
					String title = rs.getString("TITLE");
					String writerId = rs.getString("WRITER_ID");
					Date regdate = rs.getDate("REGDATE");
					int hit = rs.getInt("HIT");
					String files = rs.getString("FILES");
					String content = rs.getString("CONTENT");

					
					Notice notice = new Notice(
							id,
							title,
							writerId,
							regdate,
							hit,
							files,
							content
							);			
					
					request.setAttribute("n", notice);
					
					
					rs.close();
					st.close();
					conn.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				
			
				request
				.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp")
				.forward(request, response);
		}

		}
