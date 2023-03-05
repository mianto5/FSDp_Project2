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

@WebServlet("/subjects")
public class SubjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBcommunication dbcom = new DBcommunication();   

    public SubjectsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<LAsubject> subjectList = dbcom.getAllSubjects();
			request.setAttribute("subjectList", subjectList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("subjects.jsp");
			dispatcher.forward(request, response);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sname = request.getParameter("sname");
		
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
				response.sendRedirect("subjects");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("subjects");
				request.setAttribute("message", "Subject not added.");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
	}
}
