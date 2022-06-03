package com.fivewincow.hostalive.api.host.dto;

import lombok.Builder;
import lombok.Getter;

import java.net.InetAddress;
import java.time.LocalDateTime;

@Getter
public class HostAliveDto {

    private boolean isAlive;

    private LocalDateTime lastAliveTime;

    private String hostName;

    private InetAddress hostIp;

    @Builder
    public HostAliveDto(boolean isAlive, LocalDateTime lastAliveTime, String hostName, InetAddress hostIp) {
        this.isAlive = isAlive;
        this.lastAliveTime = lastAliveTime;
        this.hostName = hostName;
        this.hostIp = hostIp;
    }

    @Getter
    public static class Now {
        private boolean isAlive;

        private String hostName;

        private InetAddress hostIp;

        @Builder
        public Now(boolean isAlive, String hostName, InetAddress hostIp) {
            this.isAlive = isAlive;
            this.hostName = hostName;
            this.hostIp = hostIp;
        }
    }
}
