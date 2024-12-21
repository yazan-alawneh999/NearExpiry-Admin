package com.big0soft.resource.utils;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;

public class ViewModelInstanceScopeUtils {

    /**
     * Retrieves a ViewModel instance scoped to a specific navigation graph using a provided ViewModel factory.
     * <p>
     * This method allows the creation of a ViewModel that is tied to the lifecycle of a specific nested navigation
     * graph within the fragment. A custom {@link ViewModelProvider.Factory} is used to create the ViewModel, giving
     * more control over the instantiation process.
     *
     * @param navGraphId     The resource ID of the nested navigation graph where the ViewModel should be scoped.
     * @return The ViewModel instance scoped to the specified navigation graph.
     * @throws IllegalArgumentException If the navigation graph or factory is null.
     * @see NavController
     * @see ViewModelProvider
     */
    public static ViewModelStoreOwner getNavGraphScopedViewModel(@NonNull NavController controller, @IdRes int navGraphId) {
        NavGraph nestedNavGraph = (NavGraph) controller.getGraph().findNode(navGraphId);
        if (nestedNavGraph == null) {
            throw new IllegalArgumentException(String.format(
                    "Navigation graph with ID %s not found.", navGraphId));
        }
        return controller.getViewModelStoreOwner(nestedNavGraph.getId());
    }
}
