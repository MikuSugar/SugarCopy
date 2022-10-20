#!/usr/bin/env bash
set -e
APP_HOME=$(dirname "$(readlink -f "$0")")
echo "APP_HOME: $APP_HOME"
cd "$APP_HOME"

java -Xms12m -Xmx12m -XX:MaxMetaspaceSize=12M -XX:+UseG1GC -XX:NativeMemoryTracking=detail -cp ./scopy.jar me.mikusugar.copy.SugarCopy
