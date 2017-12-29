/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.vcs.log.impl;

import com.intellij.util.containers.ContainerUtil;
import com.intellij.vcs.log.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class VcsLogFilterCollectionImpl implements VcsLogFilterCollection {
  @NotNull public static final VcsLogFilterCollection EMPTY = new VcsLogFilterCollectionBuilder().build();

  @Nullable private final VcsLogBranchFilter myBranchFilter;
  @Nullable private final VcsLogUserFilter myUserFilter;
  @Nullable private final VcsLogHashFilter myHashFilter;
  @Nullable private final VcsLogDateFilter myDateFilter;
  @Nullable private final VcsLogTextFilter myTextFilter;
  @Nullable private final VcsLogStructureFilter myStructureFilter;
  @Nullable private final VcsLogRootFilter myRootFilter;

  public VcsLogFilterCollectionImpl(@Nullable VcsLogBranchFilter branchFilter,
                                    @Nullable VcsLogUserFilter userFilter,
                                    @Nullable VcsLogHashFilter hashFilter,
                                    @Nullable VcsLogDateFilter dateFilter,
                                    @Nullable VcsLogTextFilter textFilter,
                                    @Nullable VcsLogStructureFilter structureFilter,
                                    @Nullable VcsLogRootFilter rootFilter) {
    myBranchFilter = branchFilter;
    myUserFilter = userFilter;
    myHashFilter = hashFilter;
    myDateFilter = dateFilter;
    myTextFilter = textFilter;
    myStructureFilter = structureFilter;
    myRootFilter = rootFilter;
  }

  @Nullable
  @Override
  public <T extends VcsLogFilter> T get(@NotNull FilterKey<T> key) {
    if (key.equals(BRANCH_FILTER)) return (T)myBranchFilter;
    if (key.equals(USER_FILTER)) return (T)myUserFilter;
    if (key.equals(HASH_FILTER)) return (T)myHashFilter;
    if (key.equals(DATE_FILTER)) return (T)myDateFilter;
    if (key.equals(TEXT_FILTER)) return (T)myTextFilter;
    if (key.equals(STRUCTURE_FILTER)) return (T)myStructureFilter;
    if (key.equals(ROOT_FILTER)) return (T)myRootFilter;
    return null;
  }

  @Nullable
  public VcsLogBranchFilter getBranchFilter() {
    return myBranchFilter;
  }

  @Nullable
  public VcsLogHashFilter getHashFilter() {
    return myHashFilter;
  }

  @Nullable
  public VcsLogUserFilter getUserFilter() {
    return myUserFilter;
  }

  @Nullable
  public VcsLogDateFilter getDateFilter() {
    return myDateFilter;
  }

  @Nullable
  public VcsLogTextFilter getTextFilter() {
    return myTextFilter;
  }

  @Nullable
  public VcsLogStructureFilter getStructureFilter() {
    return myStructureFilter;
  }

  @Nullable
  public VcsLogRootFilter getRootFilter() {
    return myRootFilter;
  }

  @NotNull
  @Override
  public List<VcsLogFilter> getFilters() {
    return ContainerUtil.skipNulls(Arrays.asList(myUserFilter, myDateFilter, myTextFilter, myStructureFilter, myBranchFilter, myHashFilter, myRootFilter));
  }

  @Override
  public String toString() {
    return "filters: (" +
           (myBranchFilter != null ? myBranchFilter + ", " : "") +
           (myUserFilter != null ? myUserFilter + ", " : "") +
           (myHashFilter != null ? myHashFilter + ", " : "") +
           (myDateFilter != null ? myDateFilter + ", " : "") +
           (myTextFilter != null ? myTextFilter + ", " : "") +
           (myStructureFilter != null ? myStructureFilter + ", " : "") +
           (myRootFilter != null ? myRootFilter : "") + ")";
  }

  public static class VcsLogFilterCollectionBuilder {
    @Nullable private VcsLogBranchFilter myBranchFilter;
    @Nullable private VcsLogUserFilter myUserFilter;
    @Nullable private VcsLogHashFilter myHashFilter;
    @Nullable private VcsLogDateFilter myDateFilter;
    @Nullable private VcsLogTextFilter myTextFilter;
    @Nullable private VcsLogStructureFilter myStructureFilter;
    @Nullable private VcsLogRootFilter myRootFilter;

    public VcsLogFilterCollectionBuilder() {
    }

    public VcsLogFilterCollectionBuilder(@NotNull VcsLogFilterCollection filterCollection) {
      myBranchFilter = filterCollection.get(BRANCH_FILTER);
      myUserFilter = filterCollection.get(USER_FILTER);
      myHashFilter = filterCollection.get(HASH_FILTER);
      myDateFilter = filterCollection.get(DATE_FILTER);
      myTextFilter = filterCollection.get(TEXT_FILTER);
      myStructureFilter = filterCollection.get(STRUCTURE_FILTER);
      myRootFilter = filterCollection.get(ROOT_FILTER);
    }

    @NotNull
    public VcsLogFilterCollectionBuilder with(@Nullable VcsLogBranchFilter filter) {
      myBranchFilter = filter;
      return this;
    }

    @NotNull
    public VcsLogFilterCollectionBuilder with(@Nullable VcsLogUserFilter filter) {
      myUserFilter = filter;
      return this;
    }

    @NotNull
    public VcsLogFilterCollectionBuilder with(@Nullable VcsLogHashFilter filter) {
      myHashFilter = filter;
      return this;
    }

    @NotNull
    public VcsLogFilterCollectionBuilder with(@Nullable VcsLogDateFilter filter) {
      myDateFilter = filter;
      return this;
    }

    @NotNull
    public VcsLogFilterCollectionBuilder with(@Nullable VcsLogTextFilter filter) {
      myTextFilter = filter;
      return this;
    }

    @NotNull
    public VcsLogFilterCollectionBuilder with(@Nullable VcsLogStructureFilter filter) {
      myStructureFilter = filter;
      return this;
    }

    @NotNull
    public VcsLogFilterCollectionBuilder with(@Nullable VcsLogRootFilter filter) {
      myRootFilter = filter;
      return this;
    }

    @NotNull
    public VcsLogFilterCollection build() {
      return new VcsLogFilterCollectionImpl(myBranchFilter, myUserFilter, myHashFilter, myDateFilter, myTextFilter, myStructureFilter,
                                            myRootFilter);
    }
  }
}
