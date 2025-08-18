package com.Bot5wProj.SpringBotTGmy.service;

import com.Bot5wProj.SpringBotTGmy.model.Product;
import com.Bot5wProj.SpringBotTGmy.model.User;
import com.Bot5wProj.SpringBotTGmy.repository.ProductRepository;
import com.Bot5wProj.SpringBotTGmy.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Product fetchAndSaveProduct(Long chatId, String article) {
        try {
            String url = "https://www.wildberries.ru/catalog/" + article + "/detail.aspx";
            System.out.println("Запрос WB URL: " + url);

            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10_000)
                    .get();

            Element script = doc.selectFirst("script:containsData(window.__REDUX_STATE__)");
            if (script == null) {
                throw new RuntimeException("Не удалось найти данные о товаре на странице");
            }

            String jsonText = script.html();
            int start = jsonText.indexOf("{");
            int end = jsonText.lastIndexOf("}");
            String json = jsonText.substring(start, end + 1);

            JsonNode root = objectMapper.readTree(json);

            // Ищем первый объект с данными о товаре
            JsonNode productsNode = root.at("/cards/data/products");
            JsonNode productData = null;
            if (productsNode.isObject()) {
                Iterator<JsonNode> iter = productsNode.elements();
                if (iter.hasNext()) productData = iter.next();
            } else if (productsNode.isArray() && productsNode.size() > 0) {
                productData = productsNode.get(0);
            }

            if (productData == null) {
                throw new RuntimeException("Не удалось найти товар в JSON");
            }

            String title = productData.path("name").asText();
            double price = productData.path("salePriceU").asDouble() / 100.0;

            User user = userRepository.findById(chatId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Product product = Product.builder()
                    .article(article)
                    .title(title)
                    .price(price)
                    .url(url)
                    .user(user)
                    .build();

            return productRepository.save(product);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении данных о товаре: " + e.getMessage(), e);
        }
    }
}
