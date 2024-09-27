package com.woogleFX.engine.undoHandling;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.fx.AssetTab;
import com.woogleFX.engine.fx.menu.FXMenu;
import com.woogleFX.engine.undoHandling.userActions.*;

import java.util.Stack;

/** Handles all undo and redo logic. */
public class UndoManager {

    /** Registers any change to an asset.
     * This should be called exactly once for any action the user performs
     * (e.g. dragging or creating an object.) */
    public static void registerChange(UserAction... actions) {

        Asset level = AssetManager.getAsset();

        level.undoActions.add(actions);
        level.redoActions.clear();

        if (level.getEditingStatus() == AssetTab.NO_UNSAVED_CHANGES) {
            if (level.isBaseGame()) level.setEditingStatus(AssetTab.UNSAVED_CHANGES_UNMODIFIABLE, true);
            else level.setEditingStatus(AssetTab.UNSAVED_CHANGES, true);
        }

        FXMenu.updateAllButtons();

    }

    /** Undoes the last actions pushed to the undo stack. */
    public static void undo() {
        undoActions(
                AssetManager.getAsset().undoActions,
                AssetManager.getAsset().redoActions
        );
    }

    /** Undoes the last actions pushed to the redo stack, effectively redoing the last undid actions. */
    public static void redo() {
        undoActions(
                AssetManager.getAsset().redoActions,
                AssetManager.getAsset().undoActions
        );
    }


    private static void undoActions(Stack<UserAction[]> forwardActions, Stack<UserAction[]> backwardActions) {

        // If there are no actions to add, don't do anything.
        if (forwardActions.isEmpty()) return;

        // Get the most recent changes from the forward stack.
        UserAction[] changes = forwardActions.pop();

        // Add all the forward changes to the backward stack in reverse order.
        // This ensures that everything works correctly when the backward stack is executed.
        UserAction[] inverted = new UserAction[changes.length];
        for (int i = 0; i < changes.length; i++) inverted[i] = changes[changes.length - i - 1].getInverse();
        backwardActions.add(inverted);

        // Undo each of the changes.
        // This is done by calling each change's inverse action in inverse order.
        for (int i = changes.length - 1; i >= 0; i--) changes[i].getInverse().execute();
        
        // update all objects after all changes are done
        for (int i = changes.length - 1; i >= 0; i--)
            if (changes[i].getObject() != null) changes[i].getObject().update();

        // Update the current asset's editing status if all new changes have been un- or redone.
        Asset asset = AssetManager.getAsset();
        if (asset.undoActions.size() == asset.getLastSavedUndoPosition()) {
            asset.setEditingStatus(AssetTab.NO_UNSAVED_CHANGES, true);
        } else {
            if (asset.isBaseGame()) asset.setEditingStatus(AssetTab.UNSAVED_CHANGES_UNMODIFIABLE, true);
            else asset.setEditingStatus(AssetTab.UNSAVED_CHANGES, true);
        }

        // Refresh the buttons in case the undo/redo buttons need to be updated.
        FXMenu.updateAllButtons();

    }

}
