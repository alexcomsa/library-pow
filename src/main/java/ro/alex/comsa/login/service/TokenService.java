package ro.alex.comsa.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.alex.comsa.customer.dao.CustomerModel;
import ro.alex.comsa.customer.dao.CustomerRepository;
import ro.alex.comsa.login.LoginRequest;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {

    @Autowired
    public TokenService(CustomerRepository customerDAO) {
        this.customerDAO = customerDAO;
    }

    private CustomerRepository customerDAO;
    private final ConcurrentHashMap<String, Long> data = new ConcurrentHashMap<>();

    public String generateToken(LoginRequest request) {
        CustomerModel m = customerDAO.findIdByEmailAddress(request.getUsername());
        if (m != null) {
            String token = UUID.randomUUID().toString();
            data.put(token, m.getId());
            return token;
        }
        return null;
    }

    public boolean isValid(String token) {
        if(token == null || token.isEmpty()){
            return false;
        }
        return data.containsKey(token);
    }

    public long getUserIdForToken(String token) {
        return data.get(token);
    }

    public void removeAllEntries() {
        data.clear();
    }
}
