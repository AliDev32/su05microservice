package ali.su.cft2j02.prodregister;

import ali.su.cft2j02.tst.Dto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corporate-settlement-account")
public class CreateProdRegister {
    @PostMapping("/create")
    public Dto create() {
        return new Dto("Hare Krishna!!!", 108);
    }
}
