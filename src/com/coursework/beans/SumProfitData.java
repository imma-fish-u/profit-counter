package com.coursework.beans;

public class SumProfitData {
	  private float spendings;
	  private String dateReport;
	  private int sumQuantity;
	  private float sumPrice;
	  private float sum—ost;
	  private float sumMarginProfit;
	  private float pureProfit;
	  
	  public SumProfitData () { }
	  
	  public SumProfitData (String dateReport) {
		  this.dateReport = dateReport;
	  }
	  
	  public float getSpendings() {
		return spendings;
	  }

	  public void setSpendings(float spendings) {
	    this.spendings = spendings;
	  }

	  public String getDateReport() {
	    return dateReport;
	  }

	  public void setDateReport(String dateReport) {
	    this.dateReport = dateReport;
	  }
	  
	  public float getSumQuantity() {
		    return sumQuantity;
	  }
	  
	  public void setSumQuantity(int sumQuantity) {
		  this.sumQuantity = sumQuantity;
	  }
	  
	  public float getSumPrice() {
		    return sumPrice;
	  }
	  
	  public void setSumPrice(float sumPrice) {
		  this.sumPrice = sumPrice;
	  }

	  public float getSumCost() {
		  return sum—ost;
	  }
	  
	  public void setSumCost(float sumCost) {
		  this.sum—ost = sumCost;
	  }
	  
	  public float getSumMarginProfit() {
		    return sumMarginProfit;
	  }
	  
	  public void setSumMarginProfit(float sumMarginProfit) {
		   this.sumMarginProfit = sumMarginProfit;
	  }
	  
	  public float getPureProfit() {
		    return pureProfit;
	  }
	  
	  public void setPureProfit() {
		   this.pureProfit = getSumMarginProfit()-getSpendings();
	  }
}
