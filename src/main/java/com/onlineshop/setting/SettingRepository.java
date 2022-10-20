package com.onlineshop.setting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface SettingRepository extends CrudRepository<Setting, String> {
	
	//@Query("SELECT s FROM Setting s WHERE s.category = ?1")
   public List <Setting> findSettingByCategory(SettingCategory settingCategory);
   
  
}
