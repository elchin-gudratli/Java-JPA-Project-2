package com.bilgeadam.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bilgeadam.beans.ArabaBean;

import com.bilgeadam.model.Araba;


public class ArabaDAO {

	private static final String PERSISTENCE_UNIT_NAME = "ArabaJPA2";	
	private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	private static EntityTransaction transactionObj = entityMgrObj.getTransaction();

	// Method To Fetch All School Details From The Database
	@SuppressWarnings("unchecked")
	public static List<ArabaBean> getAllArabaDetails() {
		entityMgrObj.refresh(ArabaBean.class);
		Query queryObj = entityMgrObj.createQuery("SELECT a FROM Araba a");
		List<ArabaBean> arabaList = queryObj.getResultList();
		if (arabaList != null && arabaList.size() > 0) {			
			return arabaList;
		} else {
			return null;
		}
	}	
	@SuppressWarnings("unchecked")
	public static List<ArabaBean> getAllArabas() {
		
		Query queryObj = entityMgrObj.createQuery("SELECT a FROM Araba a");
		List<ArabaBean> arabaList = queryObj.getResultList();
		if (arabaList != null && arabaList.size() > 0) {			
			return arabaList;
		} else {
			return null;
		}
	}

	// Method To Add Create School Details In The Database
	public static String createNewAraba(String marka) {
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Araba newArabaObj = new Araba();
		//.setId(getMaxSchoolId());
		newArabaObj.setMarka(marka);
		entityMgrObj.persist(newArabaObj);
		transactionObj.commit();
		return "arabaLists.xhtml?faces-redirect=true";	
	}

	// Method To Delete The Selected School Id From The Database 
	public static String deleteArabaDetails(int arabaId) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Araba deleteArabaObj = new Araba();
		if(isArabaIdPresent(arabaId)) {
			deleteArabaObj.setId(arabaId);
			entityMgrObj.remove(entityMgrObj.merge(deleteArabaObj));
		}		
		transactionObj.commit();
		return "arabaLists.xhtml?faces-redirect=true";
	}

	// Method To Update The School Details For A Particular School Id In The Database
	public static String updateArabaDetails(int arabaId, String updatedArabaMarka) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		if(isArabaIdPresent(arabaId)) {
			Query queryObj = entityMgrObj.createQuery("UPDATE Araba a SET a.marka=:marka WHERE a.id= :id");			
			queryObj.setParameter("id", arabaId);
			queryObj.setParameter("marka", updatedArabaMarka);
			int updateCount = queryObj.executeUpdate();
			
			if(updateCount > 0) {
				System.out.println("Kayıt : " + arabaId + " Güncellendi");
			}
		}
		transactionObj.commit();
		FacesContext.getCurrentInstance().addMessage("editArabaForm:arabaId", new FacesMessage("Araba kaydı  #" + arabaId + " güncellendi"));
		return "arabaEdit.xhtml";
	}

	// Helper Method 1 - Fetch Maximum School Id From The Database
	private static int getMaxArabaId() {
		int maxArabaId = 1;
		Query queryObj = entityMgrObj.createQuery("SELECT MAX(a.id)+1 FROM Araba a");
		if(queryObj.getSingleResult() != null) {
			maxArabaId = (Integer) queryObj.getSingleResult();
		}
		return maxArabaId;
	}

	// Helper Method 2 - Fetch Particular School Details On The Basis Of School Id From The Database
	private static boolean isArabaIdPresent(int arabaId) {
		boolean idResult = false;
		Query queryObj = entityMgrObj.createQuery("SELECT a FROM Araba a WHERE a.id = :id");
		queryObj.setParameter("id", arabaId);
		Araba selectedArabaId = (Araba) queryObj.getSingleResult();
		if(selectedArabaId != null) {
			idResult = true;
		}
		return idResult;
	}
}
