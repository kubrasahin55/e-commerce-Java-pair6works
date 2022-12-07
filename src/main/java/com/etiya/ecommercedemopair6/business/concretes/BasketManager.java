package com.etiya.ecommercedemopair6.business.concretes;


import com.etiya.ecommercedemopair6.business.abstracts.BasketService;
import com.etiya.ecommercedemopair6.business.abstracts.CustomerService;
import com.etiya.ecommercedemopair6.business.dto.request.concretes.Basket.CreateBasketRequest;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.basket.CreateBasketResponse;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.basket.GetAllBasketResponse;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.basket.GetBasketResponse;
import com.etiya.ecommercedemopair6.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemopair6.entities.concretes.Basket;
import com.etiya.ecommercedemopair6.repository.abstracts.BasketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BasketManager implements BasketService {
    private BasketRepository basketRepository;
    private CustomerService customerService;
    private ModelMapperService modelMapperService;


    @Override
    public GetBasketResponse getById(int id) {

        Basket basket=basketRepository.findById(id).orElseThrow();
        GetBasketResponse response=modelMapperService.forResponse().map(basket,GetBasketResponse.class);
        return response;

    }

    @Override
    public List<GetAllBasketResponse> getAllBasket() {
        List<Basket>  basketResponse =basketRepository.findAll();
        List<GetAllBasketResponse> responses=basketResponse.stream()
                .map(basket -> modelMapperService.forResponse().map(basket,GetAllBasketResponse.class))
                .collect(Collectors.toList());
        return responses;
    }

    @Override
    public CreateBasketResponse createBasket(CreateBasketRequest createBasketRequest) {
        //***********************************ManuelMapper******************************************

        //Customer customer = customerService.getById(createBasketRequest.getCustomerId());
        // Basket basket = new Basket();
        //basket.setCustomer(customer);
        // basketRepository.save(basket);
        //CreateBasketResponse response = new CreateBasketResponse(basket.getCustomer().getCustomerId());
        //return response;

        Basket basket = modelMapperService.forRequest().map(createBasketRequest, Basket.class);
        Basket savedBasket = basketRepository.save(basket);
        CreateBasketResponse response = modelMapperService.forResponse().map(savedBasket, CreateBasketResponse.class);
        return response;
    }

}