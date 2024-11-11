package com.example.rest;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.tercertotest.controller.dao.services.RegistroHtmlServices;
import com.tercertotest.controller.models.RegistroHtml;
import com.tercertotest.controller.models.RegistroValidator;

@Path("registro")
public class RegistroHtmlApi {
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCensos() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        RegistroHtmlServices rs = new RegistroHtmlServices();
        try {
            map.put("msg", "OK");
            map.put("data", rs.listAll().toArray());

            if (rs.listAll().isEmpty()) {
                map.put("data", new Object[]{});
            }

            return Response.ok(map).build();
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("msg", "Error");
            error.put("data", e.toString());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveRegistroHtml(String json) {
        String jsonResponse = "";
        RegistroHtmlServices rs = new RegistroHtmlServices();
        Gson gson = new Gson();
        
        try {
            RegistroHtml registroHtml = gson.fromJson(json, RegistroHtml.class);
            rs.setRegistroHtml(registroHtml);
            rs.save();
            jsonResponse = "{\"msg\":\"OK\",\"data\":\"RegistroHtml guardado correctamente\", \"info\":" + gson.toJson(registroHtml) + "}";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        
            jsonResponse = "{\"msg\":\"ERROR\",\"data\":\"" + e.getMessage() + "\"}";
        }
        
        return Response.ok(jsonResponse).build();
    }



    @Path("/validate")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateHtmlCode(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        RegistroHtmlServices rs = new RegistroHtmlServices();
        
        try {
    
            Integer id = Integer.parseInt(map.get("id").toString());
            RegistroHtml existingRegistro = rs.get(id);
            
            if (existingRegistro == null) {
                res.put("msg", "ERROR");
                res.put("data", "No existe un registro con ese identificador");
                return Response.status(Status.NOT_FOUND).entity(res).build();
            }
            

            boolean esCorrecto = existingRegistro.validarCodigoHtml(existingRegistro.getCodigo());
            existingRegistro.setEsCorrecto(esCorrecto);
            
        
            rs.setRegistroHtml(existingRegistro);
            rs.update();
            
            res.put("msg", "OK");
            res.put("data", "Código validado correctamente");
            return Response.ok(res).build();
            
        } catch (Exception e) {
            System.out.println("Error al validar el código: " + e.toString());
            res.put("msg", "ERROR");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
    

 
