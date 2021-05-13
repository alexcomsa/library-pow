package ro.alex.comsa.book;

public class BorrowBookResponse {

 private Long borrowId;
 private Long bookInventoryId;

    public BorrowBookResponse() {
    }

    public BorrowBookResponse(Long borrowId, Long bookInventoryId) {
        this.borrowId = borrowId;
        this.bookInventoryId = bookInventoryId;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public Long getBookInventoryId() {
        return bookInventoryId;
    }

    public void setBookInventoryId(Long bookInventoryId) {
        this.bookInventoryId = bookInventoryId;
    }
}
