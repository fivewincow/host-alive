package com.fivewincow.hostalive.domain.host.repository;

import com.fivewincow.hostalive.domain.host.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {

}
