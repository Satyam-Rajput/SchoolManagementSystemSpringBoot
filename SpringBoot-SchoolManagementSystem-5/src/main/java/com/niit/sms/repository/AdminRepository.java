package com.niit.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niit.sms.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
