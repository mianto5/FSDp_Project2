package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.LAstudent;
import com.hibernate.DBcommunication;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int stid = Integer.parseInt(request.getParameter("stid"));
		try {
			LAstudent student = dbcom.getStudentById(stid); 
			RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
			request.setAttribute("student", student);
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
		
		if(cid == null || cid.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
			request.setAttribute("message", "Class ID cannot be empty.");
			request.setAttribute("cid", cid);
			dispatcher.forward(request, response);

			return;
			}
		
		LAstudent st = new LAstudent(fname, lname, cid);
		
		try {
			if(dbcom.updateStudent(st)){
				// take me to which page? login page
				response.sendRedirect("students");
			} else {
				//response.sendRedirect("register.html");
				RequestDispatcher dispatcher = request.getRequestDispatcher("students");
				request.setAttribute("message", "Update unsuccessful.");
				dispatcher.forward(request, response);
			}
		}catch (Exception e) {
			// keep me on registration page
			response.sendRedirect("index.jsp");
		}
	}
}
