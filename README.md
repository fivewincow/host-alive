# Host-Alive
|Method|URI|Description|parameter|
|---|---|---|---|
|Post|/api/host|host 등록 api|hostIp, hostName|
|Get|/api/host/{hostId}|host 조회 api|hostId|
|Patch|/api/host/{hostId}|host 수정 api|hostId|
|Get|/api/host/now/{hostId}|특정 host의 현재 Alive 상태 조회 api|hostId|
|Get|/api/host/alive/{hostId}|host의 모니터링 결과 조회 api|hostId|
|Get|/api/host/alive|전체 host의 모니터링 결과 조회 api||