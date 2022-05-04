/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.sqlutil.jdbc;

public abstract class RemoteJdbcConfig<T extends RemoteJdbcConfig<?>> extends JdbcConfig<T> {
    private String host = "localhost";
    private String port;
    private String database;

    public T host(String host) {
        this.host = host;
        return self();
    }

    public T ipv4(String host) {
        //TODO validation
        return host(host);
    }

    public T ipv6(String host) {
        //TODO validation
        if (host.startsWith("[") && host.endsWith("]")) {
            return host(host);
        }
        if (!host.startsWith("[") && !host.endsWith("]")) {
            return host(String.format("[%s]", host));
        }
        throw new IllegalArgumentException("Unbalanced bracket");
    }

    public T port(String port) {
        this.port = port;
        return self();
    }

    public T port(int port) {
        return port(String.valueOf(port));
    }

    public T database(String database) {
        this.database = database;
        return self();
    }

    @Override
    protected String baseUrl() {
        var jdbc = new StringBuilder("jdbc:")
                .append(driver())
                .append(":");

        if (host != null) {
            jdbc.append("//").append(host);

            // Port depends on existence of host
            if (port != null) {
                jdbc.append(":").append(port);
            }
            jdbc.append("/");
        }

        if (database != null) {
            jdbc.append(database);
        } else if (host == null) {
            jdbc.append("/");
        }

        return jdbc.toString();
    }

    public T login(String user, String password) {
        password(password).user(user);
        return self();
    }

    /**
     * Sets the password for the connection.
     *
     * @param password password
     * @return builder instance
     */
    public T password(String password) {
        return addProperty("password", password);
    }

    /**
     * Sets the user for the connection.
     *
     * @param user user
     * @return builder instance
     */
    public T user(String user) {
        return addProperty("user", user);
    }
}
