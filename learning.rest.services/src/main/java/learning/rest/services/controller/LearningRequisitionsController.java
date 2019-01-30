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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learning.rest.services.helper.Actions;
import learning.rest.services.helper.Course;
import learning.rest.services.helper.DateConversion;
import learning.rest.services.helper.PostRequisition;
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
@RequestMapping("/user")
public class LearningRequisitionsController {

	 Logger logger = LoggerFactory.getLogger(LearningRequisitionsController.class);
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
	 
	 @GetMapping("/myLearning")
	 public ResponseEntity<List<Course>> getMyLearnings(HttpServletRequest request){

		
		 String pUserId =  request.getUserPrincipal().getName().toUpperCase();
		 Users user = usersService.findByPUserId(pUserId);
		 List<LearningLineItems> lItems = learningLineItemsService.findByLearnerId(user.getUserID());
		 List<LearningRequisitions> lReqs = learningRequisitionsService.findByRequestorId(user.getUserID());
		 List<Course> responseList = new ArrayList<Course>();
		 Course rCourse;
		 for (LearningLineItems lItem : lItems)
		 {
			 List<LearningLineItems> learnings = learningLineItemsService.findByRequsitionId(lItem.getRequistionID());
			 List<LearningCourseItems> cItem = learningCourseItemsService.findByRequsitionId(lItem.getRequistionID());
			 CourseAttributes course = courseAttributesService.findById(cItem.get(0).getCourseID());
			 LearningRequisitions lRequisition = learningRequisitionsService.findById(lItem.getRequistionID());
			 Users requestor = usersService.findById(lRequisition.getRequestorID());
			 List<CourseProviderRelationship> courseProvider =  courseProviderRelationshipService.findByRequisitionId(lItem.getRequistionID());
			 CourseProvider provider = courseProviderService.findById(courseProvider.get(0).getProviderID());
			 List<Statuses> lStatus = statusesService.findByStatusId(lRequisition.getStatusId());
			 
			 rCourse = new Course();
			 rCourse.setCourse(course);
			 rCourse.setProvider(provider);
			 lRequisition.setStatus(lStatus.get(0).getStatusSmallText());
			 rCourse.setRequisitions(lRequisition);
			 List<Statuses> lItemStatus = statusesService.findByStatusId(lItem.getApprovalStatusId());
			 lItem.setApprovalStatus(lItemStatus.get(0).getStatusSmallText());
			 rCourse.setLearningItem(lItem);
			 for (int i=0;i<learnings.size();i++)
			 {
				 user = usersService.findById(learnings.get(i).getLearnerID());
				 learnings.get(i).setLearnerLname(user.getLname());
				 learnings.get(i).setLearnerFname(user.getFname());
				 learnings.get(i).setLearnerEmail(user.getEmail());
				 List<Statuses> llStatus = statusesService.findByStatusId(learnings.get(i).getApprovalStatusId());
				 learnings.get(i).setApprovalStatus(llStatus.get(0).getStatusSmallText());
			 }
			 rCourse.setlItems(learnings);
			 rCourse.setRequestor(requestor);
			 if(lItem.getLearnerID() == lRequisition.getRequestorID())
			 {
				 rCourse.setMyCourse("true");
			 }
			 else
			 {
				 rCourse.setMyCourse("false");
			 }
			 responseList.add(rCourse);
		 }
		 for (LearningRequisitions lReq :lReqs)
		 {
			 List<LearningLineItems> rlearnings = learningLineItemsService.findByRequsitionId(lReq.getRequistionID());
			 
			 List<LearningCourseItems> cItem = learningCourseItemsService.findByRequsitionId(lReq.getRequistionID());
			 CourseAttributes course = courseAttributesService.findById(cItem.get(0).getCourseID());
			 Users requestor = usersService.findById(lReq.getRequestorID());
			 List<CourseProviderRelationship> courseProvider =  courseProviderRelationshipService.findByRequisitionId(lReq.getRequistionID());
			 CourseProvider provider = courseProviderService.findById(courseProvider.get(0).getProviderID());
			 List<Statuses> lrStatus = statusesService.findByStatusId(lReq.getStatusId());
			 
			 Boolean rIsL = requestorIsLearner(rlearnings,lReq.getRequestorID());
			 if(!rIsL){
			 rCourse = new Course();
			 rCourse.setCourse(course);
			 rCourse.setProvider(provider);
			 lReq.setStatus(lrStatus.get(0).getStatusSmallText());
			 rCourse.setRequisitions(lReq);
			 for (int i=0;i<rlearnings.size();i++)
			 {
				 user = usersService.findById(rlearnings.get(i).getLearnerID());
				 rlearnings.get(i).setLearnerLname(user.getLname());
				 rlearnings.get(i).setLearnerFname(user.getFname());
				 rlearnings.get(i).setLearnerEmail(user.getEmail());
				 List<Statuses> lrlStatus = statusesService.findByStatusId(rlearnings.get(i).getApprovalStatusId());
				 rlearnings.get(i).setApprovalStatus(lrlStatus.get(0).getStatusSmallText());
			 }
			 rCourse.setlItems(rlearnings);
			 rCourse.setRequestor(requestor);
			 rCourse.setMyCourse("false");
			 responseList.add(rCourse);
			 }
		 }
		 
		 return ResponseEntity.ok().body(responseList);
		 
		 
	 }
	 private Boolean requestorIsLearner(List<LearningLineItems> learnings, Long requestorID){
		 for(LearningLineItems learning :learnings)
		 {
			 if(learning.getLearnerID() == requestorID)
			 {
				 return true;
			 }
					 
		 }
		 return false;
	 	}
	 @SuppressWarnings("unused")
	@GetMapping("/requisition/{id}")
	 public ResponseEntity<Course> getRequisition(@PathVariable("id") Long reqId,HttpServletRequest request){
		 
		 String pUserId = request.getUserPrincipal().getName().toUpperCase();
		 Users user = usersService.findByPUserId(pUserId);
		 List<LearningLineItems> rlearnings = learningLineItemsService.findByRequsitionId(reqId);
		 LearningLineItems lItem = null;
		 for (LearningLineItems learning :rlearnings )
		 {
			 if(learning.getLearnerID() == user.getUserID())
			 {
				 lItem = learning;
			 }
		 }
		 
		 LearningRequisitions lRequisition = learningRequisitionsService.findById(reqId);
		 List<LearningCourseItems> cItem = learningCourseItemsService.findByRequsitionId(reqId);
		 CourseAttributes course = courseAttributesService.findById(cItem.get(0).getCourseID());
		 List<CourseProviderRelationship> courseProvider =  courseProviderRelationshipService.findByRequisitionId(reqId);
		 CourseProvider provider = courseProviderService.findById(courseProvider.get(0).getProviderID());
		 Users requestor = usersService.findById(lRequisition.getRequestorID());
		 List<RequisitionLogRel> reqLRels =  requisitionLogRelService.findByRequsitionId(reqId);
		 List<Statuses> lStatus = statusesService.findByStatusId(lRequisition.getStatusId());
		 List<Logs> logs = new ArrayList<Logs>(); 
		 Logs log ;
		 Users fromUser ;
		 List<Statuses> logStatus ;
		 for (RequisitionLogRel reqLRel : reqLRels)
		 {
			 log = logsService.findById(reqLRel.getLogID());
			 fromUser = usersService.findById(Long.valueOf(log.getFromPerson()));
			 log.setFromPersonText(fromUser.getFname() + " " + fromUser.getLname());
			 logStatus = statusesService.findByStatusId(log.getToStatus());
			 log.setToStatusText(logStatus.get(0).getStatusSmallText());
			 logs.add(log);
		 }
		 
		 Course rCourse = new Course();
		 rCourse.setProvider(provider);
		 rCourse.setCourse(course);
		 
		 lRequisition.setStatus(lStatus.get(0).getStatusSmallText());
		 rCourse.setRequisitions(lRequisition);
		 
		 rCourse.setRequestor(requestor);
		 
		 List<Statuses> lItemStatus = statusesService.findByStatusId(lItem.getApprovalStatusId());
		 lItem.setApprovalStatus(lItemStatus.get(0).getStatusSmallText());
		 
		 rCourse.setLearningItem(lItem);
		 rCourse.setLogs(logs);
		 for (int i=0;i<rlearnings.size();i++)
		 {
			 user = usersService.findById(rlearnings.get(i).getLearnerID());
			 rlearnings.get(i).setLearnerLname(user.getLname());
			 rlearnings.get(i).setLearnerFname(user.getFname());
			 rlearnings.get(i).setLearnerEmail(user.getEmail());
			 lStatus = statusesService.findByStatusId(rlearnings.get(i).getApprovalStatusId());
			 rlearnings.get(i).setApprovalStatus(lStatus.get(0).getStatusSmallText());
		 }
		 rCourse.setlItems(rlearnings);
		 
		 if(lItem != null)
		 {rCourse.setMyCourse("true");}
		 else
		 {rCourse.setMyCourse("false");}
		 
		 return ResponseEntity.ok().body(rCourse);
	 }
	 @PostMapping("/requisition")
	 public  ResponseEntity <LearningRequisitions> createRequisition(@RequestBody PostRequisition pRequisiton,HttpServletRequest request) {
		 
		 // check the course exist for the learner
		 
		 //creating requisition
		 String pUserId = request.getUserPrincipal().getName().toUpperCase();
		 Users requestor = usersService.findByPUserId(pUserId);
		 DateConversion dateConversion = new DateConversion();
		 LearningRequisitions lRequisition = new LearningRequisitions();
		 
		 lRequisition.setRequestorID(requestor.getUserID());
		 lRequisition.setRequistionDate(dateConversion.DateToYYYMMDD(Calendar.getInstance()));
		 lRequisition.setStatusId(100);
		 lRequisition.setRequisitonComments(pRequisiton.getRequisitonComments());
		 lRequisition.setRequsitionCheck(pRequisiton.getRequsitionCheck());
		 lRequisition = learningRequisitionsService.create(lRequisition);
		
		//create learning line items
		String learners = pRequisiton.getLearnerID();
		String[] parts = learners.split(",");
		LearningLineItems lItem = null;
		for (String learner : parts){
			lItem = new LearningLineItems();
			lItem.setRequistionID(lRequisition.getRequistionID());
			lItem.setApprovalStatusId(100);
			lItem.setLearnerID(Long.valueOf(learner));
			learningLineItemsService.create(lItem);
		}
		
		//create course
			CourseAttributes course = new CourseAttributes();
			course.setCourseEndDate(pRequisiton.getCourseEndDate());
			course.setCourseStartDate(pRequisiton.getCourseStartDate());
			course.setCourseTitle(pRequisiton.getCourseTitle());
			course.setCourseCostCenter(pRequisiton.getCourseCostCenter());
			course.setCourseCosts(pRequisiton.getCourseCosts());
			course.setCourseDuration(pRequisiton.getCourseDuration());
			course.setCourseDurationUnit(pRequisiton.getCourseDurationUnit());
			course.setCourseLocation(pRequisiton.getCourseLocation());
			course = courseAttributesService.create(course);
		
		//link course to requisition	
		
			LearningCourseItems cItem = new LearningCourseItems();
			cItem.setCourseID(course.getCourseID());
			cItem.setRequistionID(lRequisition.getRequistionID());
			learningCourseItemsService.create(cItem);
			
		//create provider
			CourseProvider provider = new CourseProvider();
			provider.setProviderCity(pRequisiton.getProviderCity());
			provider.setProviderCompanyName(pRequisiton.getProviderCompanyName());
			provider.setProviderEmail(pRequisiton.getProviderEmail());
			provider.setProviderPostalCode(pRequisiton.getProviderPostalCode());
			provider.setProviderStreet(pRequisiton.getProviderStreet());
			provider.setProviderWebsite(pRequisiton.getProviderWebsite());
			provider = courseProviderService.create(provider);
			
		//link provider to requisition
			
			CourseProviderRelationship courseProvider = new CourseProviderRelationship();
			courseProvider.setProviderID(provider.getProviderID());
			courseProvider.setRequisitionID(lRequisition.getRequistionID());
			courseProviderRelationshipService.create(courseProvider);
	    
		//link attachement to requisition
			String attachments = pRequisiton.getAttachmentID();
			String[] attParts = attachments.split(",");
			LearningRequistionDocumentRel lRDocRel = null;
			for (String attPart : attParts){
				if(attPart.length() != 0){
			 lRDocRel = new LearningRequistionDocumentRel();
			 lRDocRel.setDocId(Long.valueOf(attPart));
			 lRDocRel.setRequistionID(lRequisition.getRequistionID());
			 learningRequistionDocumentRelService.create(lRDocRel);
			 }
			}
			// create workflow
			Calendar calendar = Calendar.getInstance();
			String dateString = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
			
			Logs log = new Logs();
			log.setFromPerson(requestor.getUserID());
			log.setToPerson(Long.valueOf(103));
			log.setToStatus(100);
			log.setComments(pRequisiton.getRequisitonComments());
			log.setTimestamp(Long.valueOf(dateString));
			log = logsService.create(log);
			
			//link workflow with requisition
			
			RequisitionLogRel rLR = new RequisitionLogRel();
			rLR.setRequistionID(lRequisition.getRequistionID());
			rLR.setLogID(log.getLogID());
			rLR.setSequence(1);
			requisitionLogRelService.create(rLR);
			
			return ResponseEntity.ok().body(lRequisition);
	 }
	 @PutMapping("/requisition/{id}")
	 public  ResponseEntity <Course> updateRequisition(@RequestBody Course course,@PathVariable("id") Long reqId,HttpServletRequest request) {
		
		 Calendar calendar = Calendar.getInstance();
			String dateString = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
		 
		 String pUserId = request.getUserPrincipal().getName().toUpperCase();
		 Users user = usersService.findByPUserId(pUserId);
		 LearningLineItems uLItem = course.getLearningItem();
		 
		 Logs log = new Logs();
		 log.setFromPerson(user.getUserID());
		 log.setToStatus(uLItem.getApprovalStatusId());
		 log.setTimestamp(Long.valueOf(dateString));
		 log.setComments(course.getRequisitions().getRequisitonComments());
		 
		 if(uLItem.getApprovalStatusId() == 100){
			 
			 log.setToStatus(105);
			 
			 LearningRequisitions lReq = learningRequisitionsService.findById(uLItem.getRequistionID());
			 uLItem.setApprovalStatusId(lReq.getStatusId());
		 }
		 
		 log = logsService.create(log);
		 	// get latest sequence 
		 	int lSequence = requisitionLogRelService.getLatestSequenceNumber(reqId);
		 	
		 	RequisitionLogRel rLR = new RequisitionLogRel();
			rLR.setRequistionID(reqId);
			rLR.setLogID(log.getLogID());
			rLR.setSequence(lSequence + 1);
			requisitionLogRelService.create(rLR);
		 
		 LearningLineItems lItem = learningLineItemsService.update(uLItem); 
		 CourseAttributes courseAttribute = courseAttributesService.update(course.getCourse());
		 CourseProvider provider = courseProviderService.update(course.getProvider());
		 
		 Course rCourse = new Course();
		 rCourse.setCourse(courseAttribute);
		 rCourse.setProvider(provider);
		 rCourse.setLearningItem(lItem);
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
		 
		 String pUserId = request.getUserPrincipal().getName().toUpperCase();
		 Users user = usersService.findByPUserId(pUserId);
		 LearningRequisitions lReq = learningRequisitionsService.findById(reqId);
		 List<LearningLineItems> lineItems =  learningLineItemsService.findByRequsitionId(reqId);
		 LearningLineItems ulItem = null ;
		 Boolean isCreator = false;
		 Boolean isLearner = false;
		 if (lReq.getRequestorID() == user.getUserID())
		 {//user is creator has option to cancel 
			 isCreator = true;
		 }
		 for (LearningLineItems lItem : lineItems)
		 {
			 if(lItem.getLearnerID() == user.getUserID())
			 {
				 ulItem = lItem;
				 isLearner = true;
			 }
		 }
		 Actions action = new Actions();
		 Statuses status;
		 String nStates;
		 String [] parts;
		 	if(isCreator)
		 	{
		 		status = statusesService.findById(lReq.getStatusId(), "C");
		 		 nStates = status.getNextStates();
		 		 parts = nStates.split(",");
		 		for(String part :  parts)
		 		{
		 			action = setActionObject(action, Integer.valueOf(part));
		 		}
		 	}
		 	if(isLearner)
		 	{
		 		status = statusesService.findById(ulItem.getApprovalStatusId(), "L");
		 		 nStates = status.getNextStates();
		 		 parts = nStates.split(",");
		 		for(String part :  parts)
		 		{
		 			action = setActionObject(action, Integer.valueOf(part));
		 		}
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
	 
	 
	 


