package ali.su.cft2j02.mapping;

import ali.su.cft2j02.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductRequestToProductMapper implements Mappable<ProductRequestMap, Product> {
    @Override
    public Product map(ProductRequestMap rq) {
        Product pr = new Product();
        pr.setProductCodeId(rq.getProductClass().getInternalId());
        pr.setType(rq.getProductRequest().getProductType());
        pr.setNumber(rq.getProductRequest().getContractNumber());
        pr.setPriority(rq.getProductRequest().getPriority());
        pr.setPenaltyRate(rq.getProductRequest().getInterestRatePenalty());
        pr.setTaxRate(rq.getProductRequest().getTaxPercentageRate());
        pr.setInterestRateType(rq.getProductRequest().getRateType());
        pr.setState("открыт");

        return pr;
    }
}