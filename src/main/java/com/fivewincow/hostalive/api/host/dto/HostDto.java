package com.fivewincow.hostalive.api.host.dto;

import com.fivewincow.hostalive.domain.host.entity.Host;
import lombok.Builder;
import lombok.Getter;

import java.net.InetAddress;
import java.time.LocalDateTime;

@Getter
public class HostDto {
    @Getter
    public static class Request {
        private InetAddress hostIp;

        private String hostName;

        public Host toEntity() {
            return Host.builder()
                    .hostName(hostName)
                    .hostIp(hostIp)
                    .build();
        }

    }

    @Getter
    public static class Response {
        private InetAddress hostIp;

        private String hostName;

        private LocalDateTime createTime;

        private LocalDateTime updateTime;

        @Builder
        public Response(InetAddress hostIp, String hostName, LocalDateTime createTime, LocalDateTime updateTime) {
            this.hostIp = hostIp;
            this.hostName = hostName;
            this.createTime = createTime;
            this.updateTime = updateTime;
        }
    }

    @Getter
    public static class ResponseAll {
        private InetAddress hostIp;

        private String hostName;

        private LocalDateTime createTime;

        private LocalDateTime updateTime;

        @Builder
        public ResponseAll(InetAddress hostIp, String hostName, LocalDateTime createTime, LocalDateTime updateTime) {
            this.hostIp = hostIp;
            this.hostName = hostName;
            this.createTime = createTime;
            this.updateTime = updateTime;
        }
    }
}
