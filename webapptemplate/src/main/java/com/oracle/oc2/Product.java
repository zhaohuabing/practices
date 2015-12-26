package com.oracle.oc2;

public class Product {
	private final String itemid;

	public Product(String itemid, String product, String listprice,
			String unitcost, String attr1, String status) {
		super();
		this.itemid = itemid;
		this.product = product;
		this.listprice = listprice;
		this.unitcost = unitcost;
		this.attr1 = attr1;
		this.status = status;
	}

	public String getItemid() {
		return itemid;
	}

	public String getProduct() {
		return product;
	}

	public String getListprice() {
		return listprice;
	}

	public String getUnitcost() {
		return unitcost;
	}

	public String getAttr1() {
		return attr1;
	}

	public String getStatus() {
		return status;
	}

	private final String product;
	private final String listprice;
	private final String unitcost;
	private final String attr1;
	private final String status;

}
