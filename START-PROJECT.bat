@echo off
title Banco Digital

color 0A

echo.
echo ===========================================
echo          BANCO DIGITAL
echo ===========================================
echo.

echo [1/5] Limpando containers e volumes...
docker compose down --remove-orphans -v

echo.
echo ===========================================
echo.

echo [2/5] Construindo imagens...
docker compose build --no-cache

echo.
echo ===========================================
echo.

echo [3/5] Subindo containers...
docker compose up -d

echo.
echo ===========================================
echo.

echo [4/5] Status dos containers
docker compose ps

echo.
echo ===========================================
echo.

echo [5/5] Logs da aplicacao
docker compose logs web

echo.
echo ===========================================
echo Projeto inicializado!
echo Spring Boot: http://localhost:8080
echo pgAdmin:     http://localhost:8081
echo ===========================================

pause