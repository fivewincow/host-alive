package com.fivewincow.hostalive.api.host.dto;

import com.fivewincow.hostalive.domain.host.entity.Host;
import lombok.Builder;
import lombok.Getter;

import java.net.InetAddress;

public class UpdateHostDto {

    @Getter
    @Builder
    public static class Request {
        private Long hostId;

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
    @Builder
    public static class Response {
        private Long hostId;

        private InetAddress hostIp;

        private String hostName;

        public static Response createResponse(Host host) {
            return Response.builder()
                    .hostId(host.getHostId())
                    .hostIp(host.getHostIp())
                    .hostName(host.getHostName())
                    .build();
        }
    }
}
