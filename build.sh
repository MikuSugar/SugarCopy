#!/usr/bin/env bash
set -e
APP_HOME=$(dirname "$(readlink -f "$0")")
echo "APP_HOME: $APP_HOME"
cd "$APP_HOME"

mvn clean package assembly:single

mv "target/sugar-copy-1.0-SNAPSHOT-jar-with-dependencies.jar" bin/scopy.jar






