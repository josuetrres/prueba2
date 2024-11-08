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
    }


 
