package com.dto.way.admin.domain.repository;

import com.dto.way.admin.domain.entity.Member;
import com.dto.way.admin.domain.entity.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    List<Member> findByMemberStatus(MemberStatus status);
}
