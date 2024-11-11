package com.tercertotest.controller.dao.services;

import com.google.gson.Gson;
import com.tercertotest.controller.dao.RegistroHtmlDao;
import com.tercertotest.controller.models.RegistroHtml;
import com.tercertotest.controller.tda.LinkedList;

public class RegistroHtmlServices {
    private RegistroHtmlDao obj;

    public RegistroHtmlServices() {
        this.obj = new RegistroHtmlDao();
    }

    public Boolean save() throws Exception{
        
        return this.obj.save();
    }

    public LinkedList<RegistroHtml> listAll() {
        return this.obj.listAll();
    }
    public RegistroHtml getRegistroHtml(){
        return this.obj.getRegistroHtml();
    }
    public void setRegistroHtml(RegistroHtml registroHtml){
        this.obj.setRegistroHtml(registroHtml);
    }

    public RegistroHtmlDao getRegistroHtmlDao() throws Exception{
        return this.obj;
    }
    public RegistroHtml get(Integer id) throws Exception{
      
        return (RegistroHtml) obj.get(id);
    }

    public Boolean update() throws Exception {
    
        return obj.update();
    }

    public String toJson() {
        LinkedList<RegistroHtml> censos = listAll(); 
        return new Gson().toJson(censos); 
    }
    
    public void deleteRegistroHtml(Integer id) throws Exception {
        obj.deleteRegistroHtml(id);
    }
}
