package converter.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import converter.dao.PhoneDao;
import converter.domain.PhoneData;


@Service("phoneService")
@Transactional
public class PhoneService {
	
	@Autowired
	@Qualifier("phoneDao")
	private PhoneDao phoneDao;
	
	public int save(PhoneData phone) {
		return phoneDao.save(phone);
	}
	
	public void saveAllRecords(Set<Set<PhoneData>> records){
		phoneDao.save(records);
	}
	
	public List<PhoneData> getAllRecords(){
		return phoneDao.findAll();
	}
	/*
	public void saveAllRecords(Set<Set> records){
		//PhoneData phone = null;
		for(Set cellStoreSet : records){
       		String stringCellValue = null;
            //for(int j = 0; j < cellStoreSet.size(); j++) {
       		Iterator it = records.iterator();
       		while(it.hasNext()){
       			Set<PhoneData> set = (Set<PhoneData>)it.next();
       			Iterator it2 = set.iterator();
       			while(it2.hasNext()){
        	          PhoneData phone = (PhoneData) it2.next();
        	          save(phone);
       			}
       		}		
                //stringCellValue = phone.toString();        
		}
	}
    */	
}
