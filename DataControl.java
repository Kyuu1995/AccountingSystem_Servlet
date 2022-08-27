import java.io.Serializable;

@SuppressWarnings("serial")
public class DataControl implements Serializable {
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

	public DataControl(int user) {
		this.user = user;
	};

	public DataControl(String type, int user) {
		this.type = type;
		this.user = user;
	};

	public DataControl(String type, int user, String now) {
		this.type = type;
		this.user = user;
		this.now = now;
	};

	public DataControl(int user, String start, String end) {
		this.user = user;
		this.start = start;
		this.end = end;
	};

	public DataControl(String type, int user, String start, String end) {
		this.type = type;
		this.user = user;
		this.start = start;
		this.end = end;
	};

	public DataControl(String type, String date, String item, int amount, String notes, int user) {
		this.type = type;
		this.date = date;
		this.item = item;
		this.amount = amount;
		this.notes = notes;
		this.user = user;
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
