package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.LAstudent;
import com.bean.LAteacher;
import com.hibernate.DBcommunication;

/**
 * Servlet implementation class TeachersServlet
 */
@WebServlet("/TeachersServlet")
public class TeachersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeachersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<LAteacher> teacherList = dbcom.getAllTeachers();
			for(LAteacher teacher: teacherList) {
				System.out.println(teacher);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("teachers.jsp");
			request.setAttribute("teacherList", teacherList);
			dispatcher.forward(request, response);
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("teacher.jsp");
	}

}
