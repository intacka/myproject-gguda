package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.ProductDto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class ProductResult {
    private ProductDto productDto;
    private List<MultipartFile> files;

    public ProductResult(ProductDto productDto, List<MultipartFile> files) {
        this.productDto = productDto;
        this.files = files;
    }
}
