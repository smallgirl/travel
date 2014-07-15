package itstudio.travel.entity;

/**
* @Description: 餐饮Entity 

* @author MR.Wang

* @date 2014-7-5 上午9:30:07 

* @version V1.0
*/
public class Catering {

	private String title;
	private String shortInfo;
	private String distance;
	private int rating;
	private String picUrl;
	private String detailUrl;
	
	
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShortInfo() {
		return shortInfo;
	}
	public void setShortInfo(String shortInfo) {
		this.shortInfo = shortInfo;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
}
