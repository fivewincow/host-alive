package com.fivewincow.hostalive.domain.host.service;

import com.fivewincow.hostalive.api.host.validator.HostValidator;
import com.fivewincow.hostalive.domain.host.entity.Host;
import com.fivewincow.hostalive.domain.host.repository.HostRepository;
import com.fivewincow.hostalive.global.error.exception.EntityNotFoundException;
import com.fivewincow.hostalive.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HostService {

    private final HostRepository hostRepository;
    private final HostValidator hostValidator;

    @Transactional
    public Host saveHost(Host host) {
        validateRegisterHost(host);
        hostRepository.save(host);

        return host;
    }

    public void validateRegisterHost(Host host) {
        hostValidator.validateDuplicateHost(host.getHostName(), host.getHostIp());
    }

    @Transactional
    public Host updateHost(Long hostId, Host updateHost) {
        Host savedHost = getHostDetail(hostId);
        savedHost.updateHost(updateHost);
        return savedHost;
    }

    public Host getHostDetail(Long hostId) {
        return hostRepository.findById(hostId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.HOST_NOT_EXISTS));
    }

    public List<Host> getHostAll() {
        return hostRepository.findAll();
    }
}
