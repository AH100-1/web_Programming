package Shopping.web.controller;

import Shopping.web.productEntity.Product;
import Shopping.web.productEntity.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class productController {
    private final ProductService productService;
    //상품 등록 화면 이동
    @GetMapping("/product")
    public String product() {
        return "product";
    }

    //상품 등록(저장, 수정)
    @GetMapping("/productSave")
    public ResponseEntity<HttpStatus> productSave(Product product) {
        if (product.getProductId() != null){
            System.out.println("상품 수정 하는 경우>" + product.getProductId());
            product.setProductId(product.getProductId());
        }

        product.setName(product.getName());
        product.setPrice(product.getPrice());
        product.setCount(product.getCount());
        product.setUrl(product.getUrl());
        product.setKind(product.getKind());

        productService.saveProduct(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //상품 상세 조회 / 수정 화면 이동
    @GetMapping("/productDetail")
    public String productDetail(Model model, Product product) {
        // productDetail?productId=1
        Product productData = productService.getOneProduct(product);
        model.addAttribute("productData", productData);
        return "productEdit";
    }
}
