package ru.netology.repository;

import lombok.NoArgsConstructor;
import ru.netology.exception.NotFoundException;
import ru.netology.product.Product;

@NoArgsConstructor
public class Repository {
    private Product[] products = new Product[0];

    public void save(Product product) {
        int length = products.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(products, 0, tmp, 0, products.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    public Product[] findById(int id) {
        Product[] result = new Product[0];
        for (Product product : products) {
            if (product.getId() == id) {
                int length = result.length + 1;
                Product[] tmp = new Product[length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = product;
                result = tmp;
            }
        }
        if (result.length == 0) {
            return null;
        }
        return result;
    }

    public Product[] removeById(int id) throws NotFoundException {
        if (findById(id) == null) {
            throw new NotFoundException("Product with id: " + id + " is not found!");
        }
        int length = products.length - 1;
        Product[] tmp = new Product[length];
        int index = 0;
        for (Product product : products) {
            if(product.getId() != id) {
                tmp[index] = product;
                index++;
            }
            products = tmp;
        }
        return products;
    }
}
