
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/doUpdateProfile")
public class DoUpdateProfile_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int user = Integer.parseInt(request.getParameter("user"));
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		DataControl dc = new DataControl(user, nickname, email, gender);
		DataBase db = new DataBase();
		DataControl data = db.updateProfile(dc);
		String json = "{\"message\":\"" + data.getMessage() + "\"}";
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
