package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {


    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/")
    public ResponseEntity<List<Customer>> home(
            HttpServletRequest request,
            HttpServletResponse response) {

        List<Customer> list = new ArrayList<>();
        for (Customer customer:customerRepository.findAll()
             ) {
            list.add(customer);
        }
        return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);

    }


}
