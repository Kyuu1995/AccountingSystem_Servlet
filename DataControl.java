import java.io.Serializable;

@SuppressWarnings("serial")
public class DataControl implements Serializable {
	private String table;
	private String date;
	private String item;
	private int amount;
	private String notes;

	public DataControl() {
	};

	public DataControl(String table) {
		this.table = table;
	};

	public DataControl(String table, String date, String item, int amount, String notes) {
		this.table = table;
		this.date = date;
		this.item = item;
		this.amount = amount;
		this.notes = notes;
	}

	// ----
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	// ----
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	// ----
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	// ----
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	// ----
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
