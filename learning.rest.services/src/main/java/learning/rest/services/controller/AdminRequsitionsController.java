package learning.rest.services.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learning.rest.services.helper.Actions;
import learning.rest.services.helper.Course;
import learning.rest.services.model.CourseAttributes;
import learning.rest.services.model.CourseProvider;
import learning.rest.services.model.CourseProviderRelationship;
import learning.rest.services.model.DocumentObject;
import learning.rest.services.model.LearningCourseItems;
import learning.rest.services.model.LearningLineItems;
import learning.rest.services.model.LearningRequisitions;
import learning.rest.services.model.LearningRequistionDocumentRel;
import learning.rest.services.model.RequisitionLogRel;
import learning.rest.services.model.Statuses;
import learning.rest.services.model.Users;
import learning.rest.services.model.Logs;
import learning.rest.services.service.CourseAttributesService;
import learning.rest.services.service.CourseProviderRelationshipService;
import learning.rest.services.service.CourseProviderService;
import learning.rest.services.service.DocumentObjectService;
import learning.rest.services.service.LearningCourseItemsService;
import learning.rest.services.service.LearningLineItemsService;
import learning.rest.services.service.LearningRequisitionsService;
import learning.rest.services.service.LearningRequistionDocumentRelService;
import learning.rest.services.service.RequisitionLogRelService;
import learning.rest.services.service.StatusesService;
import learning.rest.services.service.UsersService;
import learning.rest.services.service.LogsService;

@RestController
@RequestMapping("/admin")
public class AdminRequsitionsController {
	
	Logger logger = LoggerFactory.getLogger(AdminRequsitionsController.class);
	 @Autowired
	 CourseProviderService courseProviderService;
	 @Autowired
	 LearningRequisitionsService learningRequisitionsService;
	 @Autowired
	 CourseAttributesService courseAttributesService;
	 @Autowired
	 LearningLineItemsService learningLineItemsService;
	 @Autowired
	 LearningCourseItemsService learningCourseItemsService;
	 @Autowired
	 UsersService usersService;
	 @Autowired
	 CourseProviderRelationshipService courseProviderRelationshipService;
	 @Autowired
	 DocumentObjectService documentObjectService;
	 @Autowired
	 LearningRequistionDocumentRelService learningRequistionDocumentRelService;
	 @Autowired
	 LogsService logsService;
	 @Autowired
	 RequisitionLogRelService requisitionLogRelService;
	 @Autowired
	 StatusesService statusesService;
	 
	 @GetMapping("/requisitions")
	 public ResponseEntity<?> getRequisitions(){
		
		 List<Course> courseList = new ArrayList<Course>();
		 Course rCourse;
		 Users user;
		 List<LearningRequisitions> reqList = learningRequisitionsService.findAll();
		 for (LearningRequisitions reqItem :reqList )
		 {
			 List<LearningCourseItems> cItem = learningCourseItemsService.findByRequsitionId(reqItem.getRequistionID());
			 CourseAttributes course = courseAttributesService.findById(cItem.get(0).getCourseID());
			 Users requestor = usersService.findById(reqItem.getRequestorID());
			 List<CourseProviderRelationship> courseProvider =  courseProviderRelationshipService.findByRequisitionId(reqItem.getRequistionID());
			 CourseProvider provider = courseProviderService.findById(courseProvider.get(0).getProviderID());
			 List<LearningLineItems> lItems =  learningLineItemsService.findByRequsitionId(reqItem.getRequistionID());
			 
			 List<Statuses> lStatus = statusesService.findByStatusId(reqItem.getStatusId());
			 
			 rCourse = new Course();
			 rCourse.setCourse(course);
			 rCourse.setProvider(provider);
			 reqItem.setStatus(lStatus.get(0).getStatusSmallText());
			 rCourse.setRequisitions(reqItem);
			 for (int i=0;i<lItems.size();i++)
			 {
				 user = usersService.findById(lItems.get(i).getLearnerID());
				 lItems.get(i).setLearnerLname(user.getLname());
				 lItems.get(i).setLearnerFname(user.getFname());
				 lItems.get(i).setLearnerEmail(user.getEmail());
				 List<Statuses> llStatus = statusesService.findByStatusId(lItems.get(i).getApprovalStatusId());
				 lItems.get(i).setApprovalStatus(llStatus.get(0).getStatusSmallText());
			 }
			 rCourse.setlItems(lItems);
			 rCourse.setRequestor(requestor);
			 courseList.add(rCourse);
		 }
		 return ResponseEntity.ok().body(courseList);
		 
		 
	 }
	 @GetMapping("/requisitions/{id}")
	 public ResponseEntity<Course> getPerRequisitions(@PathVariable("id") Long reqId){
		 Course rCourse;
		 Users user;
		 LearningRequisitions reqItem = learningRequisitionsService.findById(reqId);
		 List<Statuses> lStatus = statusesService.findByStatusId(reqItem.getStatusId());
		 	 List<LearningCourseItems> cItem = learningCourseItemsService.findByRequsitionId(reqItem.getRequistionID());
			 CourseAttributes course = courseAttributesService.findById(cItem.get(0).getCourseID());
			 Users requestor = usersService.findById(reqItem.getRequestorID());
			 List<CourseProviderRelationship> courseProvider =  courseProviderRelationshipService.findByRequisitionId(reqItem.getRequistionID());
			 CourseProvider provider = courseProviderService.findById(courseProvider.get(0).getProviderID());
			 List<LearningLineItems> lItems =  learningLineItemsService.findByRequsitionId(reqItem.getRequistionID());
			 
			 rCourse = new Course();
			 rCourse.setCourse(course);
			 rCourse.setProvider(provider);
			 reqItem.setStatus(lStatus.get(0).getStatusSmallText());
			 rCourse.setRequisitions(reqItem);
			 
			 List<RequisitionLogRel> reqLRels =  requisitionLogRelService.findByRequsitionId(reqId);
			 
			 List<Logs> logs = new ArrayList<Logs>(); 
			 Logs log ;
			 Users fromUser;
			 List<Statuses> logStatus ;
			 for (RequisitionLogRel reqWFRel : reqLRels)
			 {
				 log = logsService.findById(reqWFRel.getLogID());
				 fromUser = usersService.findById(Long.valueOf(log.getFromPerson()));
				 log.setFromPersonText(fromUser.getFname() + " " + fromUser.getLname());
				 logStatus = statusesService.findByStatusId(log.getToStatus());
				 log.setToStatusText(logStatus.get(0).getStatusSmallText());
				 logs.add(log);
			 }
			 rCourse.setLogs(logs);
			 for (int i=0;i<lItems.size();i++)
			 {
				 user = usersService.findById(lItems.get(i).getLearnerID());
				 lItems.get(i).setLearnerLname(user.getLname());
				 lItems.get(i).setLearnerFname(user.getFname());
				 lItems.get(i).setLearnerEmail(user.getEmail());
				 List<Statuses> llStatus = statusesService.findByStatusId(lItems.get(i).getApprovalStatusId());
				 lItems.get(i).setApprovalStatus(llStatus.get(0).getStatusSmallText());
				 
			 }
			 rCourse.setlItems(lItems);
			 rCourse.setRequestor(requestor);

		 
		 return ResponseEntity.ok().body(rCourse);
		 
		 
	 }
	 @PutMapping("/requisition/{id}")
	 public  ResponseEntity <Course> updateRequisition(@RequestBody Course course,@PathVariable("id") Long reqId) {
		 

		 LearningRequisitions requisitions = learningRequisitionsService.update(course.getRequisitions());
		 CourseAttributes courseAttribute = courseAttributesService.update(course.getCourse());
		 CourseProvider provider = courseProviderService.update(course.getProvider());
		 List<LearningLineItems> lItems =  learningLineItemsService.findByRequsitionId(reqId);
		 
		 for(int i=0;i<lItems.size();i++){
			 
			 if(!(lItems.get(i).getApprovalStatusId() == 104))
			 {
				 lItems.get(i).setApprovalStatusId(course.getRequisitions().getStatusId());
				 
			 }
			 learningLineItemsService.update(lItems.get(i));
		 }
		 
		 Calendar calendar = Calendar.getInstance();
			String dateString = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
		 
		 
		 Logs log = new Logs();
		 log.setFromPerson(Long.valueOf(103));
		 log.setToStatus(course.getRequisitions().getStatusId());
		 log.setTimestamp(Long.valueOf(dateString));
		 log.setComments(course.getRequisitions().getRequisitonComments());
		 
		 log = logsService.create(log);
		 	// get latest sequence 
		 	int lSequence = requisitionLogRelService.getLatestSequenceNumber(reqId);
		 	
		 	RequisitionLogRel rLR = new RequisitionLogRel();
			rLR.setRequistionID(reqId);
			rLR.setLogID(log.getLogID());
			rLR.setSequence(lSequence + 1);
			requisitionLogRelService.create(rLR);
		 
		 Course rCourse = new Course();
		 rCourse.setCourse(courseAttribute);
		 rCourse.setProvider(provider);
		 rCourse.setlItems(lItems);
		 rCourse.setRequisitions(requisitions);
		 return ResponseEntity.ok().body(rCourse);
		 
		 
	 }
	 @GetMapping("/requisition/{id}/attachments")
	 public ResponseEntity<List<DocumentObject>> getAttachments(@PathVariable("id") Long reqId,HttpServletRequest request){
		 
		 List<DocumentObject> docObjectList = new ArrayList<DocumentObject>();
		 List<LearningRequistionDocumentRel> reqDocRels = learningRequistionDocumentRelService.findByRequisitionId(reqId);
		 for (LearningRequistionDocumentRel reqDocRel : reqDocRels)
		 {
			 DocumentObject docObject = documentObjectService.findById(reqDocRel.getDocId());
			 
			 docObjectList.add(docObject);
		 }
		 
		 return ResponseEntity.ok().body(docObjectList);
		 
	 	}
	 @GetMapping("/requisition/{id}/actions")
	 public ResponseEntity<Actions> getAvailableActions(@PathVariable("id") Long reqId,HttpServletRequest request){
		 
		
		 LearningRequisitions lReq = learningRequisitionsService.findById(reqId);
		 
	
		 Actions action = new Actions();
		 Statuses status;
		 String nStates;
		 String [] parts;
		 	
		 		status = statusesService.findById(lReq.getStatusId(), "A");
		 		 nStates = status.getNextStates();
		 		 parts = nStates.split(",");
		 		for(String part :  parts)
		 		{
		 			action = setActionObject(action, Integer.valueOf(part));
		 		}
		 	
		 		
		 	
		 
		 return ResponseEntity.ok().body(action);
		 
	 }
	private Actions setActionObject(Actions action, Integer part) {
		switch(part)
		{
		case 101: 
			action.setApprove(true);
			break;
		case 102: 
			action.setReject(true);
			break;
		case 103: 
			action.setCancel(true);
			break;
		case 104: 
			action.setWithdraw(true);
			break;
		case 105: 
			action.setEnter(true);
			break;
		}
		return action;
	} 

}
