package com.example.prospectron.repository;

import com.example.prospectron.model.Transaction;
import org.json.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void testFindTransactionByOrderRef() {
 /*       //behavior driven test model
        //given
        JSONArray products = new JSONArray();
        String orderRef = "unitTestRef";
        Transaction transaction = new Transaction(
                new Timestamp(System.currentTimeMillis()),
                15D,
                orderRef,
                products
        );
        underTest.save(transaction);

        //when
        boolean exists = underTest.findTransactionByOrderRef(orderRef).isPresent();

        //then
        assertThat(exists).isTrue();*/
    }

    @Test
    void testFindTransactionByOrderRefWhenOrderRefNotPresent() {

        //given
        String orderRef = "unitTestRef";

        //when
        boolean exists = underTest.findTransactionByOrderRef(orderRef).isPresent();

        //then
        assertThat(exists).isFalse();
    }
}