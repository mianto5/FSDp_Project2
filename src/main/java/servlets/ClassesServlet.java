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
import com.hibernate.DBcommunication;

@WebServlet("/classes")
public class ClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   

    public ClassesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<LAclass> classList = dbcom.getAllClasses();
			request.setAttribute("classList", classList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("classes.jsp");
			dispatcher.forward(request, response);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		
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
				response.sendRedirect("classes");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("classes");
				request.setAttribute("message", "Class not added.");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
	}
}
