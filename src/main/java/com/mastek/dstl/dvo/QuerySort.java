package com.mastek.dstl.dvo;

public class QuerySort {

	private Enum<?> sortOrder;
	
	private String sortField;

	/**
	 * @return the sortOrder
	 */
	public Enum<?> getSortOrder() {
		return sortOrder;
	}

	public QuerySort(Enum<?> sortOrder, String sortField) {
		super();
		this.sortOrder = sortOrder;
		this.sortField = sortField;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(Enum<?> sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the sortField
	 */
	public String getSortField() {
		return sortField;
	}

	/**
	 * @param sortField the sortField to set
	 */
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
}
