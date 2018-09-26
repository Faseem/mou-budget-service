package lk.dialog.mou.web.controller;

import lk.dialog.mou.aspect.annotations.EnableAudit;
import lk.dialog.mou.domain.Customer;
import lk.dialog.mou.domain.User;
import lk.dialog.mou.web.service.customer.CustomerService;
import lk.dialog.mou.web.service.user.UserService;
import lk.dialog.mou.web.util.APIResponse;
import lk.dialog.mou.web.util.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Aux072 on 19/09/2018.
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger("CONTROLLER");

    @Autowired
    private CustomerService customerService;

    @Autowired
    UserService userService;

    @EnableAudit(description = "SaveCustomer Customer")
    @PostMapping(value = "/saveCustomer")
    ResponseEntity<APIResponse> saveCustomer(@RequestBody Customer customer,  @RequestHeader(value = "user") String user){
        Long sysCreatedUserId = customer.getCreatedSysUser();
        if(sysCreatedUserId == null){
            User sysUser = userService.getUserByUserName(user);
            if(sysUser != null){
                customer.setCreatedSysUser(sysUser.getUserId());
                customer.setCreatedUser(sysUser.getUserName());
            }else{
                User userAdded = userService.addUser(new User(user));
                customer.setCreatedSysUser(userAdded.getUserId());
            }
        }
        Date customerCreatedDate = customer.getCreatedDate();
        if(customerCreatedDate == null){
            customer.setCreatedDate(new Date());
        }
        return ResponseBuilder.build(ResponseBuilder.success(customerService.addCustomer(customer)));
    }

    @EnableAudit(description = "getCustomerByKeys Customer")
    @GetMapping(value = "/getCustomerByKeys")
    public ResponseEntity<APIResponse> getCustomerByKeys(@RequestBody Customer customer,  @RequestHeader(value = "user") String user) {
        return ResponseBuilder.build(ResponseBuilder.success(customerService.getCustomerByKeys(customer)));
    }
}
