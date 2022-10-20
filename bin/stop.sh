#!/usr/bin/env bash
set -e
APP_HOME=$(dirname "$(readlink -f "$0")")
echo "APP_HOME: $APP_HOME"
cd "$APP_HOME"
