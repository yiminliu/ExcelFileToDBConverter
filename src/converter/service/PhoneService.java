package converter.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import converter.dao.PhoneDao;
import converter.domain.VerizonDLDiscPhoneData;


@Service("phoneService")
@Transactional
public class PhoneService {
	
	@Autowired
	@Qualifier("phoneDao")
	private PhoneDao phoneDao;
	
	public int save(VerizonDLDiscPhoneData phone) {
		return phoneDao.save(phone);
	}
	
	public void saveAllRecords(Set<Set<VerizonDLDiscPhoneData>> records){
		phoneDao.save(records);
	}
	/*
	public void saveAllRecords(Set<Set> records){
		//VerizonDLDiscPhoneData phone = null;
		for(Set cellStoreSet : records){
       		String stringCellValue = null;
            //for(int j = 0; j < cellStoreSet.size(); j++) {
       		Iterator it = records.iterator();
       		while(it.hasNext()){
       			Set<VerizonDLDiscPhoneData> set = (Set<VerizonDLDiscPhoneData>)it.next();
       			Iterator it2 = set.iterator();
       			while(it2.hasNext()){
        	          VerizonDLDiscPhoneData phone = (VerizonDLDiscPhoneData) it2.next();
        	          save(phone);
       			}
       		}		
                //stringCellValue = phone.toString();        
		}
	}
    */	
}
