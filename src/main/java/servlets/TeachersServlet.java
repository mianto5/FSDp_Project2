package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.LAteacher;
import com.hibernate.DBcommunication;

@WebServlet("/teachers")
public class TeachersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   

    public TeachersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<LAteacher> teacherList = dbcom.getAllTeachers();
			request.setAttribute("teacherList", teacherList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("teachers.jsp");
			dispatcher.forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		
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
				response.sendRedirect("teachers");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("teacher");
				request.setAttribute("message", "Registration unsuccessful.");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
	}
}
