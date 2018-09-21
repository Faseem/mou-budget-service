package lk.dialog.mou.db.repository;

import lk.dialog.mou.domain.Customer;
import lk.dialog.mou.domain.MOUAgreement;

/**
 * Created by Aux072 on 18/09/2018.
 */
public interface CustomerRepository {
    Customer getCustomerByKeys(Customer customer);
    Customer addCustomer(Customer customer);
}
