
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/doUpdate")
public class DoUpdate_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int user = Integer.parseInt(request.getParameter("user"));
		String table = request.getParameter("table");
		String date = request.getParameter("date");
		String item = request.getParameter("item");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String notes = request.getParameter("notes");
		DataControl dc = new DataControl(user, table, date, item, amount, notes);
		String dateNew = request.getParameter("dateNew");
		String itemNew = request.getParameter("itemNew");
		int amountNew = Integer.parseInt(request.getParameter("amountNew"));
		String notesNew = request.getParameter("notesNew");
		DataControl dcNew = new DataControl(user, table, dateNew, itemNew, amountNew, notesNew);
		DataBase db = new DataBase();
		db.update(dc, dcNew);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
