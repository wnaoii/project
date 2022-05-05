package com.icbc.sh.sukura.service.ipml;

import com.icbc.sh.sukura.bo.Product;
import com.icbc.sh.sukura.config.EnvConfig;
import com.icbc.sh.sukura.entity.OrderEntity;
import com.icbc.sh.sukura.entity.ProductEntity;
import com.icbc.sh.sukura.entity.StockProductEntity;
import com.icbc.sh.sukura.repository.ImageRepository;
import com.icbc.sh.sukura.repository.ProductRepository;
import com.icbc.sh.sukura.repository.StockProductRepository;
import com.icbc.sh.sukura.service.DrinkService;
import com.icbc.sh.sukura.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockProductRepository stockProductRepository;
    @Autowired
    private EnvConfig envConfig;
    @Autowired
    private ImageRepository imageRepository;


    @Override
    public List<Product> getProductList(String pType, Pageable pageable) {
//        PageRequest pageRequest = PageRequest.of(page,size);
        List<ProductEntity> productEntities = null;
        Page pages =productRepository.findAll(new Specification<ProductEntity>() {
            List<Predicate> predicates = new ArrayList<>();
            @Override
            public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (!StringUtils.isEmpty(pType)) {
                    predicates.add(criteriaBuilder.equal(root.get("pType"), pType));
                }
                predicates.add(criteriaBuilder.equal(root.get("deleted"), "1"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);

        productEntities = pages.getContent();

        List<Product> products = new ArrayList<>();
        for(ProductEntity p : productEntities) {
            Product bo = Product.builder()
                    .productId(p.getProductId())
                    .createTime(p.getCreateTime())
                    .pDesc(p.getPDesc())
                    .price(p.getPrice())
                    .imageId(p.getImageId())
                    .pTitle(p.getPTitle())
                    .pType(p.getPType())
                    .contacts(p.getContacts())
                    .contactsNo(p.getContactsNo())
                    .startDate(p.getStartDate())
                    .soldStatus(p.getSoldStatus())
                    .build();
            products.add(bo);
        }
        return products;
    }

    @Transactional
    @Override
    public Product insertProduct(Product product) {
        ProductEntity productEntity = this.bo2Entity(product);
        productEntity.setSoldStatus("0");
        productEntity.setDeleted("1");
        StockProductEntity stockProductEntity = StockProductEntity.builder()
                .pTitle(product.getPTitle())
                .stock(product.getStockNum())
                .productId(product.getProductId())
                .build();
        stockProductRepository.save(stockProductEntity);
        productRepository.save(productEntity);
        return product;
    }

    @Override
    public Product getProduct(String productId) {
        ProductEntity entity = productRepository.findOne(productId);
//        ProductEntity entity = productRepository.findByProductId(productId).get(0);
        return this.entity2Bo(entity);
    }

    @Override
    @Transactional
    public void deleteProduct(String productId) {
        String imageId = productRepository.findByProductId(productId).get(0).getImageId();
        ProductEntity product = productRepository.findOne(productId);
        product.setDeleted("0");
        productRepository.save(product);
        stockProductRepository.delete(productId);
//        imageRepository.delete(imageId);
//        productRepository.delete(productId);
    }

    private ProductEntity bo2Entity(Product bo) {
        return ProductEntity.builder()
                .createTime(new Date())
                .pDesc(bo.getPDesc())
                .price(bo.getPrice())
                .productId(bo.getProductId())
                .pTitle(bo.getPTitle())
                .pType(bo.getPType())
                .imageId(bo.getImageId())
                .addable(bo.getAddable())
                .addableList(bo.getAddableList())
                .contacts(bo.getContacts())
                .method(bo.getMethod())
                .contactsNo(bo.getContactsNo())
                .hot(bo.getHot())
                .startDate(bo.getStartDate())
                .build();
    }
    private Product entity2Bo(ProductEntity p) {
        return Product.builder()
                .productId(p.getProductId())
                .createTime(p.getCreateTime())
                .pDesc(p.getPDesc())
                .price(p.getPrice())
                .imageId(p.getImageId())
                .pTitle(p.getPTitle())
                .pType(p.getPType())
                .createTime(p.getCreateTime())
                .startDate(p.getStartDate())
                .contacts(p.getContacts())
                .contactsNo(p.getContactsNo())
                .hot(p.getHot())
                .addableList(p.getAddableList())
                .addable(p.getAddable())
                .build();
    }
}