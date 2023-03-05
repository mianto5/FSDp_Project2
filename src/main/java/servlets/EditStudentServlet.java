package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.LAassign;
import com.bean.LAclass;
import com.bean.LAstudent;
import com.bean.LAsubject;
import com.bean.LAteacher;
import com.hibernate.DBcommunication;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditStudentServlet() {
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
			List<LAclass> classList = dbcom.getAllClasses();
			RequestDispatcher dispatcherSt = request.getRequestDispatcher("editStudent.jsp");
			request.setAttribute("student", student);
			request.setAttribute("classList", classList);
			dispatcherSt.forward(request, response);
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
		
		int stid = Integer.parseInt(request.getParameter("stid"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String cid = request.getParameter("cid");
		
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(cid);
		
		if(cid == null || cid.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("editStudent.jsp");
			request.setAttribute("message", "Class ID cannot be empty.");
			request.setAttribute("cid", cid);
			dispatcher.forward(request, response);

			return;
			}
		
		LAstudent st = new LAstudent(fname, lname, cid);
		st.setStid(stid);
		
		try {
			dbcom.updateStudent(st);
			response.sendRedirect("students");
		}catch (Exception e) {
			// keep me on registration page
			response.sendRedirect("index.jsp");
		}
	}
}
