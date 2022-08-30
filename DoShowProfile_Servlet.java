import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/doShowProfile")
public class DoShowProfile_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int user = Integer.parseInt(request.getParameter("user"));
		DataControl dc = new DataControl(user);
		DataBase db = new DataBase();
		DataControl data = db.showProfile(dc);
		String json = "{\"username\":\"" + data.getUsername() + "\",\"password\":\"" + data.getPassword()
				+ "\",\"nickname\":\"" + data.getNickname() + "\",\"email\":\"" + data.getEmail() + "\",\"gender\":\""
				+ data.getGender() + "\"}";
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
