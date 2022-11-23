package com.example.dbtransaction.controller;

import com.example.common.util.R;
import com.example.common.entity.Product;
import com.example.dbtransaction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 11:57
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public Product info(@PathVariable long id) {
        return productService.findById(id);
    }

    @GetMapping
    public R list(){
        List<Product> products = productService.queryList();

        return R.ok(products);
    }
}
