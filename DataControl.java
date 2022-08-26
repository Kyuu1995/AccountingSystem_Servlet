import java.io.Serializable;

@SuppressWarnings("serial")
public class DataControl implements Serializable {
	private String now;
	private int user;
	private String table;
	private String date;
	private String item;
	private int amount;
	private String notes;
	private String start;
	private String end;

	public DataControl() {
	};

	public DataControl(int user, String table) {
		this.user = user;
		this.table = table;
	};

	public DataControl(String now, int user, String table) {
		this.now = now;
		this.user = user;
		this.table = table;
	};

	public DataControl(int user, String table, String start, String end) {
		this.user = user;
		this.table = table;
		this.start = start;
		this.end = end;
	};

	public DataControl(int user, String table, String date, String item, int amount, String notes) {
		this.user = user;
		this.table = table;
		this.date = date;
		this.item = item;
		this.amount = amount;
		this.notes = notes;
	}

	// ---- Now ----
	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	// ---- User ----
	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	// ---- Table ----
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
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
