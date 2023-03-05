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

@WebServlet("/classReport")
public class ClassReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   
    public ClassReportServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<LAclass> classList = dbcom.getAllClasses();
			List<LAstudent> studentList = dbcom.getAllStudents();
			List<LAteacher> teacherList = dbcom.getAllTeachers();
			List<LAsubject> subjectList = dbcom.getAllSubjects();
			List<LAassign> assignList = dbcom.getAllAssignments();
			
			request.setAttribute("classList", classList);	
			request.setAttribute("studentList", studentList);
			request.setAttribute("teacherList", teacherList);
			request.setAttribute("subjectList", subjectList);
			request.setAttribute("assignList", assignList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("classReport.jsp");
			dispatcher.forward(request, response);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
