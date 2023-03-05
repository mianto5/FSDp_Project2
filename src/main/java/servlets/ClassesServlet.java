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
 * Servlet implementation class ClassesServlet
 */
@WebServlet("/classes")
public class ClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<LAclass> classList = dbcom.getAllClasses();
			request.setAttribute("classList", classList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("classes.jsp");
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
		String cname = request.getParameter("cname");
		
		System.out.println(cid);
		System.out.println(cname);
		
		if(cid == null || cid.isEmpty() || cname == null || cname.isEmpty())
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("classes.jsp");
			request.setAttribute("message", "No box should stay empty.");
			request.setAttribute("cid", cid);
			request.setAttribute("cname", cname);
			dispatcher.forward(request, response);

			return;
		}
		
		LAclass cl = new LAclass(cid, cname);
		
		try {
			if(dbcom.addClass(cl)){
				// take me to which page? login page
				response.sendRedirect("classes");
			} else {
				//response.sendRedirect("register.html");
				RequestDispatcher dispatcher = request.getRequestDispatcher("classes");
				request.setAttribute("message", "Class not added.");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			// keep me on registration page
			response.sendRedirect("index.jsp");
			
		}
	}

}
