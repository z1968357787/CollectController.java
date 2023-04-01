#!/bin/bash
screen_name=$"collect-task"
screen -dmS $screen_name
cmd=$"cd /home/yj/autodeploy/tool/ && source /etc/profile && setsid java -jar log_server-0.0.4-SNAPSHOT-shaded.jar >nohup.txt 2>&1 &";
screen -x -S $screen_name -p 0 -X stuff "$cmd"
screen -x -S $screen_name -p 0 -X stuff $'\n'
APP_NAME=/home/yj/autodeploy/application/Newdeserialization.jar
LOG_FILE=/home/yj/autodeploy/tool/new.log
source /etc/profile
nohup java -javaagent:/home/yj/autodeploy/tool/log_agent-0.0.4-SNAPSHOT-shaded.jar=/home/yj/autodeploy/config/new/agent-config.xml -jar $APP_NAME > $LOG_FILE 2>&1 &