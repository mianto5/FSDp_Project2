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

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   

    public StudentsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<LAstudent> studentList = dbcom.getAllStudents();
			List<LAclass> classList = dbcom.getAllClasses();
			request.setAttribute("studentList", studentList);
			request.setAttribute("classList", classList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
			dispatcher.forward(request, response);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String cid = request.getParameter("cid");
		
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
				response.sendRedirect("students");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("students");
				request.setAttribute("message", "Registration unsuccessful.");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
	}
}
