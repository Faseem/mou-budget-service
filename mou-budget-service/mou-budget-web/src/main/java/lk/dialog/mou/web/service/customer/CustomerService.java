package lk.dialog.mou.web.service.customer;

import lk.dialog.mou.domain.Customer;

/**
 * Created by Aux072 on 18/09/2018.
 */
public interface CustomerService {
    public Customer getCustomerByKeys(Customer customer);
    public Customer addCustomer(Customer customer);
}
