package converter.dao;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import converter.domain.VerizonDLDiscPhoneData;


@Repository("phoneDao")
//@Transactional
public class PhoneDao extends HibernateDaoSupport{

	@Autowired
	public void init(HibernateTemplate hibernateTemplate) {
	   setHibernateTemplate(hibernateTemplate);
	}
	
	public int save(VerizonDLDiscPhoneData phone){
	   int id = (Integer) getHibernateTemplate().save(phone);	   
	   return id;
	}   
	
	public void save(final Set<Set<VerizonDLDiscPhoneData>> records){
      Set<VerizonDLDiscPhoneData> phoneSet = null;	
      VerizonDLDiscPhoneData phone = null;
	  HibernateTemplate ht = getHibernateTemplate();
	  ht.setFlushMode(ht.FLUSH_COMMIT);
      // for(int i = 0; i < records.size(); i++) {
	      Iterator it = records.iterator();
	      while(it.hasNext()){
	    	  phoneSet = (Set<VerizonDLDiscPhoneData>)it.next();
	    	  Iterator it2 = phoneSet.iterator();
	     	  while(it2.hasNext()){
	       	      phone = (VerizonDLDiscPhoneData) it2.next();
                  ht.saveOrUpdate(phone);
	       	  } 
		  }
	      // if(i % 50 == 0) {
	      //   ht.flush();
	      //   ht.clear();
	      //  System.out.println("Flushed-Cleared");
	      //}
      } 		
	//}	
}
