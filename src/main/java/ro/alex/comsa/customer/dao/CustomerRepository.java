package ro.alex.comsa.customer.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerModel, Long> {

    CustomerModel findById(long id);

    @Modifying
    @Query("update CustomerModel c set c.emailAddress =?1, c.homeAddress= ?2, c.name = ?3 where c.id = ?4")
    void updateById(String emailAddress, String homeAddress, String name, Long id);


    @Query("select c from CustomerModel c where c.name = ?1")
    CustomerModel findIdByName(String username);


    @Query("select c from CustomerModel c where c.emailAddress = ?1")
    CustomerModel findIdByEmailAddress(String emailAddress);

    @Query("select case when count(c) > 0 then true else false end from CustomerModel c where c.emailAddress =?1")
    boolean existsByEmailAddress(String email);
}
