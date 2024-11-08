package com.tercertotest.controller.dao;

import com.tercertotest.controller.dao.implement.AdapterDao;
import com.tercertotest.controller.models.RegistroHtml;
import com.tercertotest.controller.tda.LinkedList;

public class RegistroHtmlDao extends AdapterDao{
    private RegistroHtml registroHtml;
    private LinkedList<RegistroHtml> listAll;
    public RegistroHtmlDao() {
        super(RegistroHtml.class);
    }

    public void setRegistroHtml(RegistroHtml registroHtml) {
        this.registroHtml = registroHtml;
    }

    public RegistroHtml getRegistroHtml() {
        if (this.registroHtml == null) {
            this.registroHtml = new RegistroHtml();
        }
        return this.registroHtml;
    }

    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return this.listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        registroHtml.setId(id);
        this.persist(this.registroHtml);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        Integer index = getRegistroHtml().getId() - 1;
    
        if (index < 0 || index >= getListAll().getSize()) {
            throw new Exception("Índice de Censo inválido");
        }
        this.merge(getRegistroHtml(), index);
        this.listAll = listAll();
        return true;
    }

    public void deleteRegistroHtml(Integer id) throws Exception {
        // Asegúrate de que listAll esté inicializado
        if (listAll == null) {
            this.listAll = listAll(); // Inicializa si es necesario
        }
    
        // Buscar el índice del censo con el ID proporcionado
        for (int i = 0; i < listAll.getSize(); i++) {
            registroHtml = listAll.get(i); // Obtener el censo en la posición i
            if (registroHtml.getId().equals(id)) {
                // Si se encuentra el censo, eliminarlo usando el método delete
                listAll.delete(i);
                return; // Salir del método después de eliminar
            }
        }
    
        // Si no se encontró, lanzar una excepción
        throw new Exception("Censo no encontrado");
    }
    
    
}
