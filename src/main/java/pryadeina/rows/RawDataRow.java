package pryadeina.rows;

public class RawDataRow {
	private int price;
	private int id;
	private int count;

	public RawDataRow() {
		super();
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "RawDataRow [price=" + price + ", id=" + id + ", count=" + count + "]";
	}

}
