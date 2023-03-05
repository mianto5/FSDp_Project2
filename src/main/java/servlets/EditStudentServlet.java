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

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   

    public EditStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int stid = Integer.parseInt(request.getParameter("stid"));
		try {
			LAstudent student = dbcom.getStudentById(stid); 
			List<LAclass> classList = dbcom.getAllClasses();
			RequestDispatcher dispatcherSt = request.getRequestDispatcher("editStudent.jsp");
			request.setAttribute("student", student);
			request.setAttribute("classList", classList);
			dispatcherSt.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int stid = Integer.parseInt(request.getParameter("stid"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String cid = request.getParameter("cid");
		
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
			response.sendRedirect("index.jsp");
		}
	}
}
