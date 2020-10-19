package cz.muni.fi.pb162.hw01.impl;

import cz.muni.fi.pb162.hw01.DefaultPortResolver;
import cz.muni.fi.pb162.hw01.url.SmartUrl;
import java.util.Arrays;

/**
 * This class represent internet URL deconstructor.
 *
 * @author Pavol Baran
 */

public class Url implements SmartUrl {
    private String url = "";
    private String protocol = "";
    private String host = "";
    private int port;
    private String path = "";
    private String query = "";
    private String fragment = "";

    /**
     * Constructor for Url
     *
     * @param url url to be deconstructed.
     */
    public Url(String url) {
        this.url = url;
        splitPrimitives();
        splitPath();
    }

    @Override
    public String getAsString() {
        StringBuilder outputString = new StringBuilder();
        DefaultPortResolver portResolver = new DefaultPortResolver();

        if (getProtocol().equals("") || getHost().equals("")) {
            return null;
        }
        outputString.append(getProtocol()).append("://").append(getHost());

        if (getPort() != 0 && getPort() != portResolver.getPort(getProtocol())) {
            outputString.append(":").append(getPort());
        }
        if (!getPath().isEmpty()) {
            outputString.append("/").append(getPath());
        }
        if (!getFragment().isEmpty()) {
            outputString.append("#").append(getFragment());
        }
        if (!getQuery().isEmpty()) {
            outputString.append("?").append(getQuery());
        }
        return outputString.toString();
    }

    @Override
    public String getAsRawString() {
        return url;
    }

    @Override
    public boolean isSameAs(SmartUrl url) {
        return this.getAsString().equals(url.getAsString());
    }

    @Override
    public boolean isSameAs(String url) {
        Url temp = new Url(url);
        return this.getAsString().equals(temp.getAsString());
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public String getFragment() {
        return fragment;
    }

    /**
     * Splits protocol, fragment, query, host and port part of a URL.
     */
    public void splitPrimitives() {

        if (url.contains("://")) {
            protocol = url.substring(0, url.indexOf("://"));
        }

        if (url.contains("#")) {
            fragment = url.substring(url.lastIndexOf("#") + 1);
        }

        if(url.contains("?")) {
            String queryResult = url.substring(url.lastIndexOf("?") + 1);
            queryResult.replace("#" + fragment, "");
            String[] queries = queryResult.split("&");
            Arrays.sort(queries);
            queryResult = "";

            for (String query:queries) {
                queryResult = queryResult + query + "&";
            }

            if (! queryResult.isEmpty()) {
                query = queryResult.substring(0, queryResult.length() - 1);
            }
        }

        String hostResult = url.replace(protocol + "://", "");
        hostResult = trimUrl(hostResult, new char[]{':', '/', '?', '#'});
        if (hostResult.contains(".")) {
            host = hostResult;
        }

        DefaultPortResolver defaultPortResolver = new DefaultPortResolver();
        String portResult = url.replace(protocol + "://", "");
        if (portResult.contains(":")) {
            portResult = portResult.substring(portResult.indexOf(":") + 1);
            portResult = trimUrl(portResult, new char[]{':', '/', '?', '#'});
            if (!portResult.isEmpty()) {
                port = Integer.parseInt(portResult);
            }
        } else if (! protocol.isEmpty()) {
            port = defaultPortResolver.getPort(protocol);
        }
    }

    /**
     * Splits path part of a URL.
     */
    public void splitPath() {
        String pathResult = url.replace(protocol + "://", "");
        if (pathResult.contains("/")) {
            pathResult = pathResult.substring(pathResult.indexOf("/"));
            pathResult = trimUrl(pathResult, new char[]{ '?', '#'});
            String[] paths = pathResult.split("/");
            pathResult = "";

            // Resolves invalid path.
            int depth = 1;
            for (int i = 0; i < paths.length; i++) {
                if (paths[i].equals("..")) {
                    depth -= 1;
                    if (depth < 1) {
                        break;
                    }
                    pathResult = pathResult.substring(0, pathResult.lastIndexOf("/"));

                } else if (!paths[i].equals(".") && !paths[i].equals("")) {
                    pathResult = pathResult + "/" + paths[i];
                    depth += 1;
                }
            }
            if (! pathResult.isEmpty()) {
                path = pathResult.substring(1, pathResult.length());
            }
        }
    }

    /**
     * Returns trimmed inputString based on breakCharacters.
     * String gets trimmed to the first match with a character from breakCharacters.
     *
     * @param inputString to be trimmed.
     * @param breakCharacters array of characters.
     * @return string trimmed to the first match with character from breakCharacters.
     */
    public String trimUrl(String inputString, char[] breakCharacters){
        int breakPoint = inputString.length();

        for (char inputChar : inputString.toCharArray()) {
            for (char breakChar : breakCharacters) {
                if (inputChar == breakChar && inputString.indexOf(inputChar) < breakPoint){
                    breakPoint = inputString.indexOf(inputChar);
                    break;
                }
            }
        }
        return inputString.substring(0, breakPoint);
    }
}
