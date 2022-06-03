package com.fivewincow.hostalive.api.host.controller;

import com.fivewincow.hostalive.api.host.dto.HostAliveDto;
import com.fivewincow.hostalive.api.host.dto.HostDto;
import com.fivewincow.hostalive.api.host.dto.UpdateHostDto;
import com.fivewincow.hostalive.api.host.service.ApiHostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/host")
public class HostController {

    private final ApiHostService apiHostService;

    @PostMapping
    public ResponseEntity<String> saveHost(@RequestBody HostDto.Request hostRequestDto) {

        String hostDto = apiHostService.saveHost(hostRequestDto);

        return ResponseEntity.ok(hostDto);
    }

    @GetMapping("/{hostId}")
    public ResponseEntity<HostDto.Response> getHost(@PathVariable Long hostId) {
        HostDto.Response hostResponseDto = apiHostService.getHost(hostId);

        return ResponseEntity.ok(hostResponseDto);
    }

    @PatchMapping("/{hostId}")
    public ResponseEntity<UpdateHostDto.Response> updateHost(@PathVariable Long hostId
            ,@RequestBody UpdateHostDto.Request updateRequestHostDto) {

        UpdateHostDto.Response updateResponseHostDto = apiHostService.updateHost(hostId, updateRequestHostDto);

        return ResponseEntity.ok(updateResponseHostDto);

    }

    // 특정 호스트의 현재 Alive 상태 조회
    @GetMapping("/alive/now/{hostId}")
    public ResponseEntity<HostAliveDto.Now> getHostAliveNow(@PathVariable Long hostId) throws IOException {
        HostAliveDto.Now hostAliveNow = apiHostService.getHostAliveNow(hostId);

        return ResponseEntity.ok(hostAliveNow);
    }

    @GetMapping("/alive/{hostId}")
    public ResponseEntity<HostAliveDto> getHostAliveAll(@PathVariable Long hostId) throws IOException {
        HostAliveDto hostAlive = apiHostService.getHostAlive(hostId);

        return ResponseEntity.ok(hostAlive);
    }

    @GetMapping("/alive")
    public ResponseEntity<List<HostAliveDto>> getHostAliveAll() throws IOException {
        List<HostAliveDto> hostAliveAll = apiHostService.getHostAliveAll();

        return ResponseEntity.ok(hostAliveAll);
    }


}
