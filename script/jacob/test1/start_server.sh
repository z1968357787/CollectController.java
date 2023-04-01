#!/bin/bash
screen_name=$"collect-task"
screen -dmS $screen_name
cmd=$"cd /home/$1/autodeploy/tool/; setsid java -jar log_server-0.0.4-SNAPSHOT.jar >nohup.txt 2>&1 &";
screen -x -S $screen_name -p 0 -X stuff "$cmd"
screen -x -S $screen_name -p 0 -X stuff $'\n'