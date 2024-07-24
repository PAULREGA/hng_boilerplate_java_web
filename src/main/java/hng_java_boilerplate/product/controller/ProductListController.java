package hng_java_boilerplate.product.controller;

import hng_java_boilerplate.product.dto.CustomResponseDTO;
import hng_java_boilerplate.product.dto.PaginationDTO;
import hng_java_boilerplate.product.dto.ProductListDTO;
import hng_java_boilerplate.product.entity.Product;
import hng_java_boilerplate.product.service.ProductListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

        List<ProductListDTO> productDTOs = productPage.getContent().stream()
            .map(product -> new ProductDTO(product.getName(), (float) product.getPrice(), product.getCategory())) // Explicit type cast
            .collect(Collectors.toList());

        PaginationDTO paginationDTO = new PaginationDTO(
            (int) productPage.getTotalElements(),
            productPage.getTotalPages(),
            productPage.getNumber() + 1
        );

        CustomResponseDTO response = new CustomResponseDTO();
        response.setSuccess(true);
        response.setMessage("Product retrieved successfully");
        response.setProducts(productDTOs);
        response.setPagination(paginationDTO);
        response.setStatusCode(200);

        return response;
    }
}