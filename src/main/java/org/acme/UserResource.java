package org.acme;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserRepository userRepository;


    @GET
    public Response getAllUsers(){
        try {
            List<User> users = userRepository.listAll();
            ResponseC responseC = new ResponseC();
            responseC.setData(users);
            responseC.setError("no error");
            responseC.setStatus("00");
            responseC.setMsg("success");
            return Response.ok(new Gson().toJson(responseC)).build();
        }catch (Exception e){
            ResponseC responseC = new ResponseC();
            responseC.setData(null);
            responseC.setError("error");
            responseC.setStatus("99");
            responseC.setMsg("failure");
            return Response.ok(new Gson().toJson(responseC)).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id){
        return Response.ok(userRepository.findById(id)).build();
    }

    @PUT
    @Path("/updateById/{id}")
    @Transactional
    public User updateUser(@PathParam("id") Long id, User user){
        User entity = userRepository.findById(id);
        entity.setEmail(user.getEmail());

//        userRepository.update("email=tariquehassan@gmail.com", entity.getId());
        return entity;
    }

    @DELETE
    @Transactional
    @Path("/deleteById/{id}")
    public void deleteById(@PathParam("id") Long id){
        userRepository.deleteById(id);

    }

    @Transactional
    @DELETE
    @Path("/deleteAll")
    public void deleteUser(){
            userRepository.deleteAll();
    }

    @Transactional
    @POST
    public Response create(User user) {
        if (user.getId() != null)
            throw new WebApplicationException("ID was invalidly set!", 422);
        userRepository.persist(user);

        return Response.ok(user).status(201).build();
    }

}
