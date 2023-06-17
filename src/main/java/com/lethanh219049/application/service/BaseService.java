package com.lethanh219049.application.service;

import com.lethanh219049.application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseService {
    @Autowired
    BrandRepository brandRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    StatisticRepository statisticRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskBatchRepository taskBatchRepository;

    @Autowired
    TaskBatchDoneRepository taskBatchDoneRepository;

    @Autowired
    PasswordEncoder encoder;
}
