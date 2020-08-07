#!/usr/bin/env bash

function gitFun() {
    git log --pretty="format:%s" -1
    echo -e "\n"
    git log --pretty="format:%b" -1
}
logMsg=$(gitFun)

function changelog_tg() {
  local CHAT_ID
  local MSG
  CHAT_ID="${1}"
  MSG="${2}"
  curl -F chat_id="${CHAT_ID}" -F text="${MSG}" -F parse_mode="Markdown" "https://api.telegram.org/bot${TG_TOKEN:?}/sendMessage" >/dev/null 2>&1
}

changelog_tg "${TG_TO:?}" "${logMsg}"