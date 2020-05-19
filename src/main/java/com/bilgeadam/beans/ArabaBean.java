package com.bilgeadam.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import com.bilgeadam.dao.ArabaDAO;;

@ManagedBean(name = "arabaBean")
public class ArabaBean {
	
	@PostConstruct
	public void init()
	{
		ArabaDAO.getAllArabas();
	}
	

	// Method To Fetch The Existing School List From The Database
	public List<ArabaBean> arabaListFromDb() {
		return ArabaDAO.getAllArabaDetails();		
	}

	public List<ArabaBean> AllArabaListFromDb() {
		return ArabaDAO.getAllArabas();		
	}

	// Method To Add New School To The Database
	public String addNewAraba(ArabaBean arabaBean) {
		return ArabaDAO.createNewAraba(arabaBean.getMarka());		
	}

	// Method To Delete The School Details From The Database
	public String deleteArabaById(int arabaId) {		
		return ArabaDAO.deleteArabaDetails(arabaId);		
	}

	// Method To Navigate User To The Edit Details Page And Passing Selecting School Id Variable As A Hidden Value
	public String editArabaDetailsById() {
		editArabaId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedArabaId");		
		marka = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedArabaMarka");
		return "arabaEdit.xhtml";
	}

	// Method To Update The School Details In The Database
	public String updateArabaDetails(ArabaBean arabaBean) {
		return ArabaDAO.updateArabaDetails(Integer.parseInt(arabaBean.getEditArabaId()), arabaBean.getMarka());		
	}
	
	private int id;
	private String marka;	
	public String getMarka() {
		return marka;
	}


	public void setMarka(String marka) {
		this.marka = marka;
	}

	private String editArabaId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getEditArabaId() {
		return editArabaId;
	}
	public void setEditArabaId(String editArabaId) {
		this.editArabaId = editArabaId;
	}
	
}
