package dev.darshana.productservice.thirdPartClients.productService.fakseStore;

import dev.darshana.productservice.dtos.GenericProductDto;
import dev.darshana.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;

    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductsApiPath;
    private String specificProductRequestUrl;
    private String productRequestBaseUrl;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,
                                         @Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.productRequestBaseUrl  = fakeStoreApiUrl + fakeStoreProductsApiPath;
        this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id "+id+" does not exist");
        }
        //GenericProductDto product = convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestBaseUrl, product, FakeStoreProductDto.class);
        return response.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);
        return Arrays.stream(response.getBody()).toList();
    }

    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return response.getBody();
    }

    public FakeStoreProductDto updateProductById(GenericProductDto product, Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);
        return response.getBody();
    }
}
