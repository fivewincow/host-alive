package com.fivewincow.hostalive.domain.host.repository;

import com.fivewincow.hostalive.domain.host.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.InetAddress;
import java.util.Optional;

public interface HostRepository extends JpaRepository<Host, Long> {
    Optional<Host> findByHostName(String hostName);
    Optional<Host> findByHostIp(InetAddress hostIp);
}
