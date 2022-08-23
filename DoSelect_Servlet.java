import java.io.IOException;
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
		response.setContentType("text/html;charset=UTF-8");
		String table = request.getParameter("table");
		DataControl dc = new DataControl(table);
		DataBase db = new DataBase();
		List<DataControl> data = db.select(dc);
		for (int i = 0; i < data.size(); i++) {
			System.out.println(
					data.get(i).getDate() + data.get(i).getItem() + data.get(i).getAmount() + data.get(i).getNotes());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
