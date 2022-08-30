
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/doCreateData")
public class DoCreateData_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		String date = request.getParameter("date");
		String item = request.getParameter("item");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String notes = request.getParameter("notes");
		int user = Integer.parseInt(request.getParameter("user"));
		DataControl dc = new DataControl(type, date, item, amount, notes, user);
		DataBase db = new DataBase();
		DataControl data = db.createData(dc);
		String json = "{\"message\":\"" + data.getMessage() + "\"}";
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
