package com.fivewincow.hostalive.api.host.service;

import com.fivewincow.hostalive.api.host.dto.HostAliveDto;
import com.fivewincow.hostalive.api.host.dto.HostDto;
import com.fivewincow.hostalive.api.host.dto.UpdateHostDto;
import com.fivewincow.hostalive.domain.host.entity.Host;
import com.fivewincow.hostalive.domain.host.service.HostService;
import com.fivewincow.hostalive.global.error.exception.BusinessException;
import com.fivewincow.hostalive.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiHostService {

    private final HostService hostService;

    @Transactional
    public String saveHost(HostDto.Request hostRequestDto) {
        String message = null;
        int size = hostService.getHostAll().size();
        if (size >= 100) {
            throw new BusinessException(ErrorCode.HOST_MAX_EXISTS);
        }

        Host host = hostRequestDto.toEntity();
        Host saveHost = Host.createHost(host);

        saveHost = hostService.saveHost(saveHost);

        if (saveHost != null) {
            message = "host generated";
        }
        return message;
    }

    public HostDto.Response getHost(Long hostId) {
        Host host = hostService.getHostDetail(hostId);

        HostDto.Response hostResponseDto = HostDto.Response.builder()
                .hostIp(host.getHostIp())
                .hostName(host.getHostName())
                .createTime(host.getCreateTime())
                .updateTime(host.getUpdateTime())
                .build();

        return hostResponseDto;
    }

    @Transactional
    public UpdateHostDto.Response updateHost(Long hostId, UpdateHostDto.Request updateRequestHostDto) {
        Host host = hostService.getHostDetail(hostId);

        Host updateHost = updateRequestHostDto.toEntity();

        updateHost = hostService.updateHost(hostId, updateHost);

        UpdateHostDto.Response updateResponseHostDto = UpdateHostDto.Response.createResponse(updateHost);

        return updateResponseHostDto;
    }

    public HostAliveDto.Now getHostAliveNow(Long hostId) throws IOException {
        Host hostDetail = hostService.getHostDetail(hostId);
        boolean reachable = hostDetail.getHostIp().isReachable(1000);

        if (reachable) {
            return HostAliveDto.Now.builder()
                    .isAlive(true)
                    .hostName(hostDetail.getHostName())
                    .hostIp(hostDetail.getHostIp())
                    .build();

        } else {
            return HostAliveDto.Now.builder()
                    .isAlive(false)
                    .hostName(hostDetail.getHostName())
                    .hostIp(hostDetail.getHostIp())
                    .build();
        }
    }

    @Transactional
    public HostAliveDto getHostAlive(Long hostId) throws IOException {
        Host hostDetail = hostService.getHostDetail(hostId);
            boolean reachable = hostDetail.getHostIp().isReachable(1000);
            if (reachable) {
                hostDetail.updateAliveTime();
                return HostAliveDto.builder()
                        .isAlive(true)
                        .lastAliveTime(LocalDateTime.now())
                        .hostName(hostDetail.getHostName())
                        .hostIp(hostDetail.getHostIp())
                        .build();
            } else {
                return HostAliveDto.builder()
                        .isAlive(false)
                        .hostName(hostDetail.getHostName())
                        .hostIp(hostDetail.getHostIp())
                        .build();
            }
    }
    @Transactional
    public List<HostAliveDto> getHostAliveAll() throws IOException {
        List<Host> hostAll = hostService.getHostAll();
        List<HostAliveDto> hostAliveDtos = new ArrayList<>();
        for (Host host : hostAll) {
            boolean reachable = host.getHostIp().isReachable(1000);
            if (reachable) {
                host.updateAliveTime();
                HostAliveDto hostAliveDto = HostAliveDto.builder()
                        .isAlive(true)
                        .lastAliveTime(LocalDateTime.now())
                        .hostName(host.getHostName())
                        .hostIp(host.getHostIp())
                        .build();
                hostAliveDtos.add(hostAliveDto);
            } else {
                HostAliveDto hostAliveDto = HostAliveDto.builder()
                        .isAlive(false)
                        .hostName(host.getHostName())
                        .hostIp(host.getHostIp())
                        .build();
                hostAliveDtos.add(hostAliveDto);
            }
        }
        return hostAliveDtos;
    }
}
