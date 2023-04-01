package com.processmining.logdeploy.autodeploy.util;

import com.processmining.logdeploy.autodeploy.entity.Path;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

public class GenerateConfiguration {

    public static Boolean GenerateAgentConfig(String username, String collectName, List<String> collectPackageList, String logName) {
        String applicationPattern = ".*<application></application>.*";
        String tierPattern = ".*<tier></tier>.*";
        String methodPointCutPattern = ".*<method-pointcut>.*";
        String threadCallPointCutPattern = ".*<thread-call-pointcut>.*";
        String filePattern = ".*<file></file>.*";

        File directory = new File(Path.LOCAL_CONFIG(username, collectName));
        if (!directory.exists())
            directory.mkdirs();

        File agentConfig = new File(Path.LOCAL_CONFIG(username, collectName) + "/agent-config.xml");
        if (agentConfig.exists() && agentConfig.isFile())
            agentConfig.delete();

        File config = new File(Path.LOCAL_CONFIG(username, collectName) + "/config.xml");
        if (config.exists() && config.isFile())
            config.delete();

        try {
            // generate agent-config.xml
            agentConfig.createNewFile();
            FileOutputStream acFileOutputStream = new FileOutputStream(agentConfig);
            BufferedOutputStream acBufferedOutputStream = new BufferedOutputStream(acFileOutputStream);

            File agentConfigTemplate = new File(Path.CONFIG + "/template/agent-config.xml");
            BufferedReader acBufferedReader = new BufferedReader(new FileReader(agentConfigTemplate));
            String readString = null;
            while ((readString = acBufferedReader.readLine()) != null) {
                if (Pattern.matches(applicationPattern, readString)) {
                    acBufferedOutputStream.write(("\t\t<application>" + collectName + "</application>\n").getBytes());
                } else if (Pattern.matches(tierPattern, readString)) {
                    acBufferedOutputStream.write(("\t\t<tier>" + collectName + "</tier>\n").getBytes());
                } else if (Pattern.matches(methodPointCutPattern, readString)) {
                    acBufferedOutputStream.write(("\t\t<method-pointcut>\n").getBytes());
                    acBufferedOutputStream.write(("\t\t\t<enabled>true</enabled>\n").getBytes());
                    for (String collectPackage: collectPackageList) {
                        acBufferedOutputStream.write(("\t\t\t<include>" + collectPackage + ".*</include>\n").getBytes());
                    }
                } else if (Pattern.matches(threadCallPointCutPattern, readString)) {
                    acBufferedOutputStream.write(("\t\t<thread-call-pointcut>\n").getBytes());
                    acBufferedOutputStream.write(("\t\t\t<enabled>true</enabled>\n").getBytes());
                    for (String collectPackage: collectPackageList) {
                        acBufferedOutputStream.write(("\t\t\t<include>" + collectPackage + ".*</include>\n").getBytes());
                    }
                } else {
                    acBufferedOutputStream.write((readString + "\n").getBytes());
                }
            }
            acBufferedReader.close();

            acBufferedOutputStream.flush();
            acBufferedOutputStream.close();

            // generate config.xml
            config.createNewFile();
            FileOutputStream cFileOutputStream = new FileOutputStream(config);
            BufferedOutputStream cBufferedOutputStream = new BufferedOutputStream(cFileOutputStream);

            File configTemplate = new File(Path.CONFIG + "/template/config.xml");
            BufferedReader cBufferedReader = new BufferedReader(new FileReader(configTemplate));
            String cReadString = null;
            while ((cReadString = cBufferedReader.readLine()) != null) {
                if (Pattern.matches(filePattern, cReadString)) {
                    cBufferedOutputStream.write(("\t\t\t<file>" + logName + ".txt</file>\n").getBytes());
                } else {
                    cBufferedOutputStream.write((cReadString + "\n").getBytes());
                }
            }
            cBufferedReader.close();

            cBufferedOutputStream.flush();
            cBufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static Boolean GenerateAgentConfigForJar(String version, String projectName, String useCase, String username, String collectName, List<String> collectPackageList, String logName) {
        String versionPattern = ".*<version></version>.*";
        String projectPattern = ".*<project></project>.*";
        String usecasePattern = ".*<usecase></usecase>.*";
        String applicationPattern = ".*<application></application>.*";
        String tierPattern = ".*<tier></tier>.*";
        String methodPointCutPattern = ".*<method-pointcut>.*";
        String threadCallPointCutPattern = ".*<thread-call-pointcut>.*";
        String filePattern = ".*<file></file>.*";

        File directory = new File(Path.LOCAL_CONFIG(username, collectName));
        if (!directory.exists())
            directory.mkdirs();

        File agentConfig = new File(Path.LOCAL_CONFIG(username, collectName) + "/agent-config.xml");
        if (agentConfig.exists() && agentConfig.isFile())
            agentConfig.delete();

        File config = new File(Path.LOCAL_CONFIG(username, collectName) + "/config.xml");
        if (config.exists() && config.isFile())
            config.delete();

        try {
            // generate agent-config.xml
            agentConfig.createNewFile();
            FileOutputStream acFileOutputStream = new FileOutputStream(agentConfig);
            BufferedOutputStream acBufferedOutputStream = new BufferedOutputStream(acFileOutputStream);

            File agentConfigTemplate = new File(Path.CONFIG + "/template/agent-config.xml");
            BufferedReader acBufferedReader = new BufferedReader(new FileReader(agentConfigTemplate));
            String readString = null;
            while ((readString = acBufferedReader.readLine()) != null) {
                if (Pattern.matches(applicationPattern, readString)) {
                    acBufferedOutputStream.write(("\t\t<application>" + collectName + "</application>\n").getBytes());
                } else if (Pattern.matches(versionPattern,readString)) {
                    acBufferedOutputStream.write(("\t\t<version>" + version + "</version>\n").getBytes());
                } else if (Pattern.matches(projectPattern,readString)) {
                    acBufferedOutputStream.write(("\t\t<project>" + projectName + "</project>\n").getBytes());
                } else if (Pattern.matches(usecasePattern,readString)) {
                    acBufferedOutputStream.write(("\t\t<usecase>" + useCase + "</usecase>\n").getBytes());
                } else if (Pattern.matches(tierPattern, readString)) {
                    acBufferedOutputStream.write(("\t\t<tier>" + collectName + "</tier>\n").getBytes());
                } else if (Pattern.matches(methodPointCutPattern, readString)) {
                    acBufferedOutputStream.write(("\t\t<method-pointcut>\n").getBytes());
                    acBufferedOutputStream.write(("\t\t\t<enabled>true</enabled>\n").getBytes());
                    for (String collectPackage: collectPackageList) {
                        acBufferedOutputStream.write(("\t\t\t<include>" + collectPackage + "</include>\n").getBytes());
                    }
                } else if (Pattern.matches(threadCallPointCutPattern, readString)) {
                    acBufferedOutputStream.write(("\t\t<thread-call-pointcut>\n").getBytes());
                    acBufferedOutputStream.write(("\t\t\t<enabled>true</enabled>\n").getBytes());
                    for (String collectPackage: collectPackageList) {
                        acBufferedOutputStream.write(("\t\t\t<include>" + collectPackage + "</include>\n").getBytes());
                    }
                } else {
                    acBufferedOutputStream.write((readString + "\n").getBytes());
                }
            }
            acBufferedReader.close();

            acBufferedOutputStream.flush();
            acBufferedOutputStream.close();

            // generate config.xml
            config.createNewFile();
            FileOutputStream cFileOutputStream = new FileOutputStream(config);
            BufferedOutputStream cBufferedOutputStream = new BufferedOutputStream(cFileOutputStream);

            File configTemplate = new File(Path.CONFIG + "/template/config.xml");
            BufferedReader cBufferedReader = new BufferedReader(new FileReader(configTemplate));
            String cReadString = null;
            while ((cReadString = cBufferedReader.readLine()) != null) {
                if (Pattern.matches(filePattern, cReadString)) {
                    cBufferedOutputStream.write(("\t\t\t<file>" + logName + ".txt</file>\n").getBytes());
                } else {
                    cBufferedOutputStream.write((cReadString + "\n").getBytes());
                }
            }
            cBufferedReader.close();

            cBufferedOutputStream.flush();
            cBufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
