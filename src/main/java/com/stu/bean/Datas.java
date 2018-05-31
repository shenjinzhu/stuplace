package com.stu.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * ��Դ
 * 
 * @author shenj
 * @since 2018-04-19
 */
public class Datas implements Serializable {
	private String id;
	private String title;
	private String display;
	private String url;
	private String author;// �ϴ���
	private Double size;// �ļ���С
	private Date up_time;// �ϴ�ʱ��
	private int down_time;// ���ش���
	private boolean useful;// �Ƿ�����Ч
	private long can_use;// ��Ч��
	private int zan;
	private int cai;// ��
	private String file_id;
	private String type;
	private PageHelp page;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Date getUp_time() {
		return up_time;
	}

	public void setUp_time(Date up_time) {
		this.up_time = up_time;
	}

	public int getDown_time() {
		return down_time;
	}

	public void setDown_time(int down_time) {
		this.down_time = down_time;
	}

	public boolean isUseful() {
		return useful;
	}

	public void setUseful(boolean useful) {
		this.useful = useful;
	}

	public long getCan_use() {
		return can_use;
	}

	public void setCan_use(long can_use) {
		this.can_use = can_use;
	}

	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

	public int getCai() {
		return cai;
	}

	public void setCai(int cai) {
		this.cai = cai;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PageHelp getPage() {
		return page;
	}

	public void setPage(PageHelp page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Datas [id=" + id + ", title=" + title + ", display=" + display + ", url=" + url + ", author=" + author
				+ ", size=" + size + ", up_time=" + up_time + ", down_time=" + down_time + ", useful=" + useful
				+ ", can_use=" + can_use + ", zan=" + zan + ", cai=" + cai + ", file_id=" + file_id + ", type=" + type
				+ "]";
	}

}
