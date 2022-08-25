
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/doCreate")
public class DoCreate_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		int user = Integer.parseInt(request.getParameter("user"));
		String table = request.getParameter("table");
		String date = request.getParameter("date");
		String item = request.getParameter("item");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String notes = request.getParameter("notes");
		DataControl dc = new DataControl(user, table, date, item, amount, notes);
		DataBase db = new DataBase();
		db.create(dc);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
