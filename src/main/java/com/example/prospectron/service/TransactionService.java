package com.example.prospectron.service;

import com.example.prospectron.exception.ApiRequestException;
import com.example.prospectron.model.Product;
import com.example.prospectron.model.Transaction;
import com.example.prospectron.repository.ProductRepository;
import com.example.prospectron.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

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

    @Transactional  //enables to automatically update the entity on the db (enables updates in persistence)
    public List<Transaction> storeTransactionDetails(List<Transaction> transactionList) {
        //save transaction
        for (Transaction t : transactionList) {
            //check if transaction exists
            Optional<Transaction> transactionOptional = transactionRepository.findTransactionByOrderRef(t.getOrderRef());
            if (transactionOptional.isPresent()){
                throw new ApiRequestException("Transaction already exists!");
            }
            //save
            try{
                transactionRepository.save(t);
            } catch (ApiRequestException ex){
                throw new ApiRequestException("Failed to write on database", ex);
            }

            //calculate total sale amount
            double sumOfProductPrice = t.getProducts().stream().mapToDouble(o -> o.getPrice()).sum();
            t.setSaleAmount((double)Math.round(sumOfProductPrice * 100000d) / 100000d);

            //save products
            for (Product p : t.getProducts()) {
                p.settransaction(t);
                productRepository.save(p);
            }
        }
        return transactionList;
    }
}