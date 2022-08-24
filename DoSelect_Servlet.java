import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/doSelect")
public class DoSelect_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		String table = request.getParameter("table");
		DataControl dc = new DataControl(table);
		DataBase db = new DataBase();
		PrintWriter out = response.getWriter();
		List<DataControl> data = db.select(dc);
		String json = "[";
		for (int i = 0; i < data.size(); i++) {
			json += "{\"date\":\"" + data.get(i).getDate() + "\"";
			json += ",\"item\":\"" + data.get(i).getItem() + "\"";
			json += ",\"amount\":\"" + data.get(i).getAmount() + "\"";
			json += ",\"notes\":\"" + data.get(i).getNotes() + "\"}";
			if (i < data.size() - 1) {
				json += ",";
			}
		}
		json += "]";
		out.print(json);
		System.out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
