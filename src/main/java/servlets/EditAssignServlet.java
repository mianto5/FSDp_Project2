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
import com.bean.LAsubject;
import com.bean.LAteacher;
import com.hibernate.DBcommunication;

/**
 * Servlet implementation class EditAssignServlet
 */
@WebServlet("/editAssign")
public class EditAssignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAssignServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			LAassign assign = dbcom.getAssignmentById(aid); 
			List<LAsubject> subjectList = dbcom.getAllSubjects();
			List<LAteacher> teacherList = dbcom.getAllTeachers();
			RequestDispatcher dispatcherAs = request.getRequestDispatcher("editAssign.jsp");
			request.setAttribute("assign", assign);
			request.setAttribute("subjectList", subjectList);
			request.setAttribute("teacherList", teacherList);
			dispatcherAs.forward(request, response);
			
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
		int aid = Integer.parseInt(request.getParameter("aid"));
		String cid = request.getParameter("cid");
		int sbid = Integer.parseInt(request.getParameter("sbid"));
		int tid = Integer.parseInt(request.getParameter("tid"));
		
		System.out.println(aid);
		System.out.println(cid);
		System.out.println(sbid);
		System.out.println(tid);
		
		if(sbid == 0 || tid == 0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("editAssign.jsp");
			request.setAttribute("message", "No box can stay empty.");
			request.setAttribute("sbid", sbid);
			request.setAttribute("tid", tid);
			dispatcher.forward(request, response);

			return;
			}
		
		LAassign as = new LAassign(cid, sbid, tid);
		as.setAid(aid);
		
		try {
			if(dbcom.updateAssignment(as)){
				// take me to which page? login page
				RequestDispatcher dispatcher = request.getRequestDispatcher("assign");
				request.setAttribute("message", "Update successful.");
				dispatcher.forward(request, response);
			} else {
				//response.sendRedirect("register.html");
				RequestDispatcher dispatcher = request.getRequestDispatcher("assign");
				request.setAttribute("message", "Update unsuccessful.");
				dispatcher.forward(request, response);
			}
		}catch (Exception e) {
			// keep me on registration page
			response.sendRedirect("index.jsp");
		}
	}

}
