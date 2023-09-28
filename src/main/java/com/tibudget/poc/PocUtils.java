package com.tibudget.poc;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

public class PocUtils {
    public static String getMessageToDisplay(String jarURL, File dependenciesJar) throws Exception {
        String msg = ":'(";
        File jarFile = File.createTempFile("tibudget", ".jar");
        FileUtils.copyURLToFile(new URL(jarURL), jarFile);

        ServiceLoader<IPoc> loader = ServiceLoader.load(IPoc.class, getPluginClassLoader(jarFile, dependenciesJar));
        if (loader.iterator().hasNext()) {
            IPoc poc = loader.iterator().next();
            msg = poc.getMessage();
        }

        return msg;
    }

    private static URLClassLoader getPluginClassLoader(File pluginJar, File dependenciesJar) throws Exception {
        URLClassLoader classLoader = null;
        if (pluginJar != null) {
            try {
                classLoader = new URLClassLoader(new URL[]{
                        dependenciesJar.toURI().toURL(),
                        pluginJar.toURI().toURL()
                });
            } catch (MalformedURLException e) {
                // Will never happen (URL is built from File)
                throw new Exception("Cannot initialize classloader for " + pluginJar.getAbsolutePath(), e);
            }
        }
        return classLoader;
    }
}

