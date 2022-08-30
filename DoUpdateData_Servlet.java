
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/doUpdateData")
public class DoUpdateData_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		String type = request.getParameter("type");
		String date = request.getParameter("date");
		String item = request.getParameter("item");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String notes = request.getParameter("notes");
		int user = Integer.parseInt(request.getParameter("user"));
		DataControl dc = new DataControl(type, date, item, amount, notes, user);
		String dateNew = request.getParameter("dateNew");
		String itemNew = request.getParameter("itemNew");
		int amountNew = Integer.parseInt(request.getParameter("amountNew"));
		String notesNew = request.getParameter("notesNew");
		DataControl dcNew = new DataControl(type, dateNew, itemNew, amountNew, notesNew, user);
		DataBase db = new DataBase();
		db.updateData(dc, dcNew);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}