# Stop Docker Compose services in WSL
Write-Host "Stopping Docker Compose services in WSL..." -ForegroundColor Yellow

wsl docker compose -f /mnt/c/Users/vijaypandey/vijay_learning/ollamademo/compose.yml down

if ($LASTEXITCODE -eq 0) {
    Write-Host "Docker Compose services stopped successfully!" -ForegroundColor Green
} else {
    Write-Host "Failed to stop Docker Compose services" -ForegroundColor Red
}

