package com.coursework.beans;


public class ProfitData {
	  private int id;
	  private Product product;
	  private SumProfitData sumProfitData;
	  private Integer quantity;
	  private float price;
	  private float cost;
	  private float marginProfit;
	  
	  public ProfitData() {

	  }
	  
	  public ProfitData(SumProfitData sumProfitData,Product product, Integer quantity) {
		 this.sumProfitData = sumProfitData;
		 this.product = product;
		 this.quantity = quantity;
	  }
	  
	  public int getId() {
		  return id;
	  }
	  
	  public void setId(int id) {
		  this.id = id;
	  }
	  
	  public SumProfitData getSumProfitData() {
		  return sumProfitData;
	  }
	  
	  public void setSumProfitData(SumProfitData sumProfitData) {
		  this.sumProfitData = sumProfitData;
	  }

	  public Product getProduct() {
		  return product;
	  }
	  
	  public void setProduct(Product product) {
		  this.product = product;
	  }
	  
	  public Integer getQuantity() {
	    return quantity;
	  }

	  public void setQuantity(Integer quantity) {
	    this.quantity = quantity;
	  }
	  
	  public float getPrice() {
		    return price;
	  }
	  
	  public void setPrice() {
		  this.price = product.getPrice()*quantity;
	  }

	  public float getCost() {
		  return cost;
	  }
	  
	  public void setCost() {
		  this.cost =  product.getCost()*quantity;
	  }
	  
	  public float getMarginProfit() {
		    return marginProfit;
	  }
	  
	  public void setMarginProfit() {
		   this.marginProfit = getPrice()-getCost();
	  }
}
