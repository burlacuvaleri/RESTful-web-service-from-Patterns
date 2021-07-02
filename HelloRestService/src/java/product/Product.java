package product;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author v.burlacu
 */
@Path("product")
public class Product {

    @Context
    private UriInfo context;

    List<User> myUsers = new ArrayList() {
        {
            add(new User(1, "Ion"));
            add(new User(2, "Ana"));
            add(new User(3, "Laura"));
            add(new User(4, "Olga"));
            add(new User(5, "Petru"));
        }
    };

    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHTML(String content) {
    }

    // Get User by Id
    @GET
    @Path("users/{id}")
    @Produces("application/json")
    public String getUser(@PathParam("id") int id) {
        User user = myUsers.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        if (user != null) {
            return user.toJSON();
        }
        return "{}";
    }

    // Get All Users
    @GET
    @Path("users")
    @Produces("application/json")
    public String getAllUser() {
        // All Users
        if (myUsers != null) {
            String usersToJSON = "{\"users\":[" + myUsers.get(0).toJSON();

            for (int i = 1; i < myUsers.size(); i++) {
                usersToJSON += "," + myUsers.get(i).toJSON();
            }
            return usersToJSON + "]}";
        }

        // If not exist any User
        return "{\"users\":[]}";
    }

    // Delete User by Id
    @DELETE
    @Path("users/{id}")
    public void deleteAuto(@PathParam("id") int id) {
        if (id < myUsers.size() && myUsers != null) {
            myUsers.remove(id);
            System.out.println("The User with id={" + id + "} was REMOVED!");
        } else {
            System.out.println("The User with id={" + id + "} was NOT REMOVED!");
        }
    }
}
