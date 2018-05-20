package com.stu.bean;

import java.io.Serializable;

/**
 * ¸öÌå
 * @author shenj
 * @since 2018-04-19
 */
public class Person implements Serializable{
	
	private String id;
	private String name;
	private String sex;
	private String age;
	private String job;
	private String like;
	private int	zan_count;
	private int cai_count;
	private int share_count;
	private int message_count;
	private String id_url;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public int getZan_count() {
		return zan_count;
	}
	public void setZan_count(int zan_count) {
		this.zan_count = zan_count;
	}
	public int getCai_count() {
		return cai_count;
	}
	public void setCai_count(int cai_count) {
		this.cai_count = cai_count;
	}
	public int getShare_count() {
		return share_count;
	}
	public void setShare_count(int share_count) {
		this.share_count = share_count;
	}
	public int getMessage_count() {
		return message_count;
	}
	public void setMessage_count(int message_count) {
		this.message_count = message_count;
	}
	public String getId_url() {
		return id_url;
	}
	public void setId_url(String id_url) {
		this.id_url = id_url;
	}
	
	
}
