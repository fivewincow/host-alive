package com.fivewincow.hostalive.domain.host.entity;

import com.fivewincow.hostalive.domain.base.BaseTimeEntity;
import com.fivewincow.hostalive.global.converter.InetAddressConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Host extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hostId;

    @Column(length = 30, nullable = false)
    private String hostName;

    @Column(length = 30, nullable = false)
    @Convert(converter = InetAddressConverter.class)
    private InetAddress hostIp;

    private LocalDateTime lastAliveTime;

    @Builder
    public Host(Long hostId, String hostName, InetAddress hostIp) {
        this.hostId = hostId;
        this.hostName = hostName;
        this.hostIp = hostIp;
    }

    public static Host createHost(Host host) {
        return Host.builder()
                .hostName(host.getHostName())
                .hostIp(host.getHostIp())
                .build();
    }

    public void updateHost(Host host) {
        this.hostIp = host.getHostIp();
        this.hostName = host.getHostName();
    }

    public void updateAliveTime() {
        this.lastAliveTime = LocalDateTime.now();
    }
}
