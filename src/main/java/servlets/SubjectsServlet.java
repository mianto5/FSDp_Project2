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
import com.bean.LAsubject;
import com.hibernate.DBcommunication;

/**
 * Servlet implementation class SubjectsServlet
 */
@WebServlet("/subjects")
public class SubjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<LAsubject> subjectList = dbcom.getAllSubjects();
			request.setAttribute("subjectList", subjectList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("subjects.jsp");
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
		String sname = request.getParameter("sname");
		
		System.out.println(sname);
		
		if(sname == null || sname.isEmpty())
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("subjects.jsp");
			request.setAttribute("message", "Subject name should not stay empty.");
			request.setAttribute("sname", sname);
			dispatcher.forward(request, response);

			return;
		}
		
		LAsubject sb = new LAsubject(sname);
		
		try {
			if(dbcom.addSubject(sb)){
				// take me to which page? login page
				response.sendRedirect("subjects");
			} else {
				//response.sendRedirect("register.html");
				RequestDispatcher dispatcher = request.getRequestDispatcher("subjects");
				request.setAttribute("message", "Subject not added.");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			// keep me on registration page
			response.sendRedirect("index.jsp");
			
		}
	}

}
