## Script: UpdateTool.ps1
# 

param(
    [String]$LOG_FILE = 'E:\Notes\4_LearningNotes\PowerShellLearningNote\ScriptForAndroid\TestFolder\default.log',
    [String]$oName = 'default name',
    [String]$oStart = $(Get-date -F "yyyyMMdd HH:mm:ss.ms"),
    [Int]$oStatus = 0,
    [String]$oEnd = $(Get-date -F "yyyyMMdd HH:mm:ss.ms"),
    [String]$oDateRan = $(Get-date -F "yyyyMMdd"),
    $DebugPreference = "SilentlyContinue"
)

"This is $oName"
"This is $oStart"
"This is $oEnd"
"This is $oDateRan"