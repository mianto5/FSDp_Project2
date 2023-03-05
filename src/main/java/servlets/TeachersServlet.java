package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.LAclass;
import com.bean.LAstudent;
import com.bean.LAteacher;
import com.hibernate.DBcommunication;

/**
 * Servlet implementation class TeachersServlet
 */
@WebServlet("/teachers")
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
		try {
			List<LAteacher> teacherList = dbcom.getAllTeachers();
			request.setAttribute("teacherList", teacherList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("teachers.jsp");
			dispatcher.forward(request, response);
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		
		System.out.println(fname);
		System.out.println(lname);
		
		if(fname == null || fname.isEmpty() || lname == null || lname.isEmpty())
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("teachers.jsp");
			request.setAttribute("message", "No box should stay empty.");
			request.setAttribute("fname", fname);
			request.setAttribute("lname", lname);
			dispatcher.forward(request, response);

			return;
		}
		
		LAteacher te = new LAteacher(fname, lname);
		
		try {
			if(dbcom.addTeacher(te)){
				// take me to which page? login page
				response.sendRedirect("teachers");
			} else {
				//response.sendRedirect("register.html");
				RequestDispatcher dispatcher = request.getRequestDispatcher("teacher");
				request.setAttribute("message", "Registration unsuccessful.");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			// keep me on registration page
			response.sendRedirect("index.jsp");
			
		}
	}

}
