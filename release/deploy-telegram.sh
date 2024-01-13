#!/usr/bin/env bash

function send_to_tg() {
  local FILE
  local CHAT_ID
  local CAPTION
  FILE="${1}"
  CHAT_ID="${2}"
  CAPTION="${3}"
  curl -F chat_id="${CHAT_ID}" -F document="@${FILE}" -F caption="${CAPTION}" -F parse_mode="Markdown" "https://api.telegram.org/bot${TG_TOKEN:?}/sendDocument" >/dev/null 2>&1
}

# Extract versionCode and versionName from build.gradle
versionCode=$(grep "versionCode" app/build.gradle | awk '{print $2}' | tr -d '\r')
versionName=$(grep "versionName" app/build.gradle | awk '{print $2}' | tr -d '\r')

# Set the source and destination paths
source_file="${TG_FILE:?}"
destination_path="app/build/outputs/apk/release/Weathering${versionCode}(${versionName}).apk"

# Rename the file (providing full path for source)
mv "${source_file}" "${destination_path}"

# Send to Telegram
send_to_tg "${destination_path}" "${TG_TO:?}" "weathering-alpha-${GITHUB_RUN_NUMBER}-release"