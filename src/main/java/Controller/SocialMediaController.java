package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    private AccountService accountService; 

     public SocialMediaController(){
        this.accountService = new AccountService();
     }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("register", this::registerAccount);
        app.post("login", this::loginAccount);

        return app;
    }

    private void registerAccount(Context ctx){ 
        try{
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.readValue(ctx.body(), Account.class);
            Account accountFromDB = this.accountService.registerAccount(account);
    
            if(accountFromDB != null){
                ctx.status(200); // better 201
                ctx.json(accountFromDB);
            }else{
                ctx.status(400);
            }
        }catch(JsonProcessingException ex){
            ctx.status(500);
        }
    }


    private void loginAccount(Context ctx){ 
        try{
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.readValue(ctx.body(), Account.class);
            Account accountFromDB = this.accountService.loginAccount(account);
    
            if(accountFromDB != null){
                ctx.status(200); // better 201
                ctx.json(accountFromDB);
            }else{
                ctx.status(401);
            }
        }catch(JsonProcessingException ex){
            ctx.status(500);
        }
    }
}