# Host-Alive

---

### REST API
|Method|URI|Description|parameter|
|---|---|---|---|
|Post|/api/host|host 등록 api|hostIp, hostName|
|Get|/api/host/{hostId}|host 조회 api|hostId|
|Patch|/api/host/{hostId}|host 수정 api|hostId|
|Get|/api/host/now/{hostId}|특정 host의 현재 Alive 상태 조회 api|hostId|
|Get|/api/host/alive/{hostId}|host의 모니터링 결과 조회 api|hostId|
|Get|/api/host/alive|전체 host의 모니터링 결과 조회 api||

---

### Libraries
```
spring-boot: 2.7.x
jpa
lombok
spring-boot-devtools
```

---

### DDL
```sql

DROP DATABASE IF EXISTS hostalive;

DROP TABLE IF EXISTS hostalive.host;
   
CREATE DATABASE IF NOT EXISTS hostalive;

CREATE TABLE IF NOT EXISTS hostalive.host (
                                `host_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `create_time` datetime(6) DEFAULT NULL,
                                `update_time` datetime(6) DEFAULT NULL,
                                `host_ip` varchar(30) NOT NULL,
                                `host_name` varchar(30) NOT NULL,
                                `last_alive_time` datetime(6) DEFAULT NULL,
                                PRIMARY KEY (`host_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

```