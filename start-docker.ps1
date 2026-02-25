# Start Docker Compose services in WSL
Write-Host "Starting Docker Compose services in WSL..." -ForegroundColor Green

wsl docker compose -f /mnt/c/Users/vijaypandey/vijay_learning/ollamademo/compose.yml up -d

if ($LASTEXITCODE -eq 0) {
    Write-Host "Docker Compose services started successfully!" -ForegroundColor Green
    Write-Host "Qdrant is running on ports 6333 and 6334" -ForegroundColor Cyan
} else {
    Write-Host "Failed to start Docker Compose services" -ForegroundColor Red
}

