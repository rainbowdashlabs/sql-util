/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.sqlutil.jdbc;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class JdbcConfig<T extends JdbcConfig<?>> {
    private final Set<JdbProperty<?>> properties = new HashSet<>();

    /**
     * Returns the driver name of the jdbc url
     *
     * @return driver
     */
    protected abstract String driver();

    protected T self() {
        return (T) this;
    }

    /**
     * Add a new parameter to the url
     *
     * @param key   key
     * @param value value
     * @param <V>   value type
     * @return builder instance
     */
    public <V> T addProperty(String key, V value) {
        properties.add(new JdbProperty<>(key, value));
        return self();
    }

    protected abstract String baseUrl();

    protected String parameter() {
        if (properties.isEmpty()) return "";
        return "?" + properties.stream().map(prop -> String.format("%s=%s", prop.key(), prop.value()))
                .collect(Collectors.joining("&"));
    }

    public String jdbcUrl() {
        return baseUrl() + parameter();
    }
}
