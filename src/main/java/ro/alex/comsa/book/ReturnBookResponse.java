package ro.alex.comsa.book;

public class ReturnBookResponse {

    public ReturnBookResponse(boolean returned) {
        this.returned = returned;
    }

    public ReturnBookResponse() {
    }

    private boolean returned;

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
