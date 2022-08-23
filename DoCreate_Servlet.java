
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
		response.setContentType("text/html;charset=UTF-8");
		String table = request.getParameter("table");
		String date = request.getParameter("date");
		String item = request.getParameter("item");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String notes = request.getParameter("notes");
		DataControl dc = new DataControl(table, date, item, amount, notes);
		DataBase db = new DataBase();
		db.create(dc);
		System.out.println(dc.getTable());
		System.out.println(dc.getDate());
		System.out.println(dc.getItem());
		System.out.println(dc.getAmount());
		System.out.println(dc.getNotes());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
