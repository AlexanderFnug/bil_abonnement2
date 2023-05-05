package com.example.bilabonnement.Repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Repo {

    @Autowired
    JdbcTemplate db;

    public Repo(){
        //Empty constructor
    }
}
