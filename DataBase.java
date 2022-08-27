import static java.lang.System.*;
import java.sql.*;
import java.util.*;

public class DataBase {

	// ---- Create ----
	public void create(DataControl dc) {
		String create = "insert into account(type_no, date, item_no, amount, notes, user_no) values(?, ?, ?, ?, ? ,?)";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(create);
			ps.setString(1, dc.getType());
			ps.setString(2, dc.getDate());
			ps.setInt(3, Integer.parseInt(dc.getItem()));
			ps.setInt(4, dc.getAmount());
			ps.setString(5, dc.getNotes());
			ps.setInt(6, dc.getUser());
			ps.executeUpdate();
		} catch (SQLException e) {
			err.println("Create Error....");
			// e.printStackTrace();
		}
	}

	// ---- Update ----
	public void update(DataControl dc, DataControl dcNew) {
		try {
			String updateSelect = "select account_no from account where type = ?, date = ?, item_no = ?, amount = ?, notes = ?, user = ?";
			PreparedStatement pst = DataConnection.getConnection().prepareStatement(updateSelect);
			pst.setString(1, dc.getType());
			pst.setString(2, dc.getDate());
			pst.setInt(3, Integer.parseInt(dc.getItem()));
			pst.setInt(4, dc.getAmount());
			pst.setString(5, dc.getNotes());
			pst.setInt(6, dc.getUser());
			ResultSet rs = pst.executeQuery();
			try {
				String update = "update account set date = ?, item_no = ?, amount = ?, notes = ? where account_no = ?";
				PreparedStatement ps = DataConnection.getConnection().prepareStatement(update);
				ps.setString(1, dcNew.getDate());
				ps.setInt(2, Integer.parseInt(dc.getItem()));
				ps.setInt(3, dcNew.getAmount());
				ps.setString(4, dcNew.getNotes());
				ps.setInt(5, rs.getInt(1));
			} catch (Exception e) {
				err.println("Update Error....");
				// e.printStackTrace();
			}
		} catch (SQLException e) {
			err.println("UpdateSelect Error....");
			// e.printStackTrace();
		}
	}

	// ---- SelectAll ----
	public List<DataControl> selectAll(DataControl dc) {
		String selectAll = "select * from account where user_no = ? order by date";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(selectAll);
			ps.setInt(1, dc.getUser());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DataControl data = toData(rs, typeName(rs), itemName(rs));
				allData.add(data);
			}
		} catch (SQLException e) {
			err.println("SelectAll Error....");
			e.printStackTrace();
		}
		return allData;
	}

	// ---- SelectDate ----
	public List<DataControl> selectDate(DataControl dc) {
		String selectDate = "select * from account where user_no = ? and date between ? and ? order by date";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(selectDate);
			ps.setInt(1, dc.getUser());
			ps.setString(2, dc.getStart());
			ps.setString(3, dc.getEnd());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DataControl data = toData(rs, typeName(rs), itemName(rs));
				allData.add(data);
			}
		} catch (SQLException e) {
			err.println("SelectDate Error....");
			e.printStackTrace();
		}
		return allData;
	}

	// ---- SelectThisMonth ----
	public List<DataControl> selectThisMonth(DataControl dc) {
		String selectThisMonth = "select * from account where type_no = ? and user_no = ? and date like ? order by date";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(selectThisMonth);
			ps.setString(1, dc.getType());
			ps.setInt(2, dc.getUser());
			ps.setString(3, dc.getNow() + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DataControl data = toData(rs, typeName(rs), itemName(rs));
				allData.add(data);
			}
		} catch (SQLException e) {
			err.println("SelectThisMonth Error....");
			// e.printStackTrace();
		}
		return allData;
	}

	// ---- SelectTypeAll ----
	public List<DataControl> selectTypeAll(DataControl dc) {
		String selectTypeAll = "select * from account where type_no = ? and user_no = ? order by date";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(selectTypeAll);
			ps.setString(1, dc.getType());
			ps.setInt(2, dc.getUser());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DataControl data = toData(rs, typeName(rs), itemName(rs));
				allData.add(data);
			}
		} catch (SQLException e) {
			err.println("SelectTypeAll Error....");
			e.printStackTrace();
		}
		return allData;
	}

	// ---- SelectTypeDate ----
	public List<DataControl> selectTypeDate(DataControl dc) {
		String selectTableDate = "select * from account where type_no = ? and user_no = ? and date between ? and ? order by date";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(selectTableDate);
			ps.setString(1, dc.getType());
			ps.setInt(2, dc.getUser());
			ps.setString(3, dc.getStart());
			ps.setString(4, dc.getEnd());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DataControl data = toData(rs, typeName(rs), itemName(rs));
				allData.add(data);
			}
		} catch (SQLException e) {
			err.println("SelectTableDate Error....");
			e.printStackTrace();
		}
		return allData;
	}

	// ---- SetResult ----
	public DataControl toData(ResultSet rs, String type, String item) throws SQLException {
		DataControl data = new DataControl();
		data.setType(type);
		data.setDate(rs.getString(3));
		data.setItem(item);
		data.setAmount(rs.getInt(5));
		data.setNotes(rs.getString(6));
		return data;
	}

	// ---- TypeName ----
	public String typeName(ResultSet rs) {
		String sql = "select type_name from type where type_no = ?";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(sql);
			ps.setInt(1, rs.getInt(2));
			ResultSet result = ps.executeQuery();
			result.next();
			return result.getString(1);
		} catch (SQLException e) {
			err.println("ItemName Error....");
			// e.printStackTrace();
			return null;
		}

	}

	// ---- ItemName ----
	public String itemName(ResultSet rs) {
		String sql = "select item_name from item where type_no = ? and item_no = ?";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(sql);
			ps.setInt(1, rs.getInt(2));
			ps.setInt(2, rs.getInt(4));
			ResultSet result = ps.executeQuery();
			result.next();
			return result.getString(1);
		} catch (SQLException e) {
			err.println("ItemName Error....");
			// e.printStackTrace();
			return null;
		}

	}

}
