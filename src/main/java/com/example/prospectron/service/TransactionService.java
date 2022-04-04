package com.example.prospectron.service;

import com.example.prospectron.exception.ApiRequestException;
import com.example.prospectron.model.Product;
import com.example.prospectron.model.Transaction;
import com.example.prospectron.repository.ProductRepository;
import com.example.prospectron.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;



    @Autowired
    public TransactionService(TransactionRepository transactionRepository, ProductRepository productRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
    }


    public Iterable<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);

    }

    public String deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
        return "Success";
    }

    @Transactional
    public List<Transaction> storeTransactionDetails(List<java.util.Map<String, Object>> transactionList) throws JSONException, JsonProcessingException {

        List<Transaction> transList = new ArrayList<>();
        //store transactions
        for (Object trans : transactionList) {
            JSONObject transaction = new JSONObject(new ObjectMapper().writeValueAsString(trans));
            //about creating new entity instead of using bean https://stackoverflow.com/questions/52818595/create-new-entity-object-in-spring-boot
            Transaction t = new Transaction(Timestamp.valueOf(transaction.get("date").toString()),
                    transaction.get("orderRef").toString()
            );

            Optional<Transaction> transactionOptional = transactionRepository.findTransactionByOrderRef(t.getOrderRef());
            if (transactionOptional.isPresent()) {
                throw new ApiRequestException("Transaction already exists!");
            }

            try{
                transactionRepository.save(t);
            } catch (ApiRequestException ex){
                    throw new ApiRequestException("Failed to write on database", ex);
            }


            //store products
            JSONArray productData = (JSONArray) transaction.get("products");
            for (int i = 0; i < productData.length(); i++) {
                JSONObject product = new JSONObject(productData.get(i).toString());

                Product p = new Product(Double.parseDouble(product.get("price").toString()),
                        product.getString(("name")),
                        t.getId()
                );
                productRepository.save(p);
            }

            transList.add(t);
            t.setSaleAmount(productRepository.findByTransactionId(t.getId()).
                    stream().mapToDouble(o -> o.getPrice()).sum());
        }
        return transList;
    }
}