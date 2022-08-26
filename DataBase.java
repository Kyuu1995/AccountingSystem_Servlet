import static java.lang.System.*;
import java.sql.*;
import java.util.*;

public class DataBase {

	// ---- Create ----
	public void create(DataControl dc) {
		String create = "insert into " + dc.getTable()
				+ " (date, item_no, amount, notes,user_no) values (?, ?, ?, ? ,?)";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(create);
			ps.setString(1, dc.getDate());
			ps.setString(2, dc.getItem());
			ps.setInt(3, dc.getAmount());
			ps.setString(4, dc.getNotes());
			ps.setInt(5, dc.getUser());
			ps.executeUpdate();
		} catch (SQLException e) {
			err.println("Create Error....");
			// e.printStackTrace();
		}
	}

	// ---- Update ----
	public void update(DataControl dc, DataControl dcNew) {
		try {
			String select = "select " + dc.getTable() + "_no from " + dc.getTable()
					+ " where date = ?, item_no = ?, amount = ?, notes = ?";
			PreparedStatement pst = DataConnection.getConnection().prepareStatement(select);
			pst.setString(1, dc.getDate());
			pst.setString(2, dc.getItem());
			pst.setInt(3, dc.getAmount());
			pst.setString(4, dc.getNotes());
			ResultSet rs = pst.executeQuery();
			try {
				String update = "update " + dc.getTable() + " set date = ?, item_no = ?, amount = ?, notes = ? where "
						+ dc.getTable() + "_no = ?";
				PreparedStatement ps = DataConnection.getConnection().prepareStatement(update);
				ps.setString(1, dc.getDate());
				ps.setString(2, dc.getItem());
				ps.setInt(3, dc.getAmount());
				ps.setString(4, dc.getNotes());
				ps.setString(5, rs.getString(1));
			} catch (Exception e) {
				err.println("Update Error....");
				// e.printStackTrace();
			}
		} catch (SQLException e) {
			err.println("UpdateSelect Error....");
			// e.printStackTrace();
		}
	}

	// ---- Select ----
	public List<DataControl> select(DataControl dc) {
		String select = "select * from " + dc.getTable() + " where user_no = ? and date like ?";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(select);
			ps.setInt(1, dc.getUser());
			ps.setString(2, dc.getNow() + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DataControl data = toData(rs, dc);
				allData.add(data);
			}
		} catch (SQLException e) {
			err.println("Select Error....");
			e.printStackTrace();
		}
		return allData;
	}

	// ---- SelectAll ----
	public List<DataControl> selectAll(DataControl dc) {
		String selectAll = "select * from " + dc.getTable() + " where user_no = ?";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(selectAll);
			ps.setInt(1, dc.getUser());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DataControl data = toData(rs, dc);
				allData.add(data);
			}
		} catch (SQLException e) {
			err.println("DateSelect Error....");
			e.printStackTrace();
		}
		return allData;
	}

	// ---- SelectDate ----
	public List<DataControl> selectDate(DataControl dc) {
		String selectDate = "select * from " + dc.getTable() + " where user_no = ? and date between ? and ?";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(selectDate);
			ps.setInt(1, dc.getUser());
			ps.setString(2, dc.getStart());
			ps.setString(3, dc.getEnd());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DataControl data = toData(rs, dc);
				allData.add(data);
			}
		} catch (SQLException e) {
			err.println("SelectDate Error....");
			e.printStackTrace();
		}
		return allData;
	}

	// ---- SetResult ----
	public DataControl toData(ResultSet rs, DataControl dc) throws SQLException {
		String sql = "select item_" + dc.getTable() + "_name from item_" + dc.getTable() + " where item_"
				+ dc.getTable() + "_no = ?";
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
