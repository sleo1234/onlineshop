package com.onlineshop.setting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class SettingService {
	
	@Autowired private SettingRepository settingRepo;

	
	 public List <Setting> listAllSettings (){
		 
		 return (List <Setting>) settingRepo.findAll();
	 }
	 
	 public GeneralSettingBag getGeneralSettings() {
		 
		 List<Setting> generalSettings = new ArrayList<Setting>();
		 List<Setting> settings = settingRepo.findSettingByCategory(SettingCategory.GENERAL);
		 List<Setting> currencySettings = settingRepo.findSettingByCategory(SettingCategory.CURRENCY);
	       generalSettings.addAll(settings);
	       generalSettings.addAll(currencySettings);
	 return new GeneralSettingBag(generalSettings);
	 }
	 
	 
	 public void saveAll(Iterable<Setting> settings) {
		 
		  settingRepo.saveAll(settings);
	 }
	 
	 
	 
	 public List <Setting> getMailServerSettings (){
		 
		 return settingRepo.findSettingByCategory(SettingCategory.MAIL_SERVER);
	 }
	 
	 public List<Setting> getMailTemplateSettings(){
		 
		 
		 return settingRepo.findSettingByCategory(SettingCategory.MAIL_TEMPLATES);
	 }

	 
	 
	 public EmailSettingBag getEmailSettings () {
			List<Setting> settings = settingRepo.findSettingByCategory(SettingCategory.MAIL_SERVER);
			 
			 settings.addAll(settingRepo.findSettingByCategory(SettingCategory.MAIL_TEMPLATES));
			 return new EmailSettingBag(settings);
		 }
	 
	 
	 
	 
}
