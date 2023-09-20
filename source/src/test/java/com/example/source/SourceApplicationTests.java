package com.example.source;

import com.example.source.entity.*;
import com.example.source.repository.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class SourceApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CommentRepository commentRepository;

    private List<String> roles = Arrays.asList("customer, admin");
    private List<String> categories = Arrays.asList("Web Development",
            "Mobile App Development",
            "Data Science",
            "Machine Learning",
            "DevOps");
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    void add_categories() {

        for (String c : categories) {
            Category cate = new Category();
            cate.setName(c);
            categoryRepository.save(cate);
        }
    }

    @Test
    void add_roles() {

        for (String r : roles) {
            Role role = new Role();
            role.setName(r);
            roleRepository.save(role);
        }
    }

    @Test
    void add_users() {
        Faker faker = new Faker();

        // Thêm 30 users với các roles ngẫu nhiên
        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setName(faker.name().fullName());
            user.setEmail(faker.internet().emailAddress());
            user.setAvatar(faker.internet().avatar());
            user.setPassword(faker.internet().password());

            // Chọn ngẫu nhiên 1-3 roles cho mỗi user
            List<Role> randomRoles = new ArrayList<>();
            int numRoles = faker.random().nextInt(1, 4);
            for (int j = 0; j < numRoles; j++) {
                roleRepository.findById(faker.random().nextInt(0, roles.size())).ifPresent(randomRoles::add);
            }
            user.setRoles(randomRoles);

            userRepository.save(user);
        }
    }


    @Test
    void add_blog() {
        Faker faker = new Faker();

        for (int i = 0; i < 30; i++) {
            Blog blog = new Blog();
            blog.setTitle(faker.lorem().sentence());
            blog.setSlug(faker.internet().slug());
            blog.setDescription(faker.lorem().sentence());
//            blog.setContent(faker.lorem().paragraph());
            blog.setContent(faker.lorem().paragraphs(3).stream().reduce((s1, s2) -> s1 + "\n" + s2).orElse(""));
            blog.setThumbnail(faker.internet().avatar());
            blog.setPublished_at(LocalDateTime.now());
            blog.setStatus(BlogStatus.PUBLISHED);


            // Chọn một user ngẫu nhiên
            List<User> users = userRepository.findAll();
            User randomUser = users.get(faker.random().nextInt(0, users.size()));
            blog.setUser(randomUser);

            // Chọn một số lượng ngẫu nhiên các danh mục
            int numCategories = faker.random().nextInt(1, 4); // Chọn từ 1 đến 3 danh mục
            List<Category> randomCategories = new ArrayList<>();
            for (int j = 0; j < numCategories; j++) {
                categoryRepository.findById(faker.random().nextInt(0, categories.size())).ifPresent(randomCategories::add);
            }
            blog.setCategories(randomCategories);

            blogRepository.save(blog);
        }
    }

@Test
    void add_comments() {
        Faker faker = new Faker();

        List<User> users = userRepository.findAll();
        List<Blog> blogs = blogRepository.findAll();

        for (int i = 0; i < 30; i++) {
            Comment comment = new Comment();
            comment.setContent(faker.lorem().sentence());

            // Chọn một user ngẫu nhiên
            User randomUser = users.get(faker.random().nextInt(0, users.size()));
            comment.setUser(randomUser);

            // Chọn một blog ngẫu nhiên
            Blog randomBlog = blogs.get(faker.random().nextInt(0, blogs.size()));
            comment.setBlog(randomBlog);

            commentRepository.save(comment);
        }
    }


}
