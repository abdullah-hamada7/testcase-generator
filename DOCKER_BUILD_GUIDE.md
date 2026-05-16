### 1. Config Server

```bash
docker build -t m07amed66/configserver:v1 ./configserver
```

### 2. Eureka Server

```bash
docker build -t m07amed66/eurekaserver:v1 ./eurekaserver
```

### 3. Gateway Server

```bash
docker build -t m07amed66/gatewayserver:v1 ./gatewayserver
```

### 4. Projects Service

```bash
docker build -t m07amed66/projects:v1 ./projects
```

### 5. Requirement Service

```bash
docker build -t m07amed66/requirement:v1 ./requirement
```

### 6. Testcase Service

```bash
docker build -t m07amed66/testcase:v1 ./testcase
```

### 7. AI Service

```bash
docker build -t m07amed66/ai-service:v1 ./AI-Service
```

## Push to Docker Hub

```bash
docker push m07amed66/configserver:v1
docker push m07amed66/eurekaserver:v1
docker push m07amed66/gatewayserver:v1
docker push m07amed66/projects:v1
docker push m07amed66/requirement:v1
docker push m07amed66/testcase:v1
docker push m07amed66/ai-service:v1
```

```bash
docker compose -f docker-compose\default\compose.yml up -d
```
