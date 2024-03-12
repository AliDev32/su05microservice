package ali.su.cft2j02.product;

import ali.su.cft2j02.tst.Dto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/corporate-settlement-instance")
public class CreateProduct {
    @PostMapping("/create")
    public Dto create(@RequestBody Dto dto) {
//        dto.setStr(dto.getStr() + "\nHare Krishna!!!");
        dto.setNum(108);
        return dto;
    }
}
