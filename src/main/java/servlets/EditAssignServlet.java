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

@WebServlet("/editAssign")
public class EditAssignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   

    public EditAssignServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int aid = Integer.parseInt(request.getParameter("aid"));
		String cid = request.getParameter("cid");
		int sbid = Integer.parseInt(request.getParameter("sbid"));
		int tid = Integer.parseInt(request.getParameter("tid"));
		
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
			dbcom.updateAssignment(as);
			response.sendRedirect("assign");
		}catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
	}
}
