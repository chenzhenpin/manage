package org.fkit.table;

import org.apache.ibatis.type.Alias;

@Alias("card")
public class Card {
	private Integer id;
	private String card;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
}
