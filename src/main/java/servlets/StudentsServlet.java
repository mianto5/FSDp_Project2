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
import com.hibernate.DBcommunication;

/**
 * Servlet implementation class StudentsServlet
 */
@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			List<LAstudent> studentList = dbcom.getAllStudents();
			List<LAclass> classList = dbcom.getAllClasses();
			request.setAttribute("studentList", studentList);
			request.setAttribute("classList", classList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
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
		String cid = request.getParameter("cid");
		
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(cid);
		
		if(fname == null || fname.isEmpty() || lname == null || lname.isEmpty() || cid == null || cid.isEmpty())
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
			request.setAttribute("message", "No box should stay empty.");
			request.setAttribute("fname", fname);
			request.setAttribute("lname", lname);
			request.setAttribute("cid", cid);
			dispatcher.forward(request, response);

			return;
		}
		
		LAstudent st = new LAstudent(fname, lname, cid);
		
		try {
			if(dbcom.addStudent(st)){
				// take me to which page? login page
				response.sendRedirect("students");
			} else {
				//response.sendRedirect("register.html");
				RequestDispatcher dispatcher = request.getRequestDispatcher("students");
				request.setAttribute("message", "Registration unsuccessful.");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			// keep me on registration page
			response.sendRedirect("index.jsp");
			
		}
		
		
	}

}
