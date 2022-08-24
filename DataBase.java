import static java.lang.System.*;
import java.sql.*;
import java.util.*;

public class DataBase {

	// ---- Create ----
	public void create(DataControl dc) {
		String sql = "insert into " + dc.getTable() + " (date, item_no, amount, notes) values (?, ?, ?, ?)";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(sql);
			ps.setString(1, dc.getDate());
			ps.setString(2, dc.getItem());
			ps.setInt(3, dc.getAmount());
			ps.setString(4, dc.getNotes());
			ps.executeUpdate();
		} catch (SQLException e) {
			err.println("Create Error....");
			// e.printStackTrace();
		}
	}

	// ---- Select ----
	public List<DataControl> select(DataControl dc) {
		String sql = "select * from " + dc.getTable();
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			Statement st = DataConnection.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				DataControl data = toData(rs, dc);
				allData.add(data);
			}
		} catch (SQLException e) {
			err.println("Select Error....");
			// e.printStackTrace();
		}
		return allData;
	}

	// ---- setResult ----
	public DataControl toData(ResultSet rs, DataControl dc) throws SQLException {
		String sql = "select item_income_name from item_" + dc.getTable() + " where item_income_no = ?";
		PreparedStatement ps = DataConnection.getConnection().prepareStatement(sql);
		ps.setString(1, rs.getString(3));
		ResultSet result = ps.executeQuery();
		result.next();
		DataControl data = new DataControl();
		data.setDate(rs.getString(2));
		data.setItem(result.getString(1));
		data.setAmount(rs.getInt(4));
		data.setNotes(rs.getString(5));
		return data;
	}

}
