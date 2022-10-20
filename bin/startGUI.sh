#!/usr/bin/env bash
set -e
APP_HOME=$(dirname "$(readlink -f "$0")")
echo "APP_HOME: $APP_HOME"
cd "$APP_HOME"

nohup java -Xms12m -Xmx12m -XX:MaxMetaspaceSize=24M -XX:+UseG1GC -XX:NativeMemoryTracking=detail -cp ./scopy.jar me.mikusugar.copy.AppGui >scopy.log >&1 &

echo "sugar gui app 启动"

