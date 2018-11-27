package org.fkit.entity.one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test_article")
public class Article   {
	//主从表id不能一样
	@Id @Column(name="article_id")
	@GeneratedValue 
	private Long articleid;
	@Column(name = "content")
	private String content;
	
	public Article() {
		
	}
	public Article(String content) {
		this.content=content;
	}


	public Long getArticleid() {
		return articleid;
	}


	public void setArticleid(Long articleid) {
		this.articleid = articleid;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "telnumber:"+this.content;
	}
}