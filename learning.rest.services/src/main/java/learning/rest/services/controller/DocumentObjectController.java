package learning.rest.services.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.Calendar;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import com.sap.ecm.api.EcmService;

import learning.rest.services.documentRepository.CustomDocument;
import learning.rest.services.helper.DateConversion;
import learning.rest.services.helper.SharedConstants;
import learning.rest.services.model.DocumentObject;

import learning.rest.services.model.Users;
import learning.rest.services.service.DocumentObjectService;
import learning.rest.services.service.LearningRequistionDocumentRelService;
import learning.rest.services.service.UsersService;

@RestController
public class DocumentObjectController {
	Logger logger = LoggerFactory.getLogger(LearningRequisitionsController.class);
	
	 @Autowired
	 DocumentObjectService documentObjectService;
	 @Autowired
	 LearningRequistionDocumentRelService learningRequistionDocumentRelService;
	 @Autowired
	 UsersService usersService;
	 
	 @GetMapping("/attachments/{RepDocId}")
	 public ResponseEntity<?> getAttachments(@PathVariable("RepDocId") String RepDocId,HttpServletResponse response) throws NamingException, IOException{
		 
		 DocumentObject docObject = documentObjectService.findByRepDocId(RepDocId);
		 CustomDocument cDoc = new CustomDocument();
		 cDoc.setUniqueKey(SharedConstants.REP_KEY);
		 cDoc.setUniqueName(SharedConstants.REP_NAME);
		 
		 Session openCmisSession = null;
		 
		 EcmService ecmSvc = cDoc.getECMService();
		 openCmisSession = ecmSvc.connect(cDoc.getUniqueName(),cDoc.getUniqueKey());
		 ContentStream docStream = cDoc.getDocumentStreamById(openCmisSession,RepDocId);
		 if (docStream != null) {
             response.setContentType(docStream.getMimeType());
             response.setHeader("Content-disposition", "attachment; filename="+docObject.getDocName());
             IOUtils.copy(docStream.getStream(), response.getOutputStream());
             IOUtils.closeQuietly(docStream.getStream());
         }
		 return null; 
	 }
	 @DeleteMapping("/attachments/{docId}")
	 public ResponseEntity<?> deleteAttachment(@PathVariable("docId") Long docId) throws NamingException {
		 DocumentObject docObject = documentObjectService.findById(docId);
		 
		 CustomDocument cDoc = new CustomDocument();
		 cDoc.setUniqueKey(SharedConstants.REP_KEY);
		 cDoc.setUniqueName(SharedConstants.REP_NAME);
		 Session openCmisSession = null;
		 
		 EcmService ecmSvc = cDoc.getECMService();
		 openCmisSession = ecmSvc.connect(cDoc.getUniqueName(),cDoc.getUniqueKey());
		 
		 Document doc = cDoc.getDocumentBySession(openCmisSession, docObject.getRepDocId());
		 doc.delete(true);
		 documentObjectService.deleteById(docId);
		 return ResponseEntity.ok().body("Successfully Deleted DocId:" + docId);
		 
	 }
	 @PostMapping("/attachment")
	 public ResponseEntity<DocumentObject> uploadAttachment(@RequestParam("file") MultipartFile uploadfile,@RequestParam("comments")String comments ,HttpServletRequest request) throws NamingException, UnsupportedEncodingException, IOException{
		 String pUserId = request.getUserPrincipal().getName().toUpperCase();
		 Users user = usersService.findByPUserId(pUserId);
		 DocumentObject docObject = new DocumentObject();
		 DateConversion dateConversion = new DateConversion();
		 
		 docObject.setCreatedBy(user.getUserID());
		 docObject.setCreatedByName(user.getFname()+ " "+user.getLname());
		 docObject.setCreationDate(dateConversion.DateToYYYMMDD(Calendar.getInstance()));
		 docObject.setDocName(uploadfile.getOriginalFilename());
		 docObject = documentObjectService.create(docObject);
		 
		 CustomDocument cDoc = new CustomDocument();
		 cDoc.setUniqueKey(SharedConstants.REP_KEY);
		 cDoc.setUniqueName(SharedConstants.REP_NAME);
		 
		 Session openCmisSession = null;
		 
		 EcmService ecmSvc = cDoc.getECMService();
		 logger.debug("Service :"+ecmSvc.toString());
		 openCmisSession =cDoc.createSession(ecmSvc);
		 
		 Folder root = openCmisSession.getRootFolder();
		 logger.debug("Root:"+root.toString());
		 Folder learningFolder =  cDoc.getFolder(root,SharedConstants.LEARNING_FOLDER);
		 if(learningFolder == null){
		  learningFolder = cDoc.createNewFolder(root,SharedConstants.LEARNING_FOLDER);
		  logger.debug("learningFolder:"+learningFolder.toString());
		 }
		 String mimeType = URLConnection.guessContentTypeFromName(uploadfile.getName());
	        if (mimeType == null || mimeType.length() == 0) {
	            mimeType = "application/octet-stream";
	        }
		 Document doc = cDoc.createNewDocumnet(openCmisSession, learningFolder, uploadfile.getOriginalFilename()+docObject.getDocId(),uploadfile.getBytes(),mimeType);
		 
		
		 
	
		 docObject.setFileLength(doc.getContentStreamLength());
		 docObject.setMimeType(doc.getContentStreamMimeType());
		 docObject.setDownloadLink("attachments/"+doc.getId());
		 docObject.setRepDocId(doc.getId());
		 docObject.setComments(comments);
		 docObject = documentObjectService.update(docObject);
		 
		 return ResponseEntity.ok().body(docObject);

	 }
}
