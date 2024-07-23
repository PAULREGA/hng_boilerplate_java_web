package hng_java_boilerplate.product.controller;

import hng_java_boilerplate.product.dto.ProductSearchDTO;
import hng_java_boilerplate.product.entity.Product;
import hng_java_boilerplate.product.product_mapper.ProductMapper;
import hng_java_boilerplate.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public CustomResponseDTO getProducts(Pageable pageable) {
        Page<Product> productPage = productService.getProducts(pageable);

        List<ProductDTO> productDTOs = productPage.getContent().stream()
            .map(product -> new ProductDTO(product.getName(), (float) product.getPrice(), product.getCategory())) // Explicit type cast
            .collect(Collectors.toList());

        PaginationDTO paginationDTO = new PaginationDTO(
            (int) productPage.getTotalElements(),
            productPage.getTotalPages(),
            productPage.getNumber() + 1
        );

        CustomResponseDTO response = new CustomResponseDTO();
        if(products.isEmpty()){
            productSearchDTO.setStatus_code(HttpStatus.NO_CONTENT.value());
            productSearchDTO.setProducts(ProductMapper.INSTANCE.toDTOList(products).getContent());
            productSearchDTO.setSuccess(true);
            productSearchDTO.setTotal(products.getTotalPages());
            productSearchDTO.setLimit(products.getSize());
            productSearchDTO.setPage(products.getNumber());
            return new ResponseEntity<>(productSearchDTO, HttpStatus.OK);
        }

        productSearchDTO.setStatus_code(HttpStatus.OK.value());
        productSearchDTO.setProducts(ProductMapper.INSTANCE.toDTOList(products).getContent());
        productSearchDTO.setTotal(products.getTotalPages());
        productSearchDTO.setLimit(products.getSize());
        productSearchDTO.setPage(products.getNumber());
        productSearchDTO.setSuccess(true);
        return new ResponseEntity<>(productSearchDTO, HttpStatus.OK);
    }
}
