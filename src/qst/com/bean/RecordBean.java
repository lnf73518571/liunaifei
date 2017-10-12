package qst.com.bean;

public class RecordBean {
	private Integer id;
	//private Integer userID;
	private Integer medicineID;
	//private Integer cardID;
	private String cardnum;
	private String username;
	private float price;
	private Integer typeID;
	private Integer num;
	
	public Integer getTypeID() {
		return typeID;
	}
	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMedicineID() {
		return medicineID;
	}
	public void setMedicineID(Integer medicineID) {
		this.medicineID = medicineID;
	}
	/*public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	/*public Integer getCardID() {
		return cardID;
	}
	public void setCardID(Integer cardID) {
		this.cardID = cardID;
	}*/
	
	public String getUsername() {
		return username;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCardnum() {
		return cardnum;
	}
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	
	
}
