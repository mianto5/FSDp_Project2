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
 * Servlet implementation class AssignServlet
 */
@WebServlet("/assign")
public class AssignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<LAassign> assignList = dbcom.getAllAssignments();
			request.setAttribute("assignList", assignList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("assign.jsp");
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
		String cid = request.getParameter("cid");
		int sbid = Integer.parseInt(request.getParameter("sbid"));
		int tid = Integer.parseInt(request.getParameter("tid"));
		
		System.out.println(cid);
		System.out.println(sbid);
		System.out.println(tid);
		
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
				// take me to which page? login page
				response.sendRedirect("assign");
			} else {
				//response.sendRedirect("register.html");
				RequestDispatcher dispatcher = request.getRequestDispatcher("assign");
				request.setAttribute("message", "Assignment unsuccessful.");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			// keep me on registration page
			response.sendRedirect("index.jsp");
			
		}
	}

}
