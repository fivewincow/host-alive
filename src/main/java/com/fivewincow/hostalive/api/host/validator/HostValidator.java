package com.fivewincow.hostalive.api.host.validator;

import com.fivewincow.hostalive.domain.host.entity.Host;
import com.fivewincow.hostalive.domain.host.repository.HostRepository;
import com.fivewincow.hostalive.domain.host.service.HostService;
import com.fivewincow.hostalive.global.error.exception.BusinessException;
import com.fivewincow.hostalive.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HostValidator {

    private final HostRepository hostRepository;

    public void validateDuplicateHost(String hostName, InetAddress hostIp) {
        Optional<Host> optionalHostName = hostRepository.findByHostName(hostName);
        if (optionalHostName.isPresent()){
            throw new BusinessException(ErrorCode.ALREADY_CREATED_HOST_NAME);
        }
        Optional<Host> optionalHostIp = hostRepository.findByHostIp(hostIp);
        if (optionalHostIp.isPresent()){
            throw new BusinessException(ErrorCode.ALREADY_CREATED_HOST_IP);
        }
    }
}
