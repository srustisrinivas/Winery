package com.mindtree.entity;

public class Winery {

	private String P_WineID;
	private String P_Wine;
	private String P_Description;
	private String P_Winery;
	private String P_Year;
	private String P_PriceStr;
	private String P_Score;
	
	
	public Winery() {
		
	}
		
	public Winery(String p_WineID, String p_Wine, String p_Description, String p_Winery, String p_Year,
			String p_PriceStr, String p_Score) {
		super();
		P_WineID = p_WineID;
		P_Wine = p_Wine;
		P_Description = p_Description;
		P_Winery = p_Winery;
		P_Year = p_Year;
		P_PriceStr = p_PriceStr;
		P_Score = p_Score;
	}
	public String getP_WineID() {
		return P_WineID;
	}
	public void setP_WineID(String p_WineID) {
		P_WineID = p_WineID;
	}
	public String getP_Wine() {
		return P_Wine;
	}
	public void setP_Wine(String p_Wine) {
		P_Wine = p_Wine;
	}
	public String getP_Description() {
		return P_Description;
	}
	public void setP_Description(String p_Description) {
		P_Description = p_Description;
	}
	public String getP_Winery() {
		return P_Winery;
	}
	public void setP_Winery(String p_Winery) {
		P_Winery = p_Winery;
	}
	public String getP_Year() {
		return P_Year;
	}
	public void setP_Year(String p_Year) {
		P_Year = p_Year;
	}
	public String getP_PriceStr() {
		return P_PriceStr;
	}
	public void setP_PriceStr(String p_PriceStr) {
		P_PriceStr = p_PriceStr;
	}
	public String getP_Score() {
		return P_Score;
	}
	public void setP_Score(String p_Score) {
		P_Score = p_Score;
	}
	@Override
	public String toString() {
		return "Winery [P_WineID=" + P_WineID + ", P_Wine=" + P_Wine + ", P_Description=" + P_Description
				+ ", P_Winery=" + P_Winery + ", P_Year=" + P_Year + ", P_PriceStr=" + P_PriceStr + ", P_Score="
				+ P_Score + "]";
	}
	
	
}
