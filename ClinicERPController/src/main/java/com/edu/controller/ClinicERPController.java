package com.edu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edu.model.Register;
import com.edu.model.UserAppointments;
import com.edu.model.VO.AppointmentResponseWrapperVO;
import com.edu.model.VO.AppointmentStatusResponseVO;
import com.edu.model.VO.ChangeScheduleVO;
import com.edu.model.VO.CreateDoctorVO;
import com.edu.model.VO.DateChecker;
import com.edu.model.VO.DemoNewVO;
import com.edu.model.VO.DoctorRequestVO;
import com.edu.model.VO.DoctorResponseVO;
import com.edu.model.VO.DoctorResponseWrapperVO;
import com.edu.model.VO.FeebackIndexWrapperVO;
import com.edu.model.VO.FeedbackIndexVO;
import com.edu.model.VO.FeedbackVO;
import com.edu.model.VO.FeedbackWrapperVO;
import com.edu.model.VO.ListDoctorVO;
import com.edu.model.VO.ListScheduleVO;
import com.edu.model.VO.NewUserVO;
import com.edu.model.VO.RegisterVO;
import com.edu.model.VO.SampleVO;
import com.edu.model.VO.ScheduleRequestVO;
import com.edu.model.VO.ScheduleResponseWrapperVO;
import com.edu.model.VO.SchedulesVO;
import com.edu.model.VO.SimVO;
import com.edu.model.VO.SpcialisationWrapper;
import com.edu.model.VO.SpecialisationVO;
import com.edu.model.VO.UserAppointmentsVO;
import com.edu.model.VO.UserAppointmentsWrapperVO;
import com.edu.service.ClinicERPService;

@RestController
@RequestMapping("/data")
public class ClinicERPController {

	final static Logger logger = Logger.getLogger(ClinicERPController.class);
	
	@Autowired
	private ClinicERPService clinicErpService;
	
	@RequestMapping(value = "/executefile")
	public @ResponseBody String executeSampleService(@RequestBody MultipartFile file) throws IOException {
		System.out.println("in");
	    
		if (file.isEmpty()) {
			System.out.println("nithun");
		}
		else{
		        System.out.println("inyo");
				byte[] bytes = file.getBytes();
				System.out.println("read " + bytes.length + " bytes.");
				System.out.println(Arrays.toString(bytes));
		}
		    return null;
		}
	
	@RequestMapping("/getSample")
	public @ResponseBody String getSample(){
		logger.fatal("samples");
		String s1=clinicErpService.getSample();
		return s1;
	}
	
	@RequestMapping("/register")
	public @ResponseBody String register(@RequestBody RegisterVO registerVO) {
		String s1=clinicErpService.register(registerVO);
        return s1;
	}
	
	@RequestMapping("/retrieveuserpass")
	public @ResponseBody List<RegisterVO> retrieveuserpass() {
		List<RegisterVO> s1=clinicErpService.retrieveuserpass();
        return s1;
	}
	
	@RequestMapping("/changePassword")
	public @ResponseBody String changepassword(@RequestBody RegisterVO registerVO) {
		String s1=clinicErpService.changePassword(registerVO);
        return s1;
	}
	
	@RequestMapping("/login")
	public @ResponseBody String login(@RequestBody RegisterVO registerVO) {
		String s1=clinicErpService.login(registerVO);
		return s1;
	}
	
	@RequestMapping("/showDoctors")
	public @ResponseBody DoctorResponseWrapperVO showDoctors(){
		List<CreateDoctorVO> createDoctorVOList=clinicErpService.displayDoctor();
		DoctorResponseWrapperVO doctorResponseWrapperVO=new DoctorResponseWrapperVO();
		doctorResponseWrapperVO.setCreateDoctorVO(createDoctorVOList);
		return doctorResponseWrapperVO;
	}
	
	@RequestMapping("/saveSpecialisation")
	public @ResponseBody String saveSpecialisation(@RequestBody SpecialisationVO svo){
		String cg=clinicErpService.saveSpecilaisation(svo);
		return cg;
	}
	
	@RequestMapping("/editspecialisation")
	public @ResponseBody String editSpecialisation(@RequestBody SpecialisationVO specialisationVO){
		String sp=clinicErpService.editSpecialisation(specialisationVO);
		return sp;
	}
	
	@RequestMapping("/deletespecialisation")
	public @ResponseBody String deleteSpecialisation(@RequestBody SpecialisationVO specialisationVO){
		String sp=clinicErpService.deleteSpecialisation(specialisationVO);
		return sp;
		
	}
	
	@RequestMapping("/getSpecialisation")
	public @ResponseBody SpcialisationWrapper getSpecialisation(){
		List<SpecialisationVO> specialisationVOs=clinicErpService.getSpecilaisation();
		SpcialisationWrapper spcialisationWrapper=new SpcialisationWrapper();
		spcialisationWrapper.setSpecialisationVO(specialisationVOs);
		return spcialisationWrapper;
	}
	
	@RequestMapping("/getSpecialisation1")
	public @ResponseBody List<SpecialisationVO> getSpecialisation1(){
		List<SpecialisationVO> specialisationVOs=clinicErpService.getSpecilaisation();
		return specialisationVOs;
	}
	
	@RequestMapping("/doctor")
	public @ResponseBody String saveDoctor(@RequestBody CreateDoctorVO createDoctorVO){
		String cg=clinicErpService.saveDoctor(createDoctorVO);
		return cg;
	}
	
	@RequestMapping(value = "/listdoctors")
	public @ResponseBody ListDoctorVO listDoctor(@RequestBody SimVO simvo) throws ParseException {
		  String input_date=simvo.getSdate();
		  SimpleDateFormat format1=new SimpleDateFormat("dd-MMM-yyyy");
		  Date dt1=format1.parse(input_date);
		  DateFormat format2=new SimpleDateFormat("EEEE"); 
		  String finalDay=format2.format(dt1);
		  List<SchedulesVO> createDoctorVO=clinicErpService.getDoctor(finalDay);
	      ListDoctorVO listDoctorVO=new ListDoctorVO();
		  listDoctorVO.setSchedulesVO(createDoctorVO);
		  return listDoctorVO;
	}
	
	@RequestMapping("/showdoctors")
	public @ResponseBody List<CreateDoctorVO> showSchedules(){
		List<CreateDoctorVO> cg=clinicErpService.showdoctor();
		return cg;
	}
	
	@RequestMapping("/editdoctor")
	public @ResponseBody List<CreateDoctorVO> editDoctor(@RequestBody CreateDoctorVO createDoctorVO){
		List<CreateDoctorVO> cg=clinicErpService.editdoctor(createDoctorVO);
		return cg;
	}
	
	@RequestMapping("/deletedoctor")
	public @ResponseBody String deleteDoctor(@RequestBody CreateDoctorVO createDoctorVO){
		String cg=clinicErpService.deletedoctor(createDoctorVO);
		return "success";
	}
	
	@RequestMapping("/displayschedules")
	public @ResponseBody ScheduleResponseWrapperVO showSchedule(@RequestBody SimVO simVO )throws ParseException{
		  String s=simVO.getSdate();
		  SimpleDateFormat format1=new SimpleDateFormat("dd-MM-yyyy");
		  Date dt1=format1.parse(s);
		  System.out.println(s);
		  DateFormat format2=new SimpleDateFormat("EEEE"); 
		  String finalDay=format2.format(dt1);
	      List<DoctorResponseVO> cg=clinicErpService.showSchedule(s,finalDay);
		  ScheduleResponseWrapperVO scheduleResponseWrapperVO=new ScheduleResponseWrapperVO();
		  scheduleResponseWrapperVO.setDoctorResponseVO(cg);
		  return scheduleResponseWrapperVO;
	   
	}
	
	@RequestMapping("/availabilityEdit")
	public @ResponseBody DoctorResponseVO availabilityEdit(@RequestBody DoctorResponseVO doctorResponseVO) throws ParseException{
        DoctorResponseVO cg=clinicErpService.availabilityEdit(doctorResponseVO);
		return cg;
	}
	
	@RequestMapping("/changeSchedule")
	public @ResponseBody String changeschedule(@RequestBody DoctorRequestVO doctorRequestVO) throws ParseException{
		String cg=clinicErpService.changeSchedule(doctorRequestVO);
		return cg;
	}
	
	@RequestMapping("/deletechangeSchedule")
	public @ResponseBody String deleteChangeschedule(@RequestBody DoctorRequestVO doctorRequestVO) throws ParseException{
		String cg=clinicErpService.deletechangeSchedule(doctorRequestVO);
		return "success";
	}
	
	
	
	@RequestMapping("/demoschedules")
	public @ResponseBody ListScheduleVO showScheduledemo(@RequestBody SimVO simVO) throws ParseException{
		String s=simVO.getSdate();
		 SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
		  Date dt1=format1.parse(s);
		  DateFormat format2=new SimpleDateFormat("EEEE"); 
		  String finalDay=format2.format(dt1);
		List<ChangeScheduleVO> cg=clinicErpService.showScheduledemo(s,finalDay);
		ListScheduleVO listScheduleVO=new ListScheduleVO();
		listScheduleVO.setChangeScheduleVO(cg);
		return listScheduleVO;
	}
	
	@RequestMapping("/demoschedules1")
	public @ResponseBody ScheduleResponseWrapperVO showScheduledemo1(@RequestBody SimVO simVO) throws ParseException{
		String s=simVO.getSdate();
		 SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
		  Date dt1=format1.parse(s);
		  DateFormat format2=new SimpleDateFormat("EEEE"); 
		  String finalDay=format2.format(dt1);
		List<DoctorResponseVO> cg=clinicErpService.showScheduledemo1(s,finalDay);
		ScheduleResponseWrapperVO scheduleResponseWrapperVO=new ScheduleResponseWrapperVO();
		scheduleResponseWrapperVO.setDoctorResponseVO(cg);
		return scheduleResponseWrapperVO;
	}
	
	
	@RequestMapping("/checkemail")
	public @ResponseBody NewUserVO checkEmail(@RequestBody SimVO simVO){
		String email=simVO.getSdate();
		NewUserVO cg=clinicErpService.checkEmail(email);
		return cg;
	}
	
	@RequestMapping("/manageUser")
	public @ResponseBody SimVO saveUser(@RequestBody NewUserVO newUser) {
		String email = newUser.getEmail();
		String s3 = "email value null";
		if (email.equalsIgnoreCase(null) || email.equalsIgnoreCase("")) {
			SimVO specialisationVO = new SimVO(s3);
			return specialisationVO;
		} else {
			String cg = clinicErpService.saveUser(newUser);
			String s = "success";
			String s1 = "values successfully updated";
			if (cg.equalsIgnoreCase(s)) {
				SimVO specialisationVO = new SimVO(s);
				return specialisationVO;
			} else {

				SimVO specialisationVO = new SimVO(s1);
				return specialisationVO;
			}
		}
	}
	
	@RequestMapping("/newUserAppointments")
	public @ResponseBody SimVO userAppointment(@RequestBody UserAppointmentsVO userAppointments){
		String userEmail=userAppointments.getUserEmail();
		int doctorId=userAppointments.getDoctorId();
		String s1="null values";
		if(userEmail.equalsIgnoreCase(null) || userEmail.equalsIgnoreCase("") || doctorId==0){
			SimVO specialisationVO = new SimVO(s1);
			return specialisationVO;
		}
		else
		{
		String cg=clinicErpService.userappointment(userAppointments);
		SimVO specialisationVO = new SimVO(cg);
		return specialisationVO;
		
	    }
	
	}
	@RequestMapping("/displayAppointments")
	public @ResponseBody UserAppointmentsWrapperVO displayAppointment(){
		List<UserAppointmentsVO> userAppointmentsVO2=clinicErpService.displayappointment();
		UserAppointmentsWrapperVO userAppointmentsWrapperVO=new UserAppointmentsWrapperVO();
		userAppointmentsWrapperVO.setUserAppointmentsVO(userAppointmentsVO2);
		return userAppointmentsWrapperVO;
	}
	
	
	@RequestMapping("/displayuserAppointments")
	public @ResponseBody UserAppointmentsWrapperVO displayuserAppointment(@RequestBody UserAppointmentsVO userAppointmentsVO){
		List<UserAppointmentsVO> userAppointmentsVO2=clinicErpService.displayUserappointment(userAppointmentsVO);
		UserAppointmentsWrapperVO userAppointmentsWrapperVO=new UserAppointmentsWrapperVO();
		userAppointmentsWrapperVO.setUserAppointmentsVO(userAppointmentsVO2);
		return userAppointmentsWrapperVO;
	}
	
	@RequestMapping("/acceptUserAppointments")
	public @ResponseBody String acceptUserAppointment(@RequestBody UserAppointmentsVO userAppointmentsVO){
		String cg=clinicErpService.acceptUserappointment(userAppointmentsVO);
		return cg;
	}
	
	@RequestMapping("/rejectUserAppointments")
	public @ResponseBody String rejectUserAppointment(@RequestBody UserAppointmentsVO userAppointmentsVO){
		String cg=clinicErpService.rejectUserappointment(userAppointmentsVO);
		return cg;
	}
	
	@RequestMapping("/appointmentStatus")
	public @ResponseBody AppointmentResponseWrapperVO appointmentStatus(@RequestBody UserAppointmentsVO userAppointmentsVO){
			List<AppointmentStatusResponseVO> userAppointmentsVO2=clinicErpService.appointmentStatus(userAppointmentsVO);
		AppointmentResponseWrapperVO appointmentResponseWrapperVO=new AppointmentResponseWrapperVO();
		appointmentResponseWrapperVO.setAppointmentStatusResponseVO(userAppointmentsVO2);
		return appointmentResponseWrapperVO;
	}
	
	@RequestMapping("/displaydoctorAppointments")
	public @ResponseBody UserAppointmentsWrapperVO displaydoctorAppointments(@RequestBody UserAppointmentsVO userAppointmentsVO){
		List<UserAppointmentsVO> userAppointmentsVO2=clinicErpService.displaydoctorAppointment(userAppointmentsVO);
		UserAppointmentsWrapperVO userAppointmentsWrapperVO=new UserAppointmentsWrapperVO();
		userAppointmentsWrapperVO.setUserAppointmentsVO(userAppointmentsVO2);
		return userAppointmentsWrapperVO;
	}
	
	@RequestMapping("/displaydoctorAllAppointments")
	public @ResponseBody UserAppointmentsWrapperVO displaydoctorAllAppointments(@RequestBody UserAppointmentsVO userAppointmentsVO){
		System.out.println("diplaying all schedules of a doctors");
		System.out.println("dotcor is id "+userAppointmentsVO.getDoctorId());
		List<UserAppointmentsVO> userAppointmentsVO2=clinicErpService.displaydoctorAllAppointment(userAppointmentsVO);
		UserAppointmentsWrapperVO userAppointmentsWrapperVO=new UserAppointmentsWrapperVO();
		userAppointmentsWrapperVO.setUserAppointmentsVO(userAppointmentsVO2);
		return userAppointmentsWrapperVO;
	}
	
	@RequestMapping("/AddDoctorsAvailability")
	public @ResponseBody ScheduleResponseWrapperVO addDoctorsAvailability(@RequestBody DoctorResponseVO doctorResponseVO) throws ParseException{
		List<DoctorResponseVO> doctorResponseVO2=clinicErpService.addDoctorsAvailability(doctorResponseVO);
		ScheduleResponseWrapperVO scheduleResponseWrapperVO=new ScheduleResponseWrapperVO();
		scheduleResponseWrapperVO.setDoctorResponseVO(doctorResponseVO2);
		return scheduleResponseWrapperVO;
	}
	
	@RequestMapping("/SaveDoctorsAvailability")
	public @ResponseBody String saveDoctorsAvailability(@RequestBody DoctorResponseVO doctorResponseVO) throws ParseException{
		String cg=clinicErpService.saveDoctorsAvailability(doctorResponseVO);
		return cg;
	}
	
	@RequestMapping("/feedback")
	public @ResponseBody SimVO feedback(@RequestBody FeedbackVO feedbackVO){
		String cg=clinicErpService.feedback(feedbackVO);
		SimVO simVO=new SimVO(cg);
		return simVO;
	}
		
	@RequestMapping("/Showfeedback")
    public @ResponseBody FeedbackWrapperVO showFeedback(){
        List<FeedbackVO> cg=clinicErpService.ShowFeedback();
        FeedbackWrapperVO  feedBackWrapperVO=new FeedbackWrapperVO();
        feedBackWrapperVO.setFeedbackVO(cg);
        return feedBackWrapperVO;
    }
	
	@RequestMapping("/ratings")
    public @ResponseBody FeebackIndexWrapperVO ratings(){
		FeebackIndexWrapperVO cg=clinicErpService.rating();
	      return cg;
    }
	
}
