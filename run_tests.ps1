# run_tests.ps1
# Script PowerShell untuk menjalankan serangkaian skenario pengujian otomatis
# Menggunakan input redirection untuk mengirim jawaban ke aplikasi console Java

$javac = 'javac'
$java = 'java'
$workDir = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $workDir

Write-Host "Compiling Java sources..."
& $javac *.java
if ($LASTEXITCODE -ne 0) {
  Write-Host "Compilation failed." -ForegroundColor Red
  exit 1
}
Write-Host "Compilation successful.`n"

# Fungsi bantu: menjalankan app dengan input dan capture output
function Run-Scenario {
  param(
    [string]$Name,
    [string]$InputFile
  )

  Write-Host "Running scenario: $Name"
  $outputFile = "$Name-output.txt"

  # Jalankan java Main dengan input redirection
  & $java Main < $InputFile > $outputFile

  if ($LASTEXITCODE -ne 0) {
    Write-Host "$Name: Execution failed with exit code $LASTEXITCODE" -ForegroundColor Red
  } else {
    Write-Host "$Name: Completed. Output saved to $outputFile" -ForegroundColor Green
  }
}

# Buat folder tmp untuk menyimpan input/output
$tmpDir = "test_inputs"
if (!(Test-Path $tmpDir)) { New-Item -ItemType Directory -Path $tmpDir | Out-Null }
Set-Location $tmpDir

# Scenario 1: Build & show menu then exit
@"
9
"@ | Out-File -Encoding ASCII scenario_buildrun.txt

# Scenario 2: Search flight success (Jakarta -> Bali) then exit
@"
1
Jakarta
Bali
9
"@ | Out-File -Encoding ASCII scenario_search_flight_success.txt

# Scenario 3: Search flight not found
@"
1
X
Y
9
"@ | Out-File -Encoding ASCII scenario_search_flight_notfound.txt

# Scenario 4: Book flight (choose 1) then view reservations then exit
@"
5
1
6
9
"@ | Out-File -Encoding ASCII scenario_book_flight_view.txt

# Scenario 5: Book hotel then cancel
@"
7
1
8
1000
9
"@ | Out-File -Encoding ASCII scenario_book_hotel_cancel.txt

Set-Location $workDir

# Jalankan semua scenario
Run-Scenario -Name "scenario_buildrun" -InputFile "$workDir\$tmpDir\scenario_buildrun.txt"
Run-Scenario -Name "scenario_search_flight_success" -InputFile "$workDir\$tmpDir\scenario_search_flight_success.txt"
Run-Scenario -Name "scenario_search_flight_notfound" -InputFile "$workDir\$tmpDir\scenario_search_flight_notfound.txt"
Run-Scenario -Name "scenario_book_flight_view" -InputFile "$workDir\$tmpDir\scenario_book_flight_view.txt"
Run-Scenario -Name "scenario_book_hotel_cancel" -InputFile "$workDir\$tmpDir\scenario_book_hotel_cancel.txt"

Write-Host "All scenarios executed. Check output files in $workDir for details." -ForegroundColor Cyan
