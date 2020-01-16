/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import Dto.CourseDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.SchoolFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;

/**
 *
 * @author jojus1101
 */
@Path("school")

public class SchoolResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final SchoolFacade FACADE = SchoolFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    
    @Context
    private UriInfo context;

    @GET
    @Path("/courselist")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CourseDTO> getCourseList() {

        List<CourseDTO> courses = FACADE.getCourseList();
        return courses;

    }
    
    @GET
    @Path("/populate")
    @Produces(MediaType.APPLICATION_JSON)
    public String populateDB() {
        FACADE.Populate();
        return "{\"msg\":\"Your database is ready\"}";
    }

}
