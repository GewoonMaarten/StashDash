package ai.openbanking.OpenBanking.controller;

import ai.openbanking.OpenBanking.exception.BankAccountNotFoundException;
import ai.openbanking.OpenBanking.exception.UserNotFoundException;
import ai.openbanking.OpenBanking.model.BankAccount;
import ai.openbanking.OpenBanking.model.User;
import ai.openbanking.OpenBanking.repository.BankAccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankAccount")
public class BankAccountController {

    private final BankAccountRepository repository;

    BankAccountController(BankAccountRepository repository){ this.repository = repository; }

    @GetMapping("")
    Page<BankAccount> all(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @GetMapping("{id}")
    BankAccount one(@PathVariable Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException(id));
    }

    @GetMapping("/user")
    List<BankAccount> findByUser(User user) {
        return repository.findByUser(user)
                .orElseThrow(() -> new UserNotFoundException(user.getId()));
    }
}
