package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.ProductResponseDto;
import lombok.Data;

import java.util.List;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class MainResult<T> {
    private List<ProductResponseDto> popular;
    private List<ProductResponseDto> macbook;
    private List<ProductResponseDto> ipad;
    private List<ProductResponseDto> samsung_notebook;
    private List<ProductResponseDto> samsung_pc;
    private List<ProductResponseDto> benQ;

    public MainResult(List<ProductResponseDto> popular, List<ProductResponseDto> macbook, List<ProductResponseDto> ipad, List<ProductResponseDto> samsung_notebook, List<ProductResponseDto> samsung_pc, List<ProductResponseDto> benQ) {
        this.popular = popular;
        this.macbook = macbook;
        this.ipad = ipad;
        this.samsung_notebook = samsung_notebook;
        this.samsung_pc = samsung_pc;
        this.benQ = benQ;
    }

}