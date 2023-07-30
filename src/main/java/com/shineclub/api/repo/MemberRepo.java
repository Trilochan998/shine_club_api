package com.shineclub.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shineclub.api.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Long>{

	Member findByMobileNo(String mobileNo);

}
