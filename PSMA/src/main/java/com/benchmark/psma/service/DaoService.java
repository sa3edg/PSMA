package com.benchmark.psma.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.benchmark.psma.dao.impl.EventDaoImpl;
import com.benchmark.psma.dao.impl.OfferDaoImpl;
import com.benchmark.psma.dao.impl.SubscriberBillsDaoImpl;
import com.benchmark.psma.dao.impl.SubscriberCardsDaoImpl;
import com.benchmark.psma.dao.impl.SubscriberDaoImpl;
import com.benchmark.psma.dao.impl.UserDaoImpl;
import com.benchmark.psma.dao.impl.UserRoleDaoImpl;
import com.benchmark.psma.dao.model.PointsForm;
import com.benchmark.psma.dao.model.RedeemPointsForm;
import com.benchmark.psma.dao.model.Subscriber;
import com.benchmark.psma.dao.model.SubscriberBills;
import com.benchmark.psma.dao.model.SubscriberCards;
import com.benchmark.psma.dao.model.SubscriberForm;
import com.benchmark.psma.dao.model.User;
import com.benchmark.psma.dao.model.UserRole;

@Service
public class DaoService {
	private static final Logger logger = LoggerFactory.getLogger(DaoService.class);
	
	@Value("${excel.export.usher.sheetName}")
    public String ushersSheetName;
	
	@Autowired  
	private EventDaoImpl eventDao;
	
	@Autowired  
	private OfferDaoImpl offerDao;
	
	@Autowired  
	private SubscriberDaoImpl subscriberDao;
	
	@Autowired  
	private SubscriberCardsDaoImpl subscriberCardDao;
	
	@Autowired  
	private SubscriberBillsDaoImpl subscriberBillDao;
	
	
	@Autowired  
	private UserRoleDaoImpl userRoleDao;
	
	@Autowired  
	private UserDaoImpl userDao;
	
	private static volatile DaoService instance;
	
	private DaoService(){
	}
	
	public static DaoService getInstance() {
		DaoService result = instance;
	    if (result == null) { // First check (no locking)
	        synchronized(DaoService.class) {
	            result = instance;
	            if (result == null) // Second check (with locking)
	            	instance = result = new DaoService();
	        }
	    }
	    return result;
	}
	public List<User> getUsers(){
		return userDao.getUsers();
	}
	public List<UserRole> getNotAdminUsers(){
		return userRoleDao.getNotAdminUsers();
	}
	public void addUser(User user){
		user.setEnabled(true);
		userDao.save(user);
		UserRole userRole = new UserRole();
		userRole.setUserName(user.getUserName());
		userRole.setRole(user.getRole());
		userRoleDao.insert(userRole);
	}
	
	public void deleteUser(String id){
		userRoleDao.delete(id);
		userDao.delete(id);
	}
	
	public EventDaoImpl getEventDaoImpl(){
		return eventDao;
	}
	
	public OfferDaoImpl getOfferDaoImpl(){
		return offerDao;
	}
	
	public void addSubscriber(SubscriberForm subscriberForm){
		Subscriber subscriber = new Subscriber();
		subscriber.setName(subscriberForm.getName());
		subscriber.setGender(subscriberForm.getGender());
		subscriber.setAge(subscriberForm.getAge());
		subscriber.setAddress(subscriberForm.getAddress());
		subscriber.setMobileNumber(subscriberForm.getMobileNumber());
		subscriber.setFacebookAccount(subscriberForm.getFacebookAccount());
		subscriber.setEmailAddress(subscriberForm.getEmailAddress());
		subscriber.setNationalIdNumber(subscriberForm.getNationalIdNumber());
		subscriber.setRegisterationDate(new Date());
		
		SubscriberCards card = new SubscriberCards();
		card.setSubscriberId(subscriberDao.getNextSubscriberId());
		card.setCardNumber(subscriberForm.getCardNumber());
		card.setStatus(SubscriberCards.ACTIVE);
		card.setRegisterationDate(new Date());
		card.setLastUpdateDate(new Date());
		
		subscriberDao.save(subscriber);
		subscriberCardDao.save(card);
		
	}
	
	public void addBillNumber(PointsForm pointsForm, double moneyToPoints){
		//add bill
		Date current = new Date();
		SubscriberCards card = subscriberCardDao.getCardByCardNumber(pointsForm.getCardNumber());
		SubscriberBills bill = new SubscriberBills();
		bill.setAmount(Integer.parseInt(pointsForm.getResetCount()));
		bill.setSubscriberId(card.getSubscriberId());
		bill.setStatus(SubscriberBills.ADDED);
		bill.setBillNumber(pointsForm.getResetNumber());
		bill.setBillDate(current);
		subscriberBillDao.save(bill);
		
		//update subscriber card
		int points = card.getPoints() + (int)(moneyToPoints*Integer.parseInt(pointsForm.getResetCount()));
		card.setPoints(points);
		card.setLastUpdateDate(current);
		subscriberCardDao.save(card);
		
	}
	
	public void redeemPoints(RedeemPointsForm pointsForm){
		//update subscriber card
		Date current = new Date();
		SubscriberCards card = subscriberCardDao.getCardByCardNumber(pointsForm.getCardNumber());
		if("yes".equals(pointsForm.getReddemAllPoints())){
			card.setPoints(0);
		}else{
			int points = card.getPoints() - Integer.parseInt(pointsForm.getPointsCount());
			card.setPoints(points);
		}
		card.setLastUpdateDate(current);
		subscriberCardDao.save(card);
		
	}
	public SubscriberCards getSubscriberCard(String cardNumber){
		
		return subscriberCardDao.getCardByCardNumber(cardNumber);
	}

	public boolean isCardRelatedToSubscriber(String mobilenumber, String cardNumber){
		
		Subscriber subscriber = subscriberDao.getSubscriberByMobileNumber(mobilenumber);
		SubscriberCards card = subscriberCardDao.getCardByCardNumber(cardNumber);
		return subscriber != null && card != null && (subscriber.getId().equals(card.getSubscriberId()));
	}
	
	public boolean isExistSubscriberMobileNumber(String mobilenumber){
		return subscriberDao.isExistMobileNumber(mobilenumber);
	}
	
	public boolean isExistSubscriberEmail(String email){
		return subscriberDao.isExistEmail(email);
	}
	
	public boolean isExistSubscriberNationalNumber(String nationalNumber){
		return subscriberDao.isExistNationalNumber(nationalNumber);
	}
	
	public boolean isExistSubscriberCardNumber(String cardNumber){
		return subscriberCardDao.isExistCardNumber(cardNumber);
	}
	
	public boolean isExistBillNumber(String billNumber){
		return subscriberBillDao.isExistBillNumber(billNumber);
	}
}
