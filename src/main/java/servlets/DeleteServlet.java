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
import com.bean.LAstudent;
import com.hibernate.DBcommunication;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ent = request.getParameter("ent");
		System.out.println(ent);
		
		List<LAassign> assignList = dbcom.getAllAssignments();
		List<LAstudent> studentList = dbcom.getAllStudents();
		
		switch(ent) {
			case "as":
				int aid = Integer.parseInt(request.getParameter("aid"));
				RequestDispatcher dispatcherAs = request.getRequestDispatcher("assign");
				try {
					dbcom.deleteAssignById(aid); 
					request.setAttribute("message", "Assignment deleted successfully.");
					dispatcherAs.forward(request, response);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Assignment not deleted.");
					dispatcherAs.forward(request, response);
					e.printStackTrace();
				}
				break;
			case "cl":
				String cid = request.getParameter("cid");
				RequestDispatcher dispatcherCl = request.getRequestDispatcher("classes");
				for(LAstudent s:studentList) {
					if(cid.equals(s.getCid())) {
						request.setAttribute("message", "Class cannot be deleted, there are still students in the class.");
						dispatcherCl.forward(request, response);
					}
				}
				try {
					dbcom.deleteClassById(cid); 
					request.setAttribute("message", "Class deleted successfully.");
					dispatcherCl.forward(request, response);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Class not deleted.");
					dispatcherCl.forward(request, response);
					e.printStackTrace();
				}
				break;
			case "st":
				int stid = Integer.parseInt(request.getParameter("stid"));
				RequestDispatcher dispatcherSt = request.getRequestDispatcher("students");
				try {
					dbcom.deleteStudentById(stid); 
					request.setAttribute("message", "Student deleted successfully.");
					dispatcherSt.forward(request, response);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Student not deleted.");
					dispatcherSt.forward(request, response);
					e.printStackTrace();
				}
				break;
			case "sb":
				int sbid = Integer.parseInt(request.getParameter("sbid"));
				RequestDispatcher dispatcherSb = request.getRequestDispatcher("subjects");
				for(LAassign a:assignList) {
					if(sbid == a.getSbid()) {
						request.setAttribute("message", "Subject cannot be deleted, it is still assigned to classes.");
						dispatcherSb.forward(request, response);
					}
				}
				try {
					dbcom.deleteSubjectById(sbid); 
					request.setAttribute("message", "Subject deleted successfully.");
					dispatcherSb.forward(request, response);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Subject not deleted.");
					dispatcherSb.forward(request, response);
					e.printStackTrace();
				}
				break;
			case "te":
				int tid = Integer.parseInt(request.getParameter("tid"));
				RequestDispatcher dispatcherTe = request.getRequestDispatcher("teachers");
				for(LAassign a:assignList) {
					if(tid == a.getTid()) {
						request.setAttribute("message", "Teacher cannot be removed, he/she is still assigned to classes.");
						dispatcherTe.forward(request, response);
					}
				}
				try {
					dbcom.deleteTeacherById(tid); 
					request.setAttribute("message", "Teacher deleted successfully.");
					dispatcherTe.forward(request, response);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Teacher not deleted.");
					dispatcherTe.forward(request, response);
					e.printStackTrace();
				}
				break;
			default:
				response.sendRedirect("index.jsp");
		}
	}
}
