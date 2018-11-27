package org.fkit.flieManage;

public class FileInfo {
	private long id; //文件id
	private long userId; //上传用户id
	private String origName;//原始文件名
	private String saveName;//保存文件名
	private String webPath;//根目录路径包括文件名
	private String realPath;//绝对路径包括文件名
	private String uploadUser;//上传用户名
	private String uploaDateTime;//上传时间
	private String size;//文件大小
	private String type;//文件类型标识
	private String ext;//文件后缀
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getOrigName() {
		return origName;
	}
	public void setOrigName(String origName) {
		this.origName = origName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	
	public String getWebPath() {
		return webPath;
	}
	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	public String getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}
	public String getUploaDateTime() {
		return uploaDateTime;
	}
	public void setUploaDateTime(String uploaDateTime) {
		this.uploaDateTime = uploaDateTime;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	
}
