package learning.rest.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
	@NamedQuery(name = "DocumentObject.findByRepDocId", query = "SELECT do FROM DocumentObject do WHERE do.repDocId = :rdId "),
	@NamedQuery(name = "DocumentObject.findAll", query = "SELECT do FROM DocumentObject do"),
})
public class DocumentObject {
	
	@Column
	private Long createdByID;
	
	@Column
	private String createdByName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long docId;
	
	@Column
	private String docName;
	
	@Column
	private String mimeType;
	
	@Column
	private String downloadLink;
	
	@Column
	private Long creationDate;
	
	@Column
	private Long fileLength;
	
	@Column
	private String repDocId;
	
	@Column
	private String comments;
	
	public Long getCreatedBy() {
		return createdByID;
	}
	public void setCreatedBy(Long createdByID) {
		this.createdByID = createdByID;
	}
	public Long getDocId() {
		return docId;
	}
	public void setDocId(Long id) {
		this.docId = id;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public Long getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}
	public Long getFileLength() {
		return fileLength;
	}
	public void setFileLength(Long fileLength) {
		this.fileLength = fileLength;
	}
	public String getRepDocId() {
		return repDocId;
	}
	public void setRepDocId(String repDocId) {
		this.repDocId = repDocId;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
