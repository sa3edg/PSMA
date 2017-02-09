package com.benchmark.psma.controller.presentation;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.benchmark.psma.dao.model.PointsForm;
import com.benchmark.psma.dao.model.RedeemPointsForm;
import com.benchmark.psma.dao.model.SubscriberCards;
import com.benchmark.psma.dao.model.SubscriberForm;

@Controller
public class MerchantController extends BaseViewController {
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);
	@Value("${psma.moneyToPoints.percentage}")
    private double moneyToPoints;
	
	@Value("${psma.pointsToMoney.percentage}")
    private double pointsToMoney;
	
	@RequestMapping(value = "/addSubscriber", method = RequestMethod.POST, consumes="application/json", headers = {"content-type=application/json"})
	public @ResponseBody String addSubscriber (@RequestBody final  SubscriberForm subscriberForm ) {
		
		try{
			if(StringUtils.isEmpty(subscriberForm.getName())){
				return messageSource.getMessage("subscriber.valid.name", null,"enter valid name", LocaleContextHolder.getLocale());
			}
			if(StringUtils.isEmpty(subscriberForm.getMobileNumber())){
				return messageSource.getMessage("subscriber.valid.mobileNumber", null,"enter valid mobile number", LocaleContextHolder.getLocale());
			}
			if(StringUtils.isEmpty(subscriberForm.getNationalIdNumber())){
				return messageSource.getMessage("subscriber.valid.nationalId", null,"enter valid national number", LocaleContextHolder.getLocale());
			}
			if(StringUtils.isEmpty(subscriberForm.getEmailAddress())){
				return messageSource.getMessage("subscriber.valid.email", null,"enter valid email address", LocaleContextHolder.getLocale());
			}
			if(StringUtils.isEmpty(subscriberForm.getCardNumber())){
				return messageSource.getMessage("subscriber.valid.cardNumber", null,"enter valid card number", LocaleContextHolder.getLocale());
			}
			if(daoService.isExistSubscriberMobileNumber(subscriberForm.getMobileNumber())){
				return messageSource.getMessage("subscriber.mobileNumber.alreadyExist", null,"mobile number already exist", LocaleContextHolder.getLocale());
			}
			if(daoService.isExistSubscriberEmail(subscriberForm.getEmailAddress())){
				return messageSource.getMessage("subscriber.email.alreadyExist", null,"email already exist", LocaleContextHolder.getLocale());
			}
			if(daoService.isExistSubscriberNationalNumber(subscriberForm.getNationalIdNumber())){
				return messageSource.getMessage("subscriber.nationalNumber.alreadyExist", null,"national number already exist", LocaleContextHolder.getLocale());
			}
			if(daoService.isExistSubscriberCardNumber(subscriberForm.getCardNumber())){
				return messageSource.getMessage("subscriber.cardNumber.alreadyExist", null,"card number already exist", LocaleContextHolder.getLocale());
			}
			
			daoService.addSubscriber(subscriberForm);
		}
		catch(Exception ex){
			logger.error("subscriber add failed, mobile {0}, card number {1}", new Object[]{subscriberForm.getMobileNumber(), subscriberForm.getCardNumber()}, ex);
			return messageSource.getMessage("subscriber.add.failed", null,"subscriber added failed", LocaleContextHolder.getLocale());
		}
		logger.info("subscriber add succeed, mobile {0}, card number {1}", new Object[]{subscriberForm.getMobileNumber(), subscriberForm.getCardNumber()});
	    return messageSource.getMessage("subscriber.add.succeed", null,"subscriber added succeed", LocaleContextHolder.getLocale());
	}
	
	@RequestMapping(value = "/addPoints", method = RequestMethod.POST, consumes="application/json", headers = {"content-type=application/json"})
	public @ResponseBody String addPoints (@RequestBody final  PointsForm pointsForm ) {
		try{
			if(StringUtils.isEmpty(pointsForm.getMobileNumber())){
				return messageSource.getMessage("subscriber.valid.mobileNumber", null,"enter valid mobile number", LocaleContextHolder.getLocale());
			}
			if(StringUtils.isEmpty(pointsForm.getCardNumber())){
				return messageSource.getMessage("subscriber.valid.cardNumber", null,"enter valid card number", LocaleContextHolder.getLocale());
			}
			if(StringUtils.isEmpty(pointsForm.getResetNumber())){
				return messageSource.getMessage("subscriberBill.valid.resetNumber", null,"enter valid bill number", LocaleContextHolder.getLocale());
			}
			if(StringUtils.isEmpty(pointsForm.getResetCount())){
				return messageSource.getMessage("subscribeBillr.valid.resetCount", null,"enter valid bill count", LocaleContextHolder.getLocale());
			}
			
			if(!daoService.isExistSubscriberMobileNumber(pointsForm.getMobileNumber())){
				return messageSource.getMessage("subscriber.mobileNumber.notExist", null,"mobile number does not exist", LocaleContextHolder.getLocale());
			}
			
			if(!daoService.isExistSubscriberCardNumber(pointsForm.getCardNumber())){
				return messageSource.getMessage("subscriber.cardNumber.notExist", null,"card number does not exist", LocaleContextHolder.getLocale());
			}
			
			if(daoService.isExistBillNumber(pointsForm.getResetNumber())){
				return messageSource.getMessage("subscriberBill.billNumber.alreadyExist", null,"bill number already added before", LocaleContextHolder.getLocale());
			}
			
			if(!daoService.isCardRelatedToSubscriber(pointsForm.getMobileNumber(), pointsForm.getCardNumber())){
				return messageSource.getMessage("subscriber.mobileNumber.notRelatedToCardNumber", null,"mobile number does not related to card number", LocaleContextHolder.getLocale());
			}
			logger.debug("money to point="+moneyToPoints);
			daoService.addBillNumber(pointsForm, moneyToPoints);
		}
		catch(Exception ex){
			logger.error("bill add failed, mobile {0}, card number {1}, bill number {2}", new Object[]{pointsForm.getMobileNumber(), pointsForm.getCardNumber(), pointsForm.getResetNumber()}, ex);
			return messageSource.getMessage("bill.add.failed", null,"subscriber added failed", LocaleContextHolder.getLocale());
		}
		logger.info("bill add succeed, mobile {0}, card number {1}, bill number {2}", new Object[]{pointsForm.getMobileNumber(), pointsForm.getCardNumber(), pointsForm.getResetNumber()});
	    return messageSource.getMessage("bill.add.succeed", null,"subscriber added succeed", LocaleContextHolder.getLocale());
	}
	
	@RequestMapping(value = "/redeemPoints", method = RequestMethod.POST, consumes="application/json", headers = {"content-type=application/json"})
	public @ResponseBody String redeemPoints (@RequestBody final  RedeemPointsForm pointsForm ) {
		try{
			if(StringUtils.isEmpty(pointsForm.getMobileNumber())){
				return messageSource.getMessage("subscriber.valid.mobileNumber", null,"enter valid mobile number", LocaleContextHolder.getLocale());
			}
			if(StringUtils.isEmpty(pointsForm.getCardNumber())){
				return messageSource.getMessage("subscriber.valid.cardNumber", null,"enter valid card number", LocaleContextHolder.getLocale());
			}
			
			if("false".equals(pointsForm.getShowPoint()) && "no".equals(pointsForm.getReddemAllPoints()) && StringUtils.isEmpty(pointsForm.getPointsCount())){
				return messageSource.getMessage("card.valid.pointsNumber", null,"enter valid points number", LocaleContextHolder.getLocale());
			}
			
			if(!daoService.isExistSubscriberMobileNumber(pointsForm.getMobileNumber())){
				return messageSource.getMessage("subscriber.mobileNumber.notExist", null,"mobile number does not exist", LocaleContextHolder.getLocale());
			}
			
			if(!daoService.isExistSubscriberCardNumber(pointsForm.getCardNumber())){
				return messageSource.getMessage("subscriber.cardNumber.notExist", null,"card number does not exist", LocaleContextHolder.getLocale());
			}
			
			
			if(!daoService.isCardRelatedToSubscriber(pointsForm.getMobileNumber(), pointsForm.getCardNumber())){
				return messageSource.getMessage("subscriber.mobileNumber.notRelatedToCardNumber", null,"mobile number does not related to card number", LocaleContextHolder.getLocale());
			}
			if("true".equals(pointsForm.getShowPoint())){
				SubscriberCards card = daoService.getSubscriberCard(pointsForm.getCardNumber());
				logger.info("show points succeed, mobile {0}, card number {1}", new Object[]{pointsForm.getMobileNumber(), pointsForm.getCardNumber()});
				return messageSource.getMessage("card.showPoints.succeed", new Object[]{card.getPoints()},"card points ={0}", LocaleContextHolder.getLocale());
			}
			
		}
		catch(Exception ex){
			logger.error("redeem points failed, mobile {0}, card number {1}", new Object[]{pointsForm.getMobileNumber(), pointsForm.getCardNumber()}, ex);
			return "true".equals(pointsForm.getShowPoint()) ? messageSource.getMessage("card.showPoints.failed", null,"show points failed", LocaleContextHolder.getLocale()):
				messageSource.getMessage("card.redeemPoints.failed", null,"redeem points failed", LocaleContextHolder.getLocale());
		}
		daoService.redeemPoints(pointsForm);
		int moneyAmount = (int)pointsToMoney*Integer.parseInt(pointsForm.getPointsCount());
		logger.info("redeem points succeed, mobile {0}, card number {1}", new Object[]{pointsForm.getMobileNumber(), pointsForm.getCardNumber()});
		return messageSource.getMessage("card.redeemPoints.succeed", new Object[]{moneyAmount},"Your money count ={0}", LocaleContextHolder.getLocale());
	}
}
