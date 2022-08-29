import java.io.Serializable;

public class DataControl implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String gender;
	private String message;
	private String type;
	private String date;
	private String item;
	private int amount;
	private String notes;
	private int user;
	private String now;
	private String start;
	private String end;

	public DataControl() {
	};

	// ---- DoCheckID ----
	public DataControl(String username) {
		this.username = username;
	};

	// ---- DoLogin ----
	public DataControl(String username, String password) {
		this.username = username;
		this.password = password;
	};

	// ---- DoRegister ----
	public DataControl(String username, String password, String nickname, String email, String gender) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.gender = gender;
	};

	// ---- DoSelectAll ----
	public DataControl(int user) {
		this.user = user;
	};

	// ---- DoSelectTypeAll ----
	public DataControl(String type, int user) {
		this.type = type;
		this.user = user;
	};

	// ---- DoSelectThisMonth ----
	public DataControl(String type, int user, String now) {
		this.type = type;
		this.user = user;
		this.now = now;
	};

	// ---- DoSelectDate ----
	public DataControl(int user, String start, String end) {
		this.user = user;
		this.start = start;
		this.end = end;
	};

	// ---- DoSelectTypeDate ----
	public DataControl(String type, int user, String start, String end) {
		this.type = type;
		this.user = user;
		this.start = start;
		this.end = end;
	};

	// ---- DoCreate / DoUpdate ----
	public DataControl(String type, String date, String item, int amount, String notes, int user) {
		this.type = type;
		this.date = date;
		this.item = item;
		this.amount = amount;
		this.notes = notes;
		this.user = user;
	}

	// ---- Username ----
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// ---- Password ----
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// ---- Nickname ----
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	// ---- Email ----
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// ---- Gender ----
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	// ---- Message ----
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// ---- Type ----
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// ---- Date ----
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	// ---- Item ----
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	// ---- Amount ----
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	// ---- Notes ----
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	// ---- User ----
	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	// ---- Now ----
	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	// ---- Start ----
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	// ---- End ----
	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
