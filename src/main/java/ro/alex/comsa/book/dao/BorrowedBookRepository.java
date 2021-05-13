package ro.alex.comsa.book.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedBookRepository extends CrudRepository<BorrowedBookStatusModel, Long> {


    @Query("select b from BorrowedBookStatusModel b where b.book.isbn = ?1 and b.customer.id = ?2")
    List<BorrowedBookStatusModel> findByIsbnAndUserId(long isbn, long customerId);
}
