package com.fivewincow.hostalive.domain.host.entity;

import com.fivewincow.hostalive.domain.base.BaseTimeEntity;
import com.fivewincow.hostalive.domain.host.constant.HostStatus;
import com.fivewincow.hostalive.global.converter.InetAddressConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.InetAddress;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private HostStatus hostStatus;
}
