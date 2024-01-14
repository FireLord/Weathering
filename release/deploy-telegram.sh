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

# Function to extract version information from build.gradle or build.gradle.kts
function extract_version_info() {
  local FILE="${1}"

  # Check if the file exists
  if [ ! -f "${FILE}" ]; then
    echo "File not found: ${FILE}"
    return 1  # File not found
  fi

  # Extract versionCode and versionName using awk
  VERSION_CODE=$(awk -F'[= \t]+' '/versionCode/ {print $NF}' "${FILE}" | tr -d '\r')
  VERSION_NAME=$(awk -F'"' '/versionName/ {print $2}' "${FILE}" | tr -d '\r')

  # If the above extraction fails for versionCode, try another approach
  if [ -z "${VERSION_CODE}" ]; then
    VERSION_CODE=$(awk -F= '/versionCode/ {gsub(/[ \t]+/, "", $2); print $2}' "${FILE}" | tr -d '\r' | tr -d '\n' | tr -d ' ')
  fi

  # Print version information
  echo "Version Code: ${VERSION_CODE}"
  echo "Version Name: ${VERSION_NAME}"

  return 0  # Success
}

# Extract version information from either build.gradle or build.gradle.kts
if extract_version_info "app/build.gradle"; then
  :
elif extract_version_info "app/build.gradle.kts"; then
  :
else
  echo "Neither build.gradle nor build.gradle.kts found."
  exit 1
fi

# Set the source and destination paths
source_file="${TG_FILE:?}"
destination_path="app/build/outputs/apk/release/Weathering${VERSION_CODE}(${VERSION_NAME}).apk"

# Rename the file (providing full path for source)
mv "${source_file}" "${destination_path}"

# Send to Telegram
send_to_tg "${destination_path}" "${TG_TO:?}" "weathering-alpha-${VERSION_NAME}-release"