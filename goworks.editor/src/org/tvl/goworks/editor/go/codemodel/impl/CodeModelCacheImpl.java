/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.project.Project;
import org.openide.util.Lookup;
import org.openide.util.Parameters;
import org.openide.util.lookup.ServiceProvider;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.CodeModelCache;
import org.tvl.goworks.editor.go.codemodel.ImportDeclarationModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;

/**
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=CodeModelCache.class)
public class CodeModelCacheImpl implements CodeModelCache {
    private static CodeModelCacheImpl instance;

    private CodeModelProjectCache defaultProjectCache;
    private final Map<Project, CodeModelProjectCache> projectCaches =
        new WeakHashMap<Project, CodeModelProjectCache>();

    @Override
    @NonNull
    public Collection<? extends PackageModel> getPackages(Project project) {
        CodeModelProjectCache cache = getProjectCache(project, false);
        if (cache == null) {
            return Collections.emptyList();
        }

        Collection<PackageModelImpl> packages = cache.getPackages();
        if (packages instanceof List) {
            return Collections.unmodifiableList((List<PackageModelImpl>)packages);
        }

        return Collections.unmodifiableCollection(cache.getPackages());
    }

    @NonNull
    @Override
    public Collection<? extends PackageModelImpl> getPackages(Project project, String path) {
        CodeModelProjectCache cache = getProjectCache(project, false);
        if (cache == null) {
            return Collections.emptyList();
        }

        PackageModelImpl unique = cache.getUniquePackage(path);
        if (unique == null) {
            return Collections.emptyList();
        }

        return Collections.singletonList(unique);
    }

    @CheckForNull
    public PackageModelImpl getUniquePackage(Project project, String path) {
        CodeModelProjectCache cache = getProjectCache(project, false);
        if (cache == null) {
            return null;
        }

        return cache.getUniquePackage(path);
    }

    @NonNull
    public Collection<? extends PackageModelImpl> resolvePackages(ImportDeclarationModel importModel) {
        Project project = importModel.getPackage().getProject();
        CodeModelProjectCache projectCache = getProjectCache(project, false);
        PackageModelImpl unique = projectCache != null ? projectCache.getUniquePackage(importModel.getPath()) : null;
        if (unique == null) {
            return Collections.emptyList();
        }

        return Collections.singletonList(unique);
    }

    @CheckForNull
    public CodeModelProjectCache getProjectCache(@NullAllowed Project project, boolean create) {
        synchronized (projectCaches) {
            CodeModelProjectCache cache = project != null ? projectCaches.get(project) : defaultProjectCache;
            if (cache == null && create) {
                cache = new CodeModelProjectCache(project);
                if (project == null) {
                    defaultProjectCache = cache;
                } else {
                    projectCaches.put(project, cache);
                }
            }

            return cache;
        }
    }

    @NonNull
    public final CodeModelProjectCache getOrCreateProjectCache(@NullAllowed Project project) {
        CodeModelProjectCache cache = getProjectCache(project, true);
        if (cache == null) {
            throw new IllegalStateException("getProjectCache should not return null when 'create' is true.");
        }

        return cache;
    }

    public void updateFile(@NonNull FileModelImpl fileModel) {
        assert fileModel.isFrozen();
        Project project = fileModel.getProject();
        CodeModelProjectCache projectCache = getOrCreateProjectCache(project);
        projectCache.updateFile(fileModel);
    }

    public static synchronized CodeModelCacheImpl getInstance() {
        if (instance == null) {
            instance = (CodeModelCacheImpl)Lookup.getDefault().lookup(CodeModelCache.class);
        }

        return instance;
    }

    public static <T extends CodeElementModel> Collection<T> findElementsByName(Collection<? extends T> elements, @NonNull String name) {
        Parameters.notNull("name", name);

        List<T> result = new ArrayList<T>();
        for (T element : elements) {
            if (name.equals(element.getName())) {
                result.add(element);
            }
        }

        return result;
    }

}
