import static java.lang.System.*;
import java.sql.*;
import java.util.*;

public class DataBase {

	// ---- CheckUsername ----
	public DataControl checkUsername(DataControl dc) {
		DataControl data = new DataControl();
		String checkUsername = "select user_no from user where username = ?";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(checkUsername);
			ps.setString(1, dc.getUsername());
			ResultSet rs = ps.executeQuery();
			rs.next();
			data.setUser(Integer.parseInt(rs.getString(1)));
			data.setMessage("此帳號已被使用....");
		} catch (SQLException e) {
			data.setMessage("查無此帳號....");
			// e.printStackTrace();
		}
		return data;
	}

	// ---- CheckPassword ----
	public DataControl checkPassword(DataControl dc) {
		DataControl data = new DataControl();
		String checkPassword = "select password from user where user_no = ?";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(checkPassword);
			ps.setInt(1, dc.getUser());
			ResultSet rs = ps.executeQuery();
			rs.next();
			data.setPassword(rs.getString(1));
		} catch (SQLException e) {
			err.println("CheckPassword Error....");
			// e.printStackTrace();
		}
		return data;
	}

	// ---- Login ----
	public DataControl login(DataControl dc) {
		DataControl data = new DataControl();
		String login = "select user_no, password, nickname from user where username = ?";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(login);
			ps.setString(1, dc.getUsername());
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs.getString(2).equals(dc.getPassword())) {
				data.setNickname(rs.getString(3));
				data.setUser(Integer.parseInt(rs.getString(1)));
				data.setMessage("登入成功....");
			} else {
				data.setMessage("帳號或密碼錯誤....");
			}
		} catch (SQLException e) {
			err.println("Login Error....");
			// e.printStackTrace();
		}
		return data;
	}

	// ---- Register ----
	public DataControl register(DataControl dc) {
		DataControl data = new DataControl();
		String register = "insert into user(username, password, nickname, email, gender) values(?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(register);
			ps.setString(1, dc.getUsername());
			ps.setString(2, dc.getPassword());
			ps.setString(3, dc.getNickname());
			ps.setString(4, dc.getEmail());
			ps.setString(5, dc.getGender());
			ps.executeUpdate();
			data.setMessage("註冊成功....");
		} catch (SQLException e) {
			err.println("Register Error....");
			e.printStackTrace();
		}
		return data;
	}

	// ---- ShowProfile ----
	public DataControl showProfile(DataControl dc) {
		DataControl data = new DataControl();
		String showProfile = "select username, password, nickname, email, gender from user where user_no = ?";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(showProfile);
			ps.setInt(1, dc.getUser());
			ResultSet rs = ps.executeQuery();
			rs.next();
			data.setUsername(rs.getString(1));
			data.setPassword(rs.getString(2));
			data.setNickname(rs.getString(3));
			data.setEmail(rs.getString(4));
			data.setGender(rs.getString(5));
		} catch (SQLException e) {
			err.println("ShowData Error....");
			// e.printStackTrace();
		}
		return data;
	}

	// ----- UpdateProfile ----
	public DataControl updateProfile(DataControl dc) {
		DataControl data = new DataControl();
		String updateData = "update user set password = ?, nickname = ?, email = ?, gender = ? where user_no = ?";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(updateData);
			ps.setString(1, dc.getPassword());
			ps.setString(2, dc.getNickname());
			ps.setString(3, dc.getEmail());
			ps.setString(4, dc.getGender());
			ps.setInt(5, dc.getUser());
			ps.executeUpdate();
			data.setMessage("修改成功....");
		} catch (Exception e) {
			err.println("UpdateProfile Error....");
			// e.printStackTrace();
		}
		return data;
	}

	// ---- CreateData ----
	public DataControl createData(DataControl dc) {
		DataControl data = new DataControl();
		String createData = "insert into account(type_no, date, item_no, amount, notes, user_no) values(?, ?, ?, ?, ? ,?)";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(createData);
			ps.setString(1, dc.getType());
			ps.setString(2, dc.getDate());
			ps.setInt(3, Integer.parseInt(dc.getItem()));
			ps.setInt(4, dc.getAmount());
			ps.setString(5, dc.getNotes());
			ps.setInt(6, dc.getUser());
			ps.executeUpdate();
			data.setMessage("新增成功....");
		} catch (SQLException e) {
			err.println("Create Error....");
			// e.printStackTrace();
		}
		return data;
	}

	// ---- UpdateData ----
	public DataControl updateData(DataControl dc) {
		DataControl data = new DataControl();
		String updateData = "update account set type_no = ?, date = ?, item_no = ?, amount = ?, notes = ? where account_no = ?";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(updateData);
			ps.setString(1, dc.getType());
			ps.setString(2, dc.getDate());
			ps.setInt(3, Integer.parseInt(dc.getItem()));
			ps.setInt(4, dc.getAmount());
			ps.setString(5, dc.getNotes());
			ps.setInt(6, dc.getAccountNo());
			ps.executeUpdate();
			data.setMessage("修改成功....");
		} catch (SQLException e) {
			err.println("UpdateData Error....");
			// e.printStackTrace();
		}
		return data;
	}

	// ---- DeleteData ----
	public DataControl deleteData(DataControl dc) {
		DataControl data = new DataControl();
		String deleteData = "delete from account where account_no = ?";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(deleteData);
			ps.setInt(1, dc.getAccountNo());
			ps.executeUpdate();
			data.setMessage("刪除成功....");
		} catch (SQLException e) {
			err.println("DeleteData Error....");
			e.printStackTrace();
		}
		return data;
	}

	// ---- GetItemNo ----
	public DataControl getItemNo(DataControl dc) {
		DataControl data = new DataControl();
		String sql = "select item_no from item where type_no = ? and item_name = ?";
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(sql);
			ps.setString(1, dc.getType());
			ps.setString(2, dc.getItem());
			ResultSet rs = ps.executeQuery();
			rs.next();
			data.setItem(rs.getString(1));
		} catch (SQLException e) {
			err.println("GetItemNo Error....");
			// e.printStackTrace();
		}
		return data;
	}

	// ---- ReadThisMonth ----
	public List<DataControl> readThisMonth(DataControl dc) {
		String readThisMonth = "select * from account where type_no = ? and user_no = ? and date like ? order by date";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(readThisMonth);
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

	// ---- ReadTypeAll ----
	public List<DataControl> readTypeAll(DataControl dc) {
		String readTypeAll = "select * from account where type_no = ? and user_no = ? order by date";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(readTypeAll);
			ps.setString(1, dc.getType());
			ps.setInt(2, dc.getUser());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DataControl data = toData(rs, typeName(rs), itemName(rs));
				allData.add(data);
			}
		} catch (SQLException e) {
			err.println("SelectTypeAll Error....");
			// e.printStackTrace();
		}
		return allData;
	}

	// ---- ReadTypeDate ----
	public List<DataControl> readTypeDate(DataControl dc) {
		String readTableDate = "select * from account where type_no = ? and user_no = ? and date between ? and ? order by date";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(readTableDate);
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
			// e.printStackTrace();
		}
		return allData;
	}

	// ---- ReadAll ----
	public List<DataControl> readAll(DataControl dc) {
		String readAll = "select * from account where user_no = ? order by date";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(readAll);
			ps.setInt(1, dc.getUser());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DataControl data = toData(rs, typeName(rs), itemName(rs));
				allData.add(data);
			}
		} catch (SQLException e) {
			err.println("SelectAll Error....");
			// e.printStackTrace();
		}
		return allData;
	}

	// ---- ReadDate ----
	public List<DataControl> readDate(DataControl dc) {
		String readDate = "select * from account where user_no = ? and date between ? and ? order by date";
		ArrayList<DataControl> allData = new ArrayList<DataControl>();
		try {
			PreparedStatement ps = DataConnection.getConnection().prepareStatement(readDate);
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
			// e.printStackTrace();
		}
		return allData;
	}

	// ---- SetResult ----
	public DataControl toData(ResultSet rs, String type, String item) throws SQLException {
		DataControl data = new DataControl();
		data.setAccountNo(rs.getInt(1));
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
