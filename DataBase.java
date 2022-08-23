import static java.lang.System.*;
import java.sql.*;
import java.util.*;

public class DataBase {

	// ---- Create ----
	public void create(DataControl dc) {
		try {
			String sql = "insert into ? (date, item_no, amount, notes) values (?, ?, ?, ?)";
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(sql);
			ps.setString(1, dc.getTable());
			ps.setString(2, dc.getDate());
			ps.setString(3, dc.getItem());
			ps.setInt(4, dc.getAmount());
			ps.setString(5, dc.getNotes());
			ps.executeUpdate();
		} catch (SQLException e) {
			err.println("Create Error....");
		}
	}

	// ---- Select ----
	public List<DataControl> select(DataControl dc) {
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			String sql = "select * from ?";
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(sql);
			ps.setString(1, dc.getTable());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DataControl data = toData(rs);
				allData.add(data);
			}
			System.out.print(allData);
		} catch (SQLException e) {
			err.println("Select Error...." + dc.getTable());
		}
		return allData;
	}

	// ---- setResult ----
	public DataControl toData(ResultSet rs) throws SQLException {
		DataControl data = new DataControl();
		data.setDate(rs.getString(2));
		data.setItem(rs.getString(3));
		data.setAmount(rs.getInt(4));
		data.setNotes(rs.getString(5));
		return data;
	}

	public Connection conn() {
		return null;

	}
}
