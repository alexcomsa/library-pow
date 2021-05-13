package ro.alex.comsa.book.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookInventoryRepository extends CrudRepository<BookModel,Long> {

     @Query("select  bm from BookModel bm where bm.isbn = ?1")
     List<BookModel> findAllByIsbn();

     @Query(value = "select  bm from BookModel bm where bm.isbn = ?1 and bm.borrowed = false ")
     List<BookModel> reserveBook(long isbn, Pageable pageable);

}
