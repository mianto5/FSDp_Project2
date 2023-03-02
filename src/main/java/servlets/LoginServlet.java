package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");//http1.0
		response.setHeader("Pragma", "0");//proxies
		
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if(user!=null && user.equals("admin") 
				&& password!=null && password.equals("admin") )
		{
			HttpSession session = request.getSession();
			session.setAttribute("id", user);
			response.sendRedirect("index.jsp");
			return;
		}
		
		/*try {
			if(db.validateCustomer(email, password)) {
				request.setAttribute("email", email);
				// url rewriting
				HttpSession session = request.getSession();
				session.setAttribute("id", email);
				response.sendRedirect("dashboard.jsp");
				// if request is dispatched from post it invokes doPost of another servlet
//				RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard");
//				request.setAttribute("email", email);
//				dispatcher.forward(request, response);
			}
			else {
				response.sendRedirect("login.jsp?error=Invalid Credentials");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
//				out.println("invalid credentials");
//				dispatcher.include(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("login.jsp?error=Something Went Wrong");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
//			out.println("Something went wrong, please contact the admin");
//			dispatcher.include(request, response);
		}*/
	}

}
