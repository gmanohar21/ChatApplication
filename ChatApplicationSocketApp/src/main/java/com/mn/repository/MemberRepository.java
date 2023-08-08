package com.mn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mn.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
