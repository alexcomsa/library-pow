package ro.alex.comsa.login;

public class ValidEmailResponse {

    boolean valid;

    public ValidEmailResponse() {
    }

    public ValidEmailResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
