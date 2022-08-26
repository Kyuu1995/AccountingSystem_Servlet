import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/doSelectAll")
public class DoSelectAll_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int user = Integer.parseInt(request.getParameter("user"));
		String table = request.getParameter("table");
		DataControl dc = new DataControl(user, table);
		DataBase db = new DataBase();
		List<DataControl> data = db.selectAll(dc);
		int sum = 0;
		String json = "[";
		for (int i = 0; i < data.size(); i++) {
			json += "{\"date\":\"" + data.get(i).getDate() + "\"";
			json += ",\"item\":\"" + data.get(i).getItem() + "\"";
			json += ",\"amount\":\"" + data.get(i).getAmount() + "\"";
			json += ",\"notes\":\"" + data.get(i).getNotes() + "\"}";
			sum += data.get(i).getAmount();
			if (i < data.size() - 1) {
				json += ",";
			}
		}
		json += ",{\"total\":\"" + sum + "\"}]";
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
