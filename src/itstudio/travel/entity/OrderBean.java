package itstudio.travel.entity;

public class OrderBean {

	private String goodsName;
	private String imgUrl;
	private int num;
	private float price;



	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public OrderBean(String title) {
		this.goodsName = title;
	}

	public OrderBean(String goodsName, String imgUrl, int num, float price) {
		super();
		this.goodsName = goodsName;
		this.imgUrl = imgUrl;
		this.num = num;
		this.price = price;
	}

	public OrderBean() {
	}

}
