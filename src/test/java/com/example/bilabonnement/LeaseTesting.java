package com.example.bilabonnement;

import com.example.bilabonnement.Model.Lease;
import com.example.bilabonnement.Repo.Repository;
import com.example.bilabonnement.Service.Service;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class LeaseTesting {

    @MockBean
    Repository repo;

    @Autowired
    Service service;

    @Test
    public void testLeaseTotalPrice(){
        //Prepare test data
        Lease lease1 = new Lease();
        Lease lease2 = new Lease();
        Lease lease3 = new Lease();
        lease1.setPrice(10);
        lease2.setPrice(7);
        lease3.setPrice(123);
        lease1.setDateEnd("2300-12-12"); //Counts as active
        lease2.setDateEnd("2300-12-12"); //Counts as active
        lease3.setDateEnd("2000-12-12"); //Counts as INACTIVE and should get filtered by getLeasedTotal()
        List<Lease> testList = new ArrayList<>();
        testList.add(lease1);
        testList.add(lease2);
        testList.add(lease3);

        //Setup
        Mockito.when(repo.fetchAllLeases()).thenReturn(testList);
        Double testTotal = service.getLeasedTotal();
        Double expectedTotal = (Double) lease1.getPrice() + (Double) lease2.getPrice();

        //Check result
        assertEquals(expectedTotal,testTotal);
    }
}
