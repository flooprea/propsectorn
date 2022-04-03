package com.example.prospectron.service;

import com.example.prospectron.model.Product;
import com.example.prospectron.model.Transaction;
import com.example.prospectron.repository.ProductRepository;
import com.example.prospectron.repository.TransactionRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    public Iterable<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id){
        return transactionRepository.findById(id);
    }

    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }

    public void storeTransactionDetails(JSONObject transactionData) throws  JSONException {

        //save transaction
        //about creating new entity instead of using bean https://stackoverflow.com/questions/52818595/create-new-entity-object-in-spring-boot
        Transaction t = new Transaction(Timestamp.valueOf(transactionData.get("date").toString()),
                (double) transactionData.get("saleAmount"),
                transactionData.get("orderRef").toString(),
                "NULL"
        );

        Optional<Transaction> transactionOptional = transactionRepository.findTransactionByOrderRef(t.getOrderRef());
        if(transactionOptional.isPresent()){
            throw new IllegalStateException("Transaction already exists!");
        }
        transactionRepository.save(t);

        //save transaction products
        JSONArray productData = (JSONArray) transactionData.get("products");
        for(int i=0; i<productData.length(); i++){
            JSONObject product = new JSONObject(productData.get(i).toString());

            Product p = new Product(Double.parseDouble(product.get("price").toString()),
                                    product.getString(("name")),
                                    t.getId()
                        );
            productRepository.save(p);
        }
    }
}
