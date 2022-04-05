package com.example.prospectron.service;

import com.example.prospectron.repository.ProductRepository;
import com.example.prospectron.repository.TransactionRepository;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) //add to remove AutoClosable
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    private  ProductRepository productRepository;
    private TransactionService underTest;

    @BeforeEach
    void setUp(){
        underTest = new TransactionService(transactionRepository, productRepository);
    }

    @Test
    @Disabled
    void testGetTransactions() {
        //when
        underTest.getTransactions();

        //then
        //test that transactionRepository.findAll() was invoked
        verify(transactionRepository).findAll();
    }

    @Test
    @Disabled
    void deleteTransaction() {
      /*  //given
        String orderRef = "testToDelete";
        Transaction t = transactionRepository.findTransactionByOrderRef(orderRef).get();
        System.out.println(t.getId());

        //when
        underTest.deleteTransaction(t.getId());

        //then
        assertThat(transactionRepository.findTransactionByOrderRef(orderRef).isPresent()).isFalse();*/
    }

    @Test
    @Disabled
    void testStoreTransactionDetails() throws IOException, JSONException {

       /* //given
        JSONArray products = new JSONArray();
        String orderRef = "unitTestRef";
        List<Map<String, Object>> transactionList = new ArrayList<>();
        URL url = new URL("http://localhost:8081/transaction/932");
        Map<String, Object> transMap = new ObjectMapper().readValue(url, HashMap.class);

        Transaction tempTrans = new Transaction(
                Timestamp.valueOf(transMap.get("date").toString()),
                (double) transMap.get("saleAmount"),
                transMap.get("orderRef").toString(),
                products
        );

        transactionList.add(transMap);
        //when
        underTest.storeTransactionDetails(transactionList);

        //then
        ArgumentCaptor<Transaction> transactionArgumentCaptor =
                ArgumentCaptor.forClass(Transaction.class);

        verify(transactionRepository).save(transactionArgumentCaptor.capture());

        Transaction capturedTransaction = transactionArgumentCaptor.getValue();

        assertThat(capturedTransaction).isEqualTo(tempTrans);*/
    }
}