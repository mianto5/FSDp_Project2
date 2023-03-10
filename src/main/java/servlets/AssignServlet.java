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
 * Servlet implementation class AssignServlet
 */
@WebServlet("/assign")
public class AssignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   
    
    public AssignServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<LAassign> assignList = dbcom.getAllAssignments();
			List<LAclass> classList = dbcom.getAllClasses();
			List<LAsubject> subjectList = dbcom.getAllSubjects();
			List<LAteacher> teacherList = dbcom.getAllTeachers();
			request.setAttribute("assignList", assignList);
			request.setAttribute("classList", classList);
			request.setAttribute("subjectList", subjectList);
			request.setAttribute("teacherList", teacherList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("assign.jsp");
			dispatcher.forward(request, response);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		int sbid = Integer.parseInt(request.getParameter("sbid"));
		int tid = Integer.parseInt(request.getParameter("tid"));
		
		if(cid == null || cid.isEmpty() || sbid == 0 || tid == 0)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("assign.jsp");
			request.setAttribute("message", "No box should stay empty.");
			request.setAttribute("cid", cid);
			request.setAttribute("sbid", sbid);
			request.setAttribute("tid", tid);
			dispatcher.forward(request, response);

			return;
		}
		
		LAassign as = new LAassign(cid, sbid, tid);
		
		try {
			if(dbcom.addAssignment(as)){
				response.sendRedirect("assign");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("assign");
				request.setAttribute("message", "Assignment unsuccessful.");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
	}
}
