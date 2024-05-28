package com.example.demo.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.bans;

public interface banRepository extends JpaRepository<bans, Integer>{

}
