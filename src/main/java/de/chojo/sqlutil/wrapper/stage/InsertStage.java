/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.sqlutil.wrapper.stage;

import de.chojo.sqlutil.wrapper.QueryBuilderConfig;
import de.chojo.sqlutil.wrapper.exception.WrappedQueryExecutionException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public interface InsertStage extends UpdateStage {
    /**
     * Retrieve the first created key async
     *
     * @return A {@link CompletableFuture} to retrieve the data.
     * @throws WrappedQueryExecutionException if {@link QueryBuilderConfig#isThrowing()} is set to {@code true} and a exceptions occurs during query building or execution
     */
    CompletableFuture<Optional<Long>> key();

    /**
     * Retrieve the first created key async
     *
     * @param executor the executor used for async call
     * @return A {@link CompletableFuture} to retrieve the data.
     * @throws WrappedQueryExecutionException if {@link QueryBuilderConfig#isThrowing()} is set to {@code true} and a exceptions occurs during query building or execution
     */
    CompletableFuture<Optional<Long>> key(Executor executor);

    /**
     * Retrieve the first created key synced
     *
     * @return result wrapped into an optional
     * @throws WrappedQueryExecutionException if {@link QueryBuilderConfig#isThrowing()} is set to {@code true} and a exceptions occurs during query building or execution
     */
    Optional<Long> keySync();

    /**
     * Retrieve all created keys async as a list
     *
     * @return A {@link CompletableFuture} to retrieve the data.
     * @throws WrappedQueryExecutionException if {@link QueryBuilderConfig#isThrowing()} is set to {@code true} and a exceptions occurs during query building or execution
     */
    CompletableFuture<List<Long>> keys();

    /**
     * Retrieve all created keys async as a list
     *
     * @param executor the executor used for async call
     * @return A {@link CompletableFuture} to retrieve the data.
     * @throws WrappedQueryExecutionException if {@link QueryBuilderConfig#isThrowing()} is set to {@code true} and a exceptions occurs during query building or execution
     */
    CompletableFuture<List<Long>> keys(Executor executor);
}
