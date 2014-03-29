package com.tagtraum.perf.gcviewer.view.model;

import java.util.Arrays;

/**
 * <p>Holds a group of resource names (those displayed in the same GCDocument).</p>
 * 
 * <p>This class was refactored from "URLSet" inside RecentResourcNamesModel.</p>
 * @author <a href="mailto:gcviewer@gmx.ch">Joerg Wuethrich</a>
 * <p>created on: 05.03.2014</p>
 */
public class ResourceNameGroup {

    private String[] resourceNames;

    /**
     * Initialise a group from an array of resource names.
     * 
     * @param resourceNames array of resource names
     */
    public ResourceNameGroup(String[] resourceNames) {
        this.resourceNames = resourceNames;
    }
    
    /**
     * Initialise a group from a single string consisting of resource names separated by ";"
     * 
     * @param resourceNameGroup resource names separated by ";"
     */
    public ResourceNameGroup(String resourceNameGroup) {
        if (resourceNameGroup.indexOf(";") >= 0) {
            this.resourceNames = resourceNameGroup.split(";");
        }
        else {
            this.resourceNames = new String[]{ resourceNameGroup };
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ResourceNameGroup other = (ResourceNameGroup) obj;
        if (!Arrays.equals(resourceNames, other.resourceNames)) {
            return false;
        }
        return true;
    }

    /**
     * Get all resource names of the group separated by a ";"
     * 
     * @return single string with all resource names separated by a ";"
     */
    public String getGroupString() {
        StringBuilder sb = new StringBuilder();
        for (String resourceName : resourceNames) {
            sb.append(resourceName).append(";");
        }
        
        return sb.toString();
    }
    
    /**
     * Get short version of resource names (only file name without path), if more than one
     * resource is in this group.
     * 
     * @return get short group name (only file name without path), if there is more than one
     * resource
     */
    public String getGroupStringShort() {
        if (resourceNames.length > 1) {
            StringBuilder sb = new StringBuilder();
            for (String resourceName : resourceNames) {
                // test for "/" and "\\" because in Windows you have a "/" in a http url
                // but "\\" in file strings
                int lastIndexOfPathSeparator = resourceName.lastIndexOf("/");
                if (lastIndexOfPathSeparator < 0) {
                    lastIndexOfPathSeparator = resourceName.lastIndexOf("\\");
                }
                sb.append(resourceName.substring(lastIndexOfPathSeparator + 1)).append(";");
            }
            return sb.toString();
        }
        else {
            return resourceNames[0];
        }
    }
    
    /**
     * Get all resources names as an array of strings.
     * 
     * @return resource names as array of strings
     */
    public String[] getResourceNames() {
        return resourceNames;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(resourceNames);
        return result;
    }

}