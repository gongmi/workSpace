package org.crazyit.app.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.crazyit.app.domain.Msg;
@NamedQueries(
		{
			@NamedQuery(name="topic.selectCertainTopic", query="from Topic t where t.id = :id")
		}
		)
		/*
@NamedNativeQueries(
		{
			@NamedNativeQuery(name="topic.select2_5Topic", query="select * from topic limit 2, 5")
		}
		)
		*/
@Entity
public class Topic {
	@Id
	@GeneratedValue
	private int id;
	private String title;
	@ManyToOne(fetch=FetchType.LAZY)
	private Category category;
	//private Category category2;
	
	private Date createDate;
	
//	private List<Msg> msgs = new ArrayList<Msg>();
//	@OneToMany(mappedBy="topic")
//	public List<Msg> getMsgs() {
//		
//		return msgs;
//	}
//	public void setMsgs(List<Msg> msgs) {
//		this.msgs = msgs;
//	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
