package lk.dialog.mou.web.service.customer.impl;

import lk.dialog.mou.db.repository.CustomerRepository;
import lk.dialog.mou.domain.Customer;
import lk.dialog.mou.web.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aux072 on 18/09/2018.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getCustomerByKeys(Customer customer) {
        return customerRepository.getCustomerByKeys(customer);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.addCustomer(customer);
    }
}
