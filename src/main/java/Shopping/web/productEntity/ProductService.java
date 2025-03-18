package Shopping.web.productEntity;

import Shopping.web.newEntity.MemberData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    // 한명만 id로 조회를 한다는 의미
    public Product getOneProduct(Product product) {  //MemberData 에 있는 id로 한명을 조회 해온다는 소리
        return productRepository.findById( product.getProductId()).orElse(null);  // 회원이 없으면 null을 보낸다는 소리
    }

    //상품 등록 / 상품 수정
    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public List<Product> getProductList() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }


}
